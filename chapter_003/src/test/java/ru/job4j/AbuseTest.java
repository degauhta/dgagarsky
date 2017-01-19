package ru.job4j;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * AbuseTest class.
 *
 * @author Denis
 * @since 18.01.2017
 */
public class AbuseTest {

    /**
     * Test abuse.
     * @throws UnsupportedEncodingException .
     */
    @Test
    public void whenAbuseWrightThenTrue() throws UnsupportedEncodingException {
        Abuse abuse = new Abuse();
        String inputString = "one two three two";
        InputStream in = new ByteArrayInputStream(inputString.getBytes("UTF-8"));
        OutputStream out = new ByteArrayOutputStream();
        String[] abuseStrings = new String[]{"one", "two"};
        abuse.dropAbuses(in, out, abuseStrings);
        assertThat(out.toString().equals("  three "), is(true));
    }

    /**
     * Test abuse wrong.
     * @throws UnsupportedEncodingException .
     */
    @Test
    public void whenAbuseWrightThenFalse() throws UnsupportedEncodingException {
        Abuse abuse = new Abuse();
        String inputString = "one two three two";
        InputStream in = new ByteArrayInputStream(inputString.getBytes("UTF-8"));
        OutputStream out = new ByteArrayOutputStream();
        String[] abuseStrings = new String[]{"one", "two"};
        abuse.dropAbuses(in, out, abuseStrings);
        assertThat(out.toString().equals("ree "), is(false));
    }

}