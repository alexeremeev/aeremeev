package ru.job4j.tdd;

import java.util.Map;

/**
 * Template - string substitute interface.
 * @author aeremeev
 * @since 16.01.2018
 * @version 1
 */
public interface Template {
    /**
     * Generate new string from template and substitute keys map.
     * @param template template string.
     * @param substitutesMap substitute keys map
     * @return string with substitutes.
     */
    String generate(String template, Map<String, String> substitutesMap);
}
