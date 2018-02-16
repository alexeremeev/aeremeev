package ru.job4j.springioc.storage;

import org.springframework.stereotype.Component;
import ru.job4j.springioc.dao.DAOInterface;
import ru.job4j.springioc.dao.GenericDAO;
import ru.job4j.springioc.model.User;

import java.util.List;

/**
 * JDBC based user storage.
 * @author aeremeev.
 * @version 1
 * @since 16.02.2018
 */
@Component
public class JdbcStorage implements Storage {

    private DAOInterface<User> dao = new GenericDAO<>();

    @Override
    public void add(User user) {
        dao.saveOrUpdate(user);
    }

    @Override
    public List<User> getAll() {
        return dao.getAll(User.class);
    }
}
