package ru.job4j.register.model;

/**
 * BasicAction.
 * @author aeremeev.
 * @version 1
 * @since 31.01.2018
 */
public abstract class BasicAction implements UserAction {

    /**
     * Item description.
     */
    private String description;

    /**
     * Default constructor.
     * @param description item description.
     */
    public BasicAction(String description) {
        this.description = description;
    }

    /**
     * Item information.
     * @return this item description.
     */
    public String info() {
        return this.description;
    }


}
