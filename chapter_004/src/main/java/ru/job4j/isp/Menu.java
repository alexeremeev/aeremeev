package ru.job4j.isp;

import java.util.*;
/**
 * Menu.
 * @author aeremeev.
 * @version 1
 * @since 13.01.2018
 */
public class Menu implements MenuInterface {
    /**
     * Item map.
     */
    private Map<String, Item> items;

    /**
     * Constructor. TreeMap for correct sorted menu view.
     */
    public Menu() {
        this.items = new TreeMap<>();
    }

    @Override
    public void addItem(Item item) {
        this.items.put(item.getKey(), item);
    }

    @Override
    public void view() {
        for (String string: items.keySet()) {
            System.out.println(items.get(string).info());
        }
    }

    @Override
    public void select(String key) {
        if (this.items.containsKey(key)) {
            this.items.get(key).execute();
        } else {
            System.out.println("Error, no such item in this menu");
        }
    }
}
