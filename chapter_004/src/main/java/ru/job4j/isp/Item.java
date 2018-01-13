package ru.job4j.isp;
/**
 * Item.
 * @author aeremeev.
 * @version 1
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
     * Constructor.
     * @param key item key.
     * @param description item description.
     */
    public Item(String key, String description) {
        this.key = key;
        this.description = description;
    }

    /**
     * Key getter.
     * @return key.
     */
    public String getKey() {
        return key;
    }

    @Override
    public void execute() {
        System.out.println("Executed item " + this.getKey());
    }

    @Override
    public String info() {
        StringBuilder builder = new StringBuilder();
        int spacerLength = this.getKey().length();
        for (int index = 0; index != spacerLength; index++) {
            builder.append("-");
        }
        builder.append(String.format("%s %s", this.key, this.description));
        return builder.toString();
    }
}
