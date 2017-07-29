package ru.job4j;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

/**
 * HTMLParser class.
 *
 * @author Denis
 * @since 15.07.2017
 */
class HTMLParser {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(HTMLParser.class);

    /**
     * App properties.
     */
    private Properties prop;

    /**
     * Topic year.
     */
    private int topicYear;

    /**
     * Count of found topic startParsing in last year.
     */
    private int lastYearTopics;

    /**
     * Manager database.
     */
    private ManagerDB managerDB;

    /**
     * Default constructor.
     */
    HTMLParser() {
        readAppProperties();
    }

    /**
     * Main method.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        new HTMLParser().startParsing();
    }

    /**
     * Start parse.
     */
    private void startParsing() {
        this.managerDB = new ManagerDB();
        this.managerDB.start(this.prop.getProperty("dbUrl"), this.prop.getProperty("dbUser"),
                this.prop.getProperty("dbPassword"), this.prop.getProperty("dbName"));
        if (checkNeedsForRun(prop.getProperty("lastUpdate"), prop.getProperty("runOncePerDay"))) {
            String url = "http://www.sql.ru/forum/job-offers";
            Document doc = getURLConnection(url);
            int forumPage = 2;
            while (lastYearTopics < 10) {
                traverseForumTable(doc, parseDateToStringFormat(this.prop.getProperty("lastUpdate")));
                doc = getURLConnection(String.format("%s/%s", url, forumPage++));
            }
            writeLastUpdateProperty();
        }
    }

    /**
     * Read app properties.
     */
    private void readAppProperties() {
        this.prop = new Properties();
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("app.properties")) {
            this.prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if it need to run program.
     *
     * @param lastUpdate    last update
     * @param runOncePerDay run once per day
     * @return true if need to run
     */
    boolean checkNeedsForRun(String lastUpdate, String runOncePerDay) {
        boolean result = true;
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        if (runOncePerDay.equals("true")
                & dateFormat.format(new Date()).compareTo(lastUpdate) == 0) {
            result = false;
            System.out.println("Program run once per day and already run today");
        }
        return result;
    }

    /**
     * Get connection to a URL.
     *
     * @param url URL to connect to
     * @return the connection
     */
    private Document getURLConnection(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            LOGGER.error("error - ", e);
        }
        return doc;
    }

    /**
     * Traverse forum table.
     *
     * @param doc      HTML Document
     * @param lastLoad last load
     */
    private void traverseForumTable(Document doc, Date lastLoad) {
        Elements table = doc.getElementsByClass("forumTable");
        for (Element tr : table.get(0).children().get(0).children()) {
            if (this.lastYearTopics < 10
                    && tr.getElementsByClass("postslisttopic").text().length() > 0) {
                Vacancy vacancy = createSuitableVacancy(tr, lastLoad);
                if (vacancy != null
                        && !managerDB.findEntry(vacancy.getTopicDate(),
                        vacancy.getTopic(), vacancy.getUrl())) {
                    managerDB.addEntry(vacancy);
                    LOGGER.info(String.format("(%s) %s - %s(%s)", vacancy.getTopicDate(),
                            vacancy.getTopic(), vacancy.getFullDescription(), vacancy.getUrl()));
                }
            }
        }
    }

    /**
     * Create vacancy, if it satisfy the condition.
     *
     * @param tr       row in forum table
     * @param lastLoad last load
     * @return new vacancy or null
     */
    Vacancy createSuitableVacancy(Element tr, Date lastLoad) {
        Vacancy vacancy = null;
        int currentYear = Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date()));
        this.topicYear = currentYear;
        String topic = tr.getElementsByClass("postslisttopic").text().toLowerCase();
        if (!topic.equals("ответов")
                && !topic.substring(0, 6).equals("важно:")
                && !topic.contains("[закрыт]")
                && (topic.contains("java") | topic.contains("jee") | topic.contains("j2ee"))
                & (!topic.contains("java script") & !topic.contains("javascript"))) {
            String url = tr.getElementsByClass("postslisttopic")
                    .select("a").attr("href");
            Document doc = getURLConnection(url);
            String fullDescription = doc
                    .getElementsByClass("msgBody").get(1)
                    .text().replaceAll("(\r\n|\n)", "<br />");
            String topicDate = formatDateToStringYYYYMMDDhhmm(doc
                    .getElementsByClass("msgFooter").get(0).text());
            if (this.topicYear < currentYear) {
                this.lastYearTopics++;
            } else if (lastLoad == null || lastLoad.compareTo(formatStringToDate(topicDate)) < 0) {
                vacancy = new Vacancy(topicDate, topic, fullDescription, url);
                this.lastYearTopics = 0;
            }
        }
        return vacancy;
    }

    /**
     * Format date.
     *
     * @param fullDate full date
     * @return YYYY.MM.DD hh:mm representation of date
     */
    String formatDateToStringYYYYMMDDhhmm(String fullDate) {
        String result = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String[] splitDate = fullDate.split(" ");
        if (fullDate.contains("сегодня")) {
            result = String.format("%s %s", dateFormat.format(new Date()), splitDate[1].substring(0, 5));
        } else if (fullDate.contains("вчера")) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            result = String.format("%s %s", dateFormat.format(cal.getTime()), splitDate[1].substring(0, 5));
        } else {
            this.topicYear = Integer.valueOf(String.format("20%s", splitDate[2].substring(0, 2)));
            result = String.format("20%s.%s.%02d %s", splitDate[2].substring(0, 2),
                    getDigitMonthRepresentation(splitDate[1]), Integer.valueOf(splitDate[0]), splitDate[3].substring(0, 5));
        }
        return result;
    }

    /**
     * Formatting string to date.
     *
     * @param topicDate string yyyy.MM.dd hh:mm
     * @return date
     */
    private Date formatStringToDate(String topicDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm");
        Date date = null;
        try {
            date = dateFormat.parse(topicDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Get digit (MM) representation of month.
     *
     * @param month month
     * @return digit (MM) representation
     */
    String getDigitMonthRepresentation(String month) {
        String result = "";
        switch (month) {
            case "янв":
                result = "01";
                break;
            case "фев":
                result = "02";
                break;
            case "мар":
                result = "03";
                break;
            case "апр":
                result = "04";
                break;
            case "май":
                result = "05";
                break;
            case "июн":
                result = "06";
                break;
            case "июл":
                result = "07";
                break;
            case "авг":
                result = "08";
                break;
            case "сен":
                result = "09";
                break;
            case "окт":
                result = "10";
                break;
            case "ноя":
                result = "11";
                break;
            case "дек":
                result = "12";
                break;
            default:
                result = null;
        }
        return result;
    }

    /**
     * Get date of last load information.
     *
     * @param lastUpdate last update
     * @return date
     */
    Date parseDateToStringFormat(String lastUpdate) {
        Date date = null;
        if (lastUpdate.length() > 0) {
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm");
                date = dateFormat.parse(lastUpdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    /**
     * Set current date to property key 'lastUpdate'.
     */
    private void writeLastUpdateProperty() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm");
        this.prop.setProperty("lastUpdate", dateFormat.format(new Date()));
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("app.properties").getPath().replaceAll("%20", " "));
        try (OutputStream out = new FileOutputStream(file)) {
            this.prop.store(out, "properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get manager.
     *
     * @return manager.
     */
    ManagerDB getManagerDB() {
        return this.managerDB;
    }
}