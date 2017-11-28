package ru.job4j.servlet.controller;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.servlet.UserStorage;
import ru.job4j.servlet.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Тесты сервлетов для работы с моделью Role.
 */
public class RoleServletTest {

    private final UserStorage users = UserStorage.getInstance();

    /**
     * Изначальный сброс таблиц.
     */
    @Before
    public void clear() {
        users.clearTable();
        users.checkTable();
    }

    /**
     * Тест добавления роли.
     * @throws ServletException ServletException.
     * @throws IOException IOException.
     */
    @Test
    public void whenAddNewRoleThenGetRole() throws ServletException, IOException {
        CreateRoleServlet servlet = new CreateRoleServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("name")).thenReturn("test");
        when(request.getParameter("admin")).thenReturn("admin");
        servlet.doPost(request, response);

        List<Role> list = users.getRoles();

        assertThat(list.get(2).getName(), is("test"));
    }

    /**
     * Тест редактирования роли.
     * @throws ServletException ServletException.
     * @throws IOException IOException.
     */
    @Test
    public void whenUpdateRoleThenGetUpdatedRole() throws ServletException, IOException {
        UpdateRoleServlet servlet = new UpdateRoleServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("name")).thenReturn("updated");
        when(request.getParameter("admin")).thenReturn("admin");
        when(request.getParameter("id")).thenReturn("1");
        servlet.doPost(request, response);

        List<Role> list = users.getRoles();

        assertThat(list.get(0).getName(), is("updated"));
    }

    /**
     * Тест удаления роли.
     * @throws ServletException ServletException.
     * @throws IOException IOException.
     */
    @Test
    public void whenDeleteRoleThenGetSecondRole() throws ServletException, IOException {
        DeleteRoleServlet servlet = new DeleteRoleServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("id")).thenReturn("2");
        servlet.doGet(request, response);

        List<Role> list = users.getRoles();

        assertThat(list.get(0).getName(), is("admin"));
    }

}