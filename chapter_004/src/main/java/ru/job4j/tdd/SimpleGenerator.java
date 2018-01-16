package ru.job4j.tdd;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SimpleGenerator - string substitute interface.
 * @author aeremeev
 * @since 16.01.2018
 * @version 1
 */
public class SimpleGenerator implements Template {
    /**
     * Key regex.
     */
    private final String regex = "(\\$\\{)(\\w+)(\\})";
    /**
     * Key pattern.
     */
    private final Pattern pattern = Pattern.compile(regex);

    /**
     * Generate new string from template and substitute keys map.
     * @param template template string.
     * @param substitutesMap substitute keys map
     * @return string with substitutes.
     */
    @Override
    public String generate(String template, Map<String, String> substitutesMap) {
        if (template == null) {
            throw new TemplateException("String template must be not null");
        }

        StringBuffer buffer = new StringBuffer();
        Matcher matcher = pattern.matcher(template);

        while (matcher.find()) {
            String key = this.getKey(matcher.group());
            if (substitutesMap.containsKey(key)) {
                matcher.appendReplacement(buffer, substitutesMap.get(key));
            } else {
                throw new TemplateException(String.format("Key %s not found in substitutes map", key));
            }
        }
        matcher.appendTail(buffer);

        for (String key: substitutesMap.keySet()) {
            if (!buffer.toString().contains(substitutesMap.get(key))) {
                throw new TemplateException(String.format("Unused key %s in substitutes map", key));
            }
        }

        return buffer.toString();
    }

    /**
     * Get key from ${...}.
     * @param key key.
     * @return key.
     */
    public String getKey(String key) {
        return key.substring(2, key.length() - 1);
    }
}
