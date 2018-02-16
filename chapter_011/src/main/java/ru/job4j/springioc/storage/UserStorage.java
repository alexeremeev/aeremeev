package ru.job4j.springioc.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.springioc.model.User;

/**
 * Userstorage.
 * @author aeremeev.
 * @version 1
 * @since 15.02.2018
 */
@Component
public class UserStorage {

    private final Storage storage;

    @Autowired
    public UserStorage(Storage storage) {
        this.storage = storage;
    }

    public void add(User user) {
        this.storage.add(user);
    }
}
