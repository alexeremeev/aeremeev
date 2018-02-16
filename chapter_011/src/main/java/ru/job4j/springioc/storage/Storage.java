package ru.job4j.springioc.storage;

import ru.job4j.springioc.model.User;
/**
 * User storage interface.
 * @author aeremeev.
 * @version 1
 * @since 15.02.2018
 */
public interface Storage {
    /**
     * Add new user to storage.
     * @param user user.
     */
    void add(User user);
}
