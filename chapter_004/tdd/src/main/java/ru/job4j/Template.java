package ru.job4j;

import java.util.Map;

/**
 * Template class.
 *
 * @author Denis
 * @since 18.02.2017
 */
public interface Template {
    /**
     * Hello world, ${name}.
     *
     * @param template template.
     * @param data data.
     * @return generated template.
     */
    String generate(String template, Map<String, String> data);
}
