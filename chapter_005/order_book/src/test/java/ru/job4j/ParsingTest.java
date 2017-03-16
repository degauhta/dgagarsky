package ru.job4j;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * ParsingTest class.
 *
 * @author Denis
 * @since 14.03.2017
 */
public class ParsingTest {
    /**
     * Test.
     */
    @Test
    public void test() {
        String pathname = "C:\\1.txt";
        try (PrintStream writer = new PrintStream(new File(pathname))) {
            writer.println("<AddOrder book=\"book-1\" operation=\"BUY\" price=\"100.10\" volume=\"40\" orderId=\"141\" />");
            writer.println("<AddOrder book=\"book-1\" operation=\"BUY\" price=\"101.20\" volume=\"40\" orderId=\"142\" />");
            writer.println("<AddOrder book=\"book-1\" operation=\"SELL\" price=\" 99.50\" volume=\"12\" orderId=\"143\" />");
            writer.println("<AddOrder book=\"book-1\" operation=\"BUY\" price=\"100.00\" volume=\"96\" orderId=\"148\" />");
            writer.println("<AddOrder book=\"book-1\" operation=\"SELL\" price=\"100.80\" volume=\"24\" orderId=\"149\" />");
            writer.println("<AddOrder book=\"book-1\" operation=\"SELL\" price=\"100.30\" volume=\"57\" orderId=\"150\" />");
            writer.println("<AddOrder book=\"book-1\" operation=\"BUY\" price=\"100.20\" volume=\"42\" orderId=\"151\" />");
            writer.println("<DeleteOrder book=\"book-1\" orderId=\"141\" />");
            writer.println("<AddOrder book=\"book-1\" operation=\"BUY\" price=\" 99.60\" volume=\"80\" orderId=\"152\" />");
            writer.println("<AddOrder book=\"book-1\" operation=\"SELL\" price=\" 99.90\" volume=\"92\" orderId=\"153\" />");
            writer.print("<AddOrder book=\"book-1\" operation=\"BUY\" price=\" 100.00\" volume=\"50\" orderId=\"154\" />");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Parsing parsing = new Parsing();
        parsing.start(pathname);
        List<String> texts = Lists.newArrayList("", "Order book: book-1",
                "BID                  ASK", "Volume@Price – Volume@Price",
                "124@100.0 - 57@100.3", "80@99.6 - 24@100.8", "");
        String expected = Joiner.on(System.lineSeparator()).join(texts);

        assertThat(outputStream.toString(), is(expected));
    }
}