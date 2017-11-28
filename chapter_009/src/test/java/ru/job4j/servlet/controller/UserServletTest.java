package ru.job4j.servlet.controller;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.servlet.UserStorage;
import ru.job4j.servlet.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Тесты сервлетов для работы с моделью User.
 */
public class UserServletTest {

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
     * Тест добавления пользователя.
     * @throws ServletException ServletException.
     * @throws IOException IOException.
     */
    @Test
    public void whenAddNewUserThenGetUser() throws ServletException, IOException {
        CreateUserServlet servlet = new CreateUserServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("login")).thenReturn("user");
        when(request.getParameter("name")).thenReturn("test");
        when(request.getParameter("email")).thenReturn("user@user");
        when(request.getParameter("password")).thenReturn("user");
        when(request.getParameter("role")).thenReturn("2");
        when(request.getParameter("city")).thenReturn("1");
        servlet.doPost(request, response);
        List<User> list = users.getUsers();
        assertThat(list.get(1).getLogin(), is("user"));
    }

    /**
     * Тест редактирования пользователя.
     * @throws ServletException ServletException.
     * @throws IOException IOException.
     */
    @Test
    public void whenUpdateUserDataThenGetUpdatedUser() throws ServletException, IOException {
        UpdateUserServlet servlet = new UpdateUserServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("admin")).thenReturn(true);
        when(request.getParameter("name")).thenReturn("updated");
        when(request.getParameter("login")).thenReturn("admin");
        when(request.getParameter("email")).thenReturn("admin@admin");
        when(request.getParameter("password")).thenReturn("admin");
        when(request.getParameter("role")).thenReturn("1");
        when(request.getParameter("city")).thenReturn("1");
        servlet.doPost(request, response);
        List<User> list = users.getUsers();
        assertThat(list.get(0).getName(), is("updated"));
    }

    /**
     * Тест удаления пользователя.
     * @throws ServletException ServletException.
     * @throws IOException IOException.
     */
    @Test
    public void whenDeleteUserThenUserListIsEmpty() throws ServletException, IOException {
        DeleteUserServlet servlet = new DeleteUserServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("login")).thenReturn("admin");
        servlet.doGet(request, response);
        List<User> list = users.getUsers();

        assertThat(list.isEmpty(), is(true));

    }
}