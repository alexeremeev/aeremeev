package ru.job4j.dao;

import ru.job4j.models.User;

import java.util.List;

/**
 * User model DAO.
 * @author aeremeev.
 * @version 1
 * @since 05.02.2018
 */
public class UserDAO extends GenericDAO {

    public User isCredential(String login, String password) {
        User exists = null;
        List<User> users = super.getAll(User.class);
        for (User user: users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                exists = user;
                break;
            }
        }
        return exists;
    }
}
