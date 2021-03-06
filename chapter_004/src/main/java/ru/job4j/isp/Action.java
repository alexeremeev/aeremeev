package ru.job4j.isp;

/**
 * Action - execute item.
 * @author aeremeev.
 * @version 1.1
 * @since 13.01.2018
 */
public interface Action {
    /**
     * Execute item.
     * @param key item key.
     * @return true, is executed.
     */
    boolean execute(String key);
}
