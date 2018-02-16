package ru.job4j.springioc.storage;

import org.springframework.stereotype.Component;
import ru.job4j.springioc.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Memory user storage.
 * @author aeremeev.
 * @version 1.1
 * @since 15.02.2018
 */
@Component
public class MemoryStorage implements Storage {

    private List<User> users = new ArrayList<>();

    @Override
    public void add(User user) {
        this.users.add(user);
        System.out.println(String.format("New user added: %s", user.toString()));
    }

    @Override
    public List<User> getAll() {
        return this.users;
    }
}
