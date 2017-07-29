package ru.job4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * HTMLParserTest class.
 *
 * @author Denis
 * @since 26.07.2017
 */
public class HTMLParserTest {
    /**
     * Test needs to run.
     */
    @Test
    public void whenLastUpdateInThePastThenReturnTrue() {
        HTMLParser htmlParser = new HTMLParser();
        String lastUpdate = "2000.01.01";
        assertThat(htmlParser.checkNeedsForRun(lastUpdate, "false"), is(true));
    }

    /**
     * Test needs to run.
     */
    @Test
    public void whenRunMoreThenOncePerDayThenReturnTrue() {
        HTMLParser htmlParser = new HTMLParser();
        String lastUpdate = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
        assertThat(htmlParser.checkNeedsForRun(lastUpdate, "false"), is(true));
    }

    /**
     * Test needs to run.
     */
    @Test
    public void whenRunOncePerDayThenReturnFalse() {
        HTMLParser htmlParser = new HTMLParser();
        String lastUpdate = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
        assertThat(htmlParser.checkNeedsForRun(lastUpdate, "true"), is(false));
    }

    /**
     * Test digit month.
     */
    @Test
    public void whenStringIsMonthThenReturnDigitRepresentation() {
        HTMLParser htmlParser = new HTMLParser();
        assertThat(htmlParser.getDigitMonthRepresentation("янв"), is("01"));
        assertThat(htmlParser.getDigitMonthRepresentation("фев"), is("02"));
        assertThat(htmlParser.getDigitMonthRepresentation("мар"), is("03"));
        assertThat(htmlParser.getDigitMonthRepresentation("апр"), is("04"));
        assertThat(htmlParser.getDigitMonthRepresentation("май"), is("05"));
        assertThat(htmlParser.getDigitMonthRepresentation("июн"), is("06"));
        assertThat(htmlParser.getDigitMonthRepresentation("июл"), is("07"));
        assertThat(htmlParser.getDigitMonthRepresentation("авг"), is("08"));
        assertThat(htmlParser.getDigitMonthRepresentation("сен"), is("09"));
        assertThat(htmlParser.getDigitMonthRepresentation("окт"), is("10"));
        assertThat(htmlParser.getDigitMonthRepresentation("ноя"), is("11"));
        assertThat(htmlParser.getDigitMonthRepresentation("дек"), is("12"));
        assertThat(htmlParser.getDigitMonthRepresentation(""), is(nullValue()));
    }

    /**
     * Test date parsing.
     */
    @Test
    public void whenStringHasRightDateFormatThenReturnDate() {
        HTMLParser htmlParser = new HTMLParser();
        Calendar calendar = new GregorianCalendar(2017, 6, 25, 21, 0, 0);
        assertThat(htmlParser.parseDateToStringFormat("2017.07.25 21:00"), is(calendar.getTime()));
        assertThat(htmlParser.parseDateToStringFormat(""), is(nullValue()));
    }

    /**
     * Test date format.
     */
    @Test
    public void whenSendCustomStringThenReturnDateStringRepresentation() {
        HTMLParser htmlParser = new HTMLParser();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String expectedToday = String.format("%s 09:45", dateFormat.format(new Date()));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String expectedYesterday = String.format("%s 09:45", dateFormat.format(cal.getTime()));
        assertThat(htmlParser.formatDateToStringYYYYMMDDhhmm("сегодня, 09:45"), is(expectedToday));
        assertThat(htmlParser.formatDateToStringYYYYMMDDhhmm("вчера, 09:45"), is(expectedYesterday));
        assertThat(htmlParser.formatDateToStringYYYYMMDDhhmm("24 июл 17, 09:45"), is("2017.07.24 09:45"));
    }

    /**
     * Test vacancy creation.
     */
    @Test
    public void whenFitConditionsThenCreateNewVacancy() {
        HTMLParser htmlParser = new HTMLParser();
        Calendar lastUpdate = new GregorianCalendar(2000, 1, 1, 1, 1, 1);
        String html = "<html><head><title>First parse</title></head><body>"
                + "<table class=\"forumTable\">"
                + "<tr>"
                + "<td class=\"postslisttopic\"> "
                + "<a href=\"http://www.sql.ru/forum/1266579/razrabotchiki-php-150\">Разработчик Java Ухта</a>"
                + "</td >"
                + "/forumTable"
                + "</tr></body></html>";
        Document doc = Jsoup.parse(html);
        Elements table = doc.getElementsByClass("forumTable");
        Element tr = table.get(0).children().get(0).children().get(0);

        Vacancy vacancy = htmlParser.createSuitableVacancy(tr, lastUpdate.getTime());
        assertThat(vacancy.getTopicDate(), is("2017.07.20 16:31"));
        assertThat(vacancy.getTopic(), is("разработчик java ухта"));
        assertThat(vacancy.getTopic(), is("разработчик java ухта"));
        assertThat(vacancy.getUrl(), is("http://www.sql.ru/forum/1266579/razrabotchiki-php-150"));
    }

    /**
     * Test vacancy creation.
     */
    @Test
    public void whenNotFitConditionsThenReturnNull() {
        HTMLParser htmlParser = new HTMLParser();
        Calendar lastUpdate = new GregorianCalendar(2000, 1, 1, 1, 1, 1);
        String html = "<html><head><title>First parse</title></head><body>"
                + "<table class=\"forumTable\">"
                + "<tr>"
                + "<td class=\"postslisttopic\"> "
                + "<a href=\"http://www.sql.ru/forum/1230000/sistemnyy-analitik-dbo-moskva\">Разработчик Java Ухта</a>"
                + "</td >"
                + "/forumTable"
                + "</tr></body></html>";
        Document doc = Jsoup.parse(html);
        Elements table = doc.getElementsByClass("forumTable");
        Element tr = table.get(0).children().get(0).children().get(0);

        Vacancy vacancy = htmlParser.createSuitableVacancy(tr, lastUpdate.getTime());
        assertThat(vacancy, is(nullValue()));

        html = html.replace("Разработчик Java Ухта", "ответов");
        doc = Jsoup.parse(html);
        table = doc.getElementsByClass("forumTable");
        tr = table.get(0).children().get(0).children().get(0);

        html = html.replace("ответов", "важно:");
        doc = Jsoup.parse(html);
        table = doc.getElementsByClass("forumTable");
        tr = table.get(0).children().get(0).children().get(0);

        vacancy = htmlParser.createSuitableVacancy(tr, lastUpdate.getTime());
        assertThat(vacancy, is(nullValue()));

        html = html.replace("важно:", "[закрыт]");
        doc = Jsoup.parse(html);
        table = doc.getElementsByClass("forumTable");
        tr = table.get(0).children().get(0).children().get(0);

        vacancy = htmlParser.createSuitableVacancy(tr, lastUpdate.getTime());
        assertThat(vacancy, is(nullValue()));

        html = html.replace("[закрыт]", "java script");
        doc = Jsoup.parse(html);
        table = doc.getElementsByClass("forumTable");
        tr = table.get(0).children().get(0).children().get(0);

        vacancy = htmlParser.createSuitableVacancy(tr, lastUpdate.getTime());
        assertThat(vacancy, is(nullValue()));
    }
}