package ru.job4j.register.model;

import ru.job4j.register.Register;

/**
 * BasicAction.
 * @author aeremeev.
 * @version 1
 * @since 31.01.2018
 */
public interface UserAction {

    /**
     * Execute menu item.
     * @param register register.
     */
    void execute(Register register);
    /**
     * Item info.
     * @return item info.
     */
    String info();
}
