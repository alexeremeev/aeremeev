package ru.job4j.srp.interactcalc;
/**
 * BaseAction - abstract class of menu action.
 * @author aeremeev
 * @since 07.01.2018
 * @version 1
 */
public abstract class BaseAction implements UserAction {
    /**
     * Menu item description.
     */
    private String description;

    /**
     * Constructor.
     * @param description menu item description.
     */
    protected BaseAction(String description) {
         this.description = description;
        }

    /**
     * Menu item info.
     * @return menu item info.
     */
    public String info() {
      return this.description;
    }
}
