package ru.job4j.isp;

import java.util.ArrayList;
import java.util.List;

/**
 * Item.
 * @author aeremeev.
 * @version 1.1
 * @since 13.01.2018
 */
public class Item implements Action, Information {
    /**
     * Item key.
     */
    private String key;

    /**
     * Item description.
     */
    private String description;
    /**
     * Sub items list.
     */
    private List<Item> subItems;

    /**
     * Constructor.
     * @param key item key.
     * @param description item description.
     */
    public Item(String key, String description) {
        this.key = key;
        this.description = description;
        this.subItems = new ArrayList<>();
    }

    /**
     * Add sub item to item.
     * @param item sub item.
     */
    public void addSubItem(Item item) {
        this.subItems.add(item);
    }

    /**
     * Key getter.
     * @return key.
     */
    public String getKey() {
        return key;
    }

    @Override
    public boolean execute(String key) {
        boolean result = false;
        if (this.key.equals(key)) {
            result = true;
            System.out.println("Executed item " + this.getKey());
        } else if (!this.subItems.isEmpty()) {
            for (Item subItem: subItems) {
                result = subItem.execute(key);
                if (result) {
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public String info() {
        StringBuilder builder = new StringBuilder();
        int spacerLength = this.getKey().length();
        for (int index = 0; index != spacerLength; index++) {
            builder.append("-");
        }
        builder.append(String.format("%s %s%s", this.key, this.description, System.getProperty("line.separator")));
        if (!this.subItems.isEmpty()) {
            for (Item subItem: subItems) {
                builder.append(subItem.info());
            }
        }
        return builder.toString();
    }
}
