package ru.job4j.springmvc.repo;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.springmvc.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByLogin(String login);
}
