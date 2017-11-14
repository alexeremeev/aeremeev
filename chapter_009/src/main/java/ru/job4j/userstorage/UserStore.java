package ru.job4j.userstorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * class UserStore - хранилище User, взаимодействующее с SQL БД.
 */
public class UserStore {
    /**
     * Userstore instance.
     */
    private static final UserStore INSTANCE = new UserStore();

    /**
     * Конструктор.
     */
    private UserStore() {
    }

    /**
     * Геттер instance.
     * @return instance.
     */
    public static UserStore getInstance() {
        return INSTANCE;
    }

    /**
     * Connection.
     */
    private Connection connection;

    /**
     * Загрузчик settings.
     */
    private Settings settings = new Settings("userstore.properties");
    /**
     * DB url.
     */
    private String url = this.settings.getSettings("DB_url");
    /**
     * DB username.
     */
    private String username = this.settings.getSettings("DB_username");
    /**
     * DB password.
     */
    private String password = this.settings.getSettings("DB_password");

    /**
     * Добавить пользователя.
     * @param user пользователь.
     * @return пользователь.
     */
    public User addUser(User user) {
        this.checkTable();
        String query = this.settings.getSettings("SQL_ADD_USER");
        Object[] fields = new Object[] {user.getName(), user.getLogin(), user.getEmail(), new Timestamp(user.getCreateDate())};
        this.executeWithArgs(query, fields);
        return user;
    }

    /**
     * Возвращает список всех пользователей.
     * @return список всех пользователей.
     */
    public List<User> getUsers() {
        this.checkTable();
        String query = this.settings.getSettings("SQL_FIND_ALL");
        Object[] fields = new Object[] {0};
        List<User> users = this.getUsers(query, fields);
        return users;
    }

    /**
     * Изменить данные пользователя.
     * @param user пользователь.
     */
    public void updateUser(User user) {
        this.checkTable();
        String query = this.settings.getSettings("SQL_UPDATE_USER");
        Object[] fields = new Object[] {user.getName(), user.getEmail(), user.getLogin()};
        this.executeWithArgs(query, fields);

    }

    /**
     * Удалить пользователя.
     * @param user пользователь.
     */
    public void deleteUser(User user) {
        this.checkTable();
        String query = this.settings.getSettings("SQL_DELETE_USER");
        Object[] fields = new Object[] {user.getLogin()};
        this.executeWithArgs(query, fields);
    }

    /**
     * Найти пользователя по логину.
     * @param login логин.
     * @return пользователь.
     */
    public User findByLogin(String login) {
        this.checkTable();
        String query = this.settings.getSettings("SQL_FIND_BY_LOGIN");
        Object[] fields = new Object[] {login};
        List<User> result = this.getUsers(query, fields);
        if (!result.isEmpty()) {
            return result.get(0);
        } else {
            return null;
        }
    }

    /**
     * Проверка существования таблицы USERS в БД.
     * Если отсутствует, то создается новая.
     */
    public void checkTable() {
        if (this.select(this.settings.getSettings("SQL_CHECK_TABLE")) == 0) {
            this.execute(this.settings.getSettings("SQL_CREATE_TABLE"));
        }
    }

    public void clearTable() {
        String query = this.settings.getSettings("SQL_CLEAR_TABLE");
        this.execute(query);
    }
    /**
     * Установление соединения с БД.
     * @param url url.
     * @param username username.
     * @param password password.
     */
    private void setConnection(String url, String username, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Закрывает соединение с базой данных.
     */
    private void endConnection() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }
    /**
     * Выполняет запросы SELECT в БД.
     * @param query sql запрос.
     * @return значение первой колонки.
     */
    private int select(String query) {
        this.setConnection(this.url, this.username, this.password);
        int result = 0;

        Statement st = null;
        ResultSet rs = null;
        try {
            st = this.connection.createStatement();
            rs = st.executeQuery(query);

            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            this.closeResult(rs);
            this.closeStatement(st);
        }
        this.endConnection();
        return result;
    }
    /**
     * Исполняет запросы Create к SQL БД.
     * @param query запрос.
     * @return количество обновленных строк.
     */
    private int execute(String query) {
        this.setConnection(this.url, this.username, this.password);
        int result = 0;
        Statement st = null;

        try {
            st = this.connection.createStatement();
            result = st.executeUpdate(query);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            this.closeStatement(st);
        }
        this.endConnection();
        return result;
    }
    /**
     * Исполняет запросы к SQL БД типа Insert, Update, Delete с полями ?.
     * @param query запрос.
     * @param fields поля ? для PreparedStatement.
     * @return количество обновленных строк.
     */
    private int executeWithArgs(String query, Object[] fields) {
        this.setConnection(this.url, this.username, this.password);
        int result = 0;
        PreparedStatement ps = null;
        try {
            this.connection.setAutoCommit(false);
            ps = this.connection.prepareStatement(query);
            for (int i = 0; i < fields.length; i++) {
                ps.setObject(i + 1, fields[i]);
            }
            result = ps.executeUpdate();
            this.connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            try {
                this.connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            this.closeStatement(ps);
        }
        this.endConnection();
        return result;
    }
    /**
     * Возвращает список User, ограниченный полем fields.
     * @param query запрос.
     * @param fields поля ? для PreparedStatement.
     * @return запрошенный список.
     */
    private List<User> getUsers(String query, Object[] fields) {
        this.setConnection(this.url, this.username, this.password);
        List<User> users = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = this.connection.prepareStatement(query);
            ps.setObject(1, fields[0]);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5).getTime());
                users.add(user);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        this.endConnection();
        return users;
    }

    /**
     * Закрытие ResultSet.
     * @param rs ResultSet.
     */
    private void closeResult(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }
    /**
     * Закрытие Statement.
     * @param st Statement.
     */
    private void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }
}

