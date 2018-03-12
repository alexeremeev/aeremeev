package ru.job4j.service;

import ru.job4j.domain.User;

public interface UserService {
    boolean registerNewUser(String username);
    User findByUsername(String username);
}
