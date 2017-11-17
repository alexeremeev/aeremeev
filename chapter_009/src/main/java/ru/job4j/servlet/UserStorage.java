package ru.job4j.servlet;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * class UserStorage - хранилище User, взаимодействующее с SQL БД.
 */
public class UserStorage {
    /**
     * UserStorage instance.
     */
    private static final UserStorage INSTANCE = new UserStorage();
    /**
     * DataSource.
     */
    private BasicDataSource dataSource;
    /**
     * Конструктор.
     */
    private UserStorage() {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(this.settings.getSettings("DB_driver"));
        dataSource.setUsername(this.settings.getSettings("DB_username"));
        dataSource.setPassword(this.settings.getSettings("DB_password"));
        dataSource.setUrl(this.settings.getSettings("DB_url"));
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(20);
        dataSource.setMaxOpenPreparedStatements(180);
    }
    /**
     * Геттер instance.
     * @return instance.
     */
    public static UserStorage getInstance() {
        return INSTANCE;
    }

    /**
     * Установить соединиение с БД.
     * @return connection.
     */
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Загрузчик settings.
     */
    private Settings settings = new Settings("userstore.properties");

    /**
     * Добавить пользователя.
     * @param user пользователь.
     * @return true,если добавлен.
     */
    public boolean addUser(User user) {
        boolean result = false;
        this.checkTable();
        String query = this.settings.getSettings("SQL_ADD_USER");
        Object[] fields = new Object[] {user.getName(), user.getLogin(), user.getEmail(), new Timestamp(user.getCreateDate())};
        if (this.executeWithArgs(query, fields) > 0) {
            result = true;
        }
        return result;
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
     * @return true, если обновлен.
     */
    public boolean updateUser(User user) {
        boolean result = false;
        this.checkTable();
        String query = this.settings.getSettings("SQL_UPDATE_USER");
        Object[] fields = new Object[] {user.getName(), user.getEmail(), user.getLogin()};
        if (this.executeWithArgs(query, fields) > 0) {
            result = true;
        }
        return result;
    }

    /**
     * Удалить пользователя.
     * @param user пользователь.
     */
    public boolean deleteUser(User user) {
        boolean result = false;
        this.checkTable();
        String query = this.settings.getSettings("SQL_DELETE_USER");
        Object[] fields = new Object[] {user.getLogin()};
        if (this.executeWithArgs(query, fields) > 0) {
            result = true;
        }
        return result;
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

    /**
     * Очистить таблицу Users.
     */
    public void clearTable() {
        String query = this.settings.getSettings("SQL_CLEAR_TABLE");
        this.execute(query);
    }

    /**
     * Закрывает соединение с базой данных.
     */
    private void endConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
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
        Connection connection = null;
        int result = 0;

        Statement st = null;
        ResultSet rs = null;
        try {
            connection = UserStorage.getInstance().getConnection();
            st = connection.createStatement();
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
        this.endConnection(connection);
        return result;
    }
    /**
     * Исполняет запросы Create к SQL БД.
     * @param query запрос.
     * @return количество обновленных строк.
     */
    private int execute(String query) {
        Connection connection = null;
        int result = 0;
        Statement st = null;

        try {
            connection = UserStorage.getInstance().getConnection();
            st = connection.createStatement();
            result = st.executeUpdate(query);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            this.closeStatement(st);
        }
        this.endConnection(connection);
        return result;
    }
    /**
     * Исполняет запросы к SQL БД типа Insert, Update, Delete с полями ?.
     * @param query запрос.
     * @param fields поля ? для PreparedStatement.
     * @return количество обновленных строк.
     */
    private int executeWithArgs(String query, Object[] fields) {
        Connection connection = null;
        int result = 0;
        PreparedStatement ps = null;
        try {
            connection = UserStorage.getInstance().getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(query);
            for (int i = 0; i < fields.length; i++) {
                ps.setObject(i + 1, fields[i]);
            }
            result = ps.executeUpdate();
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            result = 0;
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            this.closeStatement(ps);
        }
        this.endConnection(connection);
        return result;
    }
    /**
     * Возвращает список User, ограниченный полем fields.
     * @param query запрос.
     * @param fields поля ? для PreparedStatement.
     * @return запрошенный список.
     */
    private List<User> getUsers(String query, Object[] fields) {
        Connection connection = null;
        List<User> users = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = UserStorage.getInstance().getConnection();
            ps = connection.prepareStatement(query);
            ps.setObject(1, fields[0]);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getTimestamp(5).getTime());
                users.add(user);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        this.endConnection(connection);
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

