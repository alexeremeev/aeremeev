package ru.job4j.control.repository;

import ru.job4j.control.dao.PSQLpool;
import ru.job4j.control.dao.RoleDAO;
import ru.job4j.control.dao.UserDAO;
import ru.job4j.control.models.Role;
import ru.job4j.control.models.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * class RoleRepository - репозиторий для модели Role.
 */
public class RoleRepository {

    private PSQLpool pool = PSQLpool.getInstance();
    private RoleDAO roleDAO = pool.getRoleDAO();
    private UserDAO userDAO = pool.getUserDAO();

    /**
     * Собрать всех User по ролям Role.
     * @return список всех пользователей, сортированный по Role.
     */
    public List<User> getAllRoleReferences() {
        List<User> result = new CopyOnWriteArrayList<>();
        List<User> users = userDAO.getAll();
        List<Role> roles = roleDAO.getAll();
        for (Role role: roles) {
            for (User user: users) {
                if (role.getId() == user.getRoleId()) {
                    result.add(user);
                }
            }
        }
        return result;
    }
}
