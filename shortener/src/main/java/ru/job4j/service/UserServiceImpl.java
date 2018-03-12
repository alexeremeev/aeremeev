package ru.job4j.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.domain.User;
import ru.job4j.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean registerNewUser(String username) {
        boolean result = false;
        User newUser = this.repository.findByUsername(username);
        if (newUser == null) {
            newUser = new User();
            newUser.setUsername(username);
            int length = 8;
            String password = RandomStringUtils.random(length, true, true);
            newUser.setPassword(password);
            this.repository.save(newUser);
            result = true;
        }
        return result;
    }

    @Override
    public User findByUsername(String username) {
        return this.repository.findByUsername(username);
    }
}
