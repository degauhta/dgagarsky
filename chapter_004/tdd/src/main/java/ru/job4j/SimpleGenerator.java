package ru.job4j;

import java.util.HashMap;
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
     * Pattern.
     */
    private static final Pattern PATTERN = Pattern.compile("\\$\\{(.+?)}");

    /**
     * Hello world, ${name}.
     *
     * @param template template.
     * @param data     data.
     * @return generated template.
     */
    @Override
    public String generate(String template, Map<String, String> data) {
        Matcher matcher = PATTERN.matcher(template);
        StringBuffer sb = new StringBuffer(template.length());
        String value;
        String key;
        Map<String, String> delete = new HashMap<>();
        while (matcher.find()) {
            key = matcher.group(1);
            value = data.get(key);
            if (value == null) {
                throw new TemplateException("There are no such key in data.");
            } else {
                matcher.appendReplacement(sb, Matcher.quoteReplacement(value));
                delete.put(key, value);
            }
        }
        matcher.appendTail(sb);
        if (data.size() != delete.size()) {
            throw new TemplateException("Not all keys have been used.");
        }
        return sb.toString();
    }
}
