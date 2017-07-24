package ru.job4j;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        if (checkNeedsForRun()) {
            this.managerDB = new ManagerDB();
            String url = "http://www.sql.ru/forum/job-offers";
            Document doc = getURLConnection(url);
            int forumPage = 2;
            while (lastYearTopics < 10) {
                traverseForumTable(doc);
                doc = getURLConnection(String.format("%s/%s", url, forumPage++));
            }
            writeLastUpdateProperty();
        }
    }

    /**
     * Check if it need to run program.
     *
     * @return true if need to run
     */
    private boolean checkNeedsForRun() {
        boolean result = true;
        readAppProperties();
        String lastUpdate = prop.getProperty("lastUpdate");
        String runOncePerDay = prop.getProperty("runOncePerDay");
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
     * @param doc HTML Document
     */
    private void traverseForumTable(Document doc) {
        Elements table = doc.getElementsByClass("forumTable");
        for (Element tr : table.get(0).children().get(0).children()) {
            if (this.lastYearTopics < 10
                    && tr.getElementsByClass("postslisttopic").text().length() > 0) {
                logSuitableVacancy(tr);
            }
        }
    }

    /**
     * Log suitable vacancy.
     *
     * @param tr row in forum table
     */
    private void logSuitableVacancy(Element tr) {
        String url;
        String fullDescription;
        String topicDate;
        Date lastLoad = getDateOfLastLoadInformation();
        int currentYear = Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date()));
        this.topicYear = currentYear;
        String topic = tr.getElementsByClass("postslisttopic").text().toLowerCase();
        if (!topic.equals("ответов")
                && !topic.substring(0, 6).equals("важно:")
                && !topic.contains("[закрыт]")
                && (topic.contains("java") | topic.contains("jee") | topic.contains("j2ee")
                & !topic.contains("java script") & !topic.contains("javascript"))) {
            url = tr.getElementsByClass("postslisttopic").select("a").attr("href");
            Document doc = getURLConnection(url);
            fullDescription = doc.getElementsByClass("msgBody").get(1).text();
            fullDescription = fullDescription.replace("\r", " ");
            fullDescription = fullDescription.replace("\n", " ");
            topicDate = formatDateToStringYYYYMMDDhhmm(doc.getElementsByClass("msgFooter").get(0).text());
            if (this.topicYear < currentYear) {
                this.lastYearTopics++;
            } else if (lastLoad == null || lastLoad.compareTo(formatStringToDate(topicDate)) < 0) {
                if (managerDB.addEntry(topicDate, topic, fullDescription, url)) {
                    LOGGER.info(String.format("(%s) %s - %s(%s)", topicDate, topic, fullDescription, url));
                }
                this.lastYearTopics = 0;
            }
        }
    }

    /**
     * Format date.
     *
     * @param fullDate full date
     * @return YYYY.MM.DD hh:mm representation of date
     */
    private String formatDateToStringYYYYMMDDhhmm(String fullDate) {
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
    private String getDigitMonthRepresentation(String month) {
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
     * Get date of last load information.
     *
     * @return date
     */
    private Date getDateOfLastLoadInformation() {
        Date date = null;
        String lastUpdate = this.prop.getProperty("lastUpdate");
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
        try (OutputStream out = new FileOutputStream(classLoader.getResource("app.properties").getFile())) {
            this.prop.store(out, "properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}