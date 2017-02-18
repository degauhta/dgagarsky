package ru.job4j;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SimpleGenerator class.
 *
 * @author Denis
 * @since 18.02.2017
 */
public class SimpleGenerator implements Template {
    /**
     * Hello world, ${name}.
     *
     * @param template template.
     * @param data     data.
     * @return generated template.
     */
    @Override
    public String generate(String template, Map<String, String> data) {
        Pattern pattern = Pattern.compile("\\$\\{(.+?)}");
        Matcher matcher = pattern.matcher(template);
        String result = template;
        String value;
        while (matcher.find()) {
            value = data.get(matcher.group(1));
            if (value == null) {
                throw new TemplateException("There are no such key in data.");
            } else {
                result = result.replaceAll(String.format("\\$\\{%s}", matcher.group(1)), value);
            }
            data.remove(matcher.group(1));
            matcher = pattern.matcher(result);
        }
        if (data.size() > 0) {
            throw new TemplateException("Not all keys have been used.");
        }
        System.out.println(result);
        return result;
    }
}
