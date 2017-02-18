package ru.job4j;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * SimpleGeneratorTest class.
 *
 * @author Denis
 * @since 18.02.2017
 */
public class SimpleGeneratorTest {
    /**
     * Different keys.
     */
    @Test
    public void whenDifferentKeysUsedThenReturnTemplate() {
        Template template = new SimpleGenerator();
        String text = "I am a ${name}. Who are ${subject}?";
        Map<String, String> data = new HashMap<>();
        data.put("name", "Denis");
        data.put("subject", "you");
        String expected = "I am a Denis. Who are you?";
        String actual = template.generate(text, data);
        assertThat(actual, is(expected));
    }

    /**
     * One repeated key.
     */
    @Test
    public void whenOneRepeatedKeyThenReturnTemplate() {
        Template template = new SimpleGenerator();
        String text = "Help, ${sos}, ${sos}, ${sos}";
        Map<String, String> data = new HashMap<>();
        data.put("sos", "Aaaa");
        String expected = "Help, Aaaa, Aaaa, Aaaa";
        String actual = template.generate(text, data);
        assertThat(actual, is(expected));
    }

    /**
     * Extra keys.
     */
    @Test (expected = TemplateException.class)
    public void whenExtraKeysThenTemplateException() {
        Template template = new SimpleGenerator();
        String text = "Help, ${sos}, ${sos}, ${sos}";
        Map<String, String> data = new HashMap<>();
        data.put("sos", "Aaaa");
        data.put("extra", "qwerty");
        template.generate(text, data);
    }

    /**
     * Extra tag in text.
     */
    @Test (expected = TemplateException.class)
    public void whenExtraTagInTextThenTemplateException() {
        Template template = new SimpleGenerator();
        String text = "Help, ${sos}, ${extra}, ${sos}";
        Map<String, String> data = new HashMap<>();
        data.put("sos", "Aaaa");
        template.generate(text, data);
    }
}