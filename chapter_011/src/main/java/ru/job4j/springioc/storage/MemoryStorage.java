package ru.job4j.springioc.storage;

import org.springframework.stereotype.Component;
import ru.job4j.springioc.model.User;

/**
 * Memory user storage.
 * @author aeremeev.
 * @version 1
 * @since 15.02.2018
 */
@Component
public class MemoryStorage implements Storage {

    @Override
    public void add(User user) {
        System.out.println(String.format("New user added: %s", user.toString()));
    }
}
