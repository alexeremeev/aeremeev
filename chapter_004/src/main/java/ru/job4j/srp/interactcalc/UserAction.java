package ru.job4j.srp.interactcalc;
/**
 * Interface UserAction - menu action.
 * @author aeremeev
 * @since 07.01.2018
 * @version 1
 */
public interface UserAction {

    /**
     * Execute menu item.
     */
    void execute();

    /**
     * Menu item info.
     * @return menu item info.
     */
    String info();

}
