package ru.job4j.control.repository;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.control.dao.PSQLpool;
import ru.job4j.control.models.User;

import java.util.List;

public class RoleRepositoryTest {

    private PSQLpool pool = PSQLpool.getInstance();

    @Before
    public void clear() {
        pool.getRoleDAO().fillRoles();
        pool.getMusicTypeDAO().fillMusicType();
        UserRepository userRepository = new UserRepository();
        userRepository.createReference("admin", "admin", "Default city", "admin", "Pop");
        userRepository.createReference("user", "user", "Default city", "user", "Rock");
    }

    @Test
    public void test() {
        RoleRepository repository = new RoleRepository();
        List<User> result = repository.getAllRoleReferences();

        for (User user : result) {
            System.out.println(user);
        }
    }

}