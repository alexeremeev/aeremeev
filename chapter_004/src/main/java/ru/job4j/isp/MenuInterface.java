package ru.job4j.isp;

/**
 * Menu interface.
 * @author aeremeev.
 * @version 1
 * @since 13.01.2018
 */
public interface MenuInterface {
    /**
     * Add item to menu.
     * @param item item.
     */
    void addItem(Item item);
    /**
     * View menu.
     */
    void view();
    /**
     * Select menu item.
     * @param key menu item key.
     */
    void select(String key);
}
