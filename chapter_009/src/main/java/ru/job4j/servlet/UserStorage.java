package ru.job4j.servlet;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.servlet.model.Address;
import ru.job4j.servlet.model.Role;
import ru.job4j.servlet.model.User;

import java.sql.*;
import java.util.*;

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
        List<String> initSQLs = new ArrayList<>(Arrays.asList(this.settings.getSettings("SQL_CREATE_COUNTRIES"),
                this.settings.getSettings("SQL_CREATE_CITIES"),
                this.settings.getSettings("SQL_CREATE_ADDRESS"),
                this.settings.getSettings("SQL_CREATE_ROLES"),
                this.settings.getSettings("SQL_CREATE_TABLE")));
        dataSource.setConnectionInitSqls(initSQLs);
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
     * Close connections pool.
     */
    public void close() {
        try {
            this.dataSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        String query = this.settings.getSettings("SQL_ADD_USER");
        Object[] fields = new Object[] {user.getName(), user.getLogin(), user.getEmail(),
                new Timestamp(user.getCreateDate()), user.getPassword(), user.getRole().getId(),
                user.getAddress().getCityId()};
        if (this.executeWithArgs(query, fields) > 0) {
            result = true;
        }
        return result;
    }

    /**
     * Добавить роль пользователя.
     * @param role роль.
     * @return true, если добавлена.
     */
    public boolean addRole(Role role) {
        boolean result = false;
        String query = this.settings.getSettings("SQL_ADD_ROLE");
        Object[] fields = new Object[] {role.getName(), role.getAdmin()};
        int execute = this.executeWithArgs(query, fields);
        if (execute > 0) {
            role.setId(execute);
            result = true;
        }
        return result;
    }

    /**
     * Возвращает список всех пользователей.
     * @return список всех пользователей.
     */
    public List<User> getUsers() {
        String query = this.settings.getSettings("SQL_FIND_ALL");
        Object[] fields = new Object[] {0};
        List<User> users = this.getUsers(query, fields);
        return users;
    }

    /**
     * Возвращает список всех ролей.
     * @return список всех ролей.
     */
    public List<Role> getRoles() {
        String query = this.settings.getSettings("SQL_FIND_ALL_ROLES");
        Object[] fields = new Object[] {0};
        List<Role> roles = this.getRoles(query, fields);
        return roles;
    }

    /**
     * Изменить данные пользователя.
     * @param user пользователь.
     * @return true, если обновлен.
     */
    public boolean updateUser(User user) {
        boolean result = false;
        String query = this.settings.getSettings("SQL_UPDATE_USER");
        Object[] fields = new Object[] {user.getName(), user.getEmail(), user.getPassword(),
                user.getRole().getId(), user.getAddress().getCityId(), user.getLogin()};
        if (this.executeWithArgs(query, fields) > 0) {
            result = true;
        }
        return result;
    }

    /**
     * Изменить данные роли.
     * @param role роль.
     * @return true, если обновлена.
     */
    public boolean updateRole(Role role) {
        boolean result = false;
        String query = this.settings.getSettings("SQL_UPDATE_ROLE");
        Object[] fields = new Object[] {role.getName(), role.getAdmin(), role.getId()};
        if (this.executeWithArgs(query, fields) > 0) {
            result = true;
        }
        return result;
    }

    /**
     * Удалить пользователя.
     * @param user пользователь.
     * @return true, если удален.
     */
    public boolean deleteUser(User user) {
        boolean result = false;
        String query = this.settings.getSettings("SQL_DELETE_USER");
        Object[] fields = new Object[] {user.getLogin()};
        if (this.executeWithArgs(query, fields) > 0) {
            result = true;
        }
        return result;
    }

    /**
     * Удалить роль.
     * @param role роль
     * @return true, если удалена.
     */
    public boolean deleteRole(Role role) {
        boolean result = false;
        String query = this.settings.getSettings("SQL_DELETE_ROLE");
        Object[] fields = new Object[] {role.getName()};
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
     * Найти роль по ID.
     * @param id ID.
     * @return роль.
     */
    public Role findRole(int id) {
        String query = this.settings.getSettings("SQL_FIND_ROLE");
        Object[] fields = new Object[] {id};
        List<Role> result = this.getRoles(query, fields);
        if (!result.isEmpty()) {
            return result.get(0);
        } else {
            return null;
        }
    }

    /**
     * Получить список всех стран.
     * @return список всех стран.
     */
    public Map<Integer, String> getCountries() {
        Map<Integer, String> result = new HashMap<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = this.getConnection();
            ps = connection.prepareStatement("SELECT * FROM COUNTRIES");
            rs = ps.executeQuery();
            while (rs.next()) {
                result.put(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            this.closeStatement(ps);
            this.closeResult(rs);
        }
        this.endConnection(connection);
        return result;
    }

    /**
     * Получить список всех городов по ID страны.
     * @param countryId ID страны.
     * @return список всех городов по ID страны.
     */
    public Map<Integer, String> getCities(int countryId) {
        Map<Integer, String> result = new HashMap<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = this.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("select id, name from cities where country_id = ?");
            ps.setInt(1, countryId);
            rs = ps.executeQuery();
            while (rs.next()) {
                result.put(rs.getInt("id"), rs.getString("name"));
            }
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            this.closeStatement(ps);
            this.closeResult(rs);
        }
        this.endConnection(connection);
        return result;
    }

    /**
     * Проверка существования пользователя с парой login / password в БД.
     * @param login login.
     * @param password password.
     * @return пользователь, если найден.
     */
    public User isCredential(String login, String password) {
        this.checkTable();
        User exist = null;
        User find = this.findByLogin(login);
        if (find != null && find.getLogin().equals(login) && find.getPassword().equals(password)) {
            exist = find;
        }
        return exist;
    }

    /**
     * Проверка существования таблицы USERS в БД.
     * Если отсутствует, то создается новая.
     */
    public void checkTable() {
        if ((this.select(this.settings.getSettings("SQL_CHECK_COUNTRIES")) == 0)) {
            String[] queries = new String[] {"insert into countries (name) values ('Russia')",
                    "insert into countries (name) values ('Ukraine')",
                    "insert into countries (name) values ('Belarus')",
                    "insert into countries (name) values ('Kazakhstan')"};
            this.executeBatch(queries);
        }
        if ((this.select(this.settings.getSettings("SQL_CHECK_CITIES")) == 0)) {
            String[] queries = new String[] {"insert into cities (name, country_id) values ('Moscow', 1)",
                    "insert into cities (name, country_id) values ('Saint-Petersburg', 1)",
                    "insert into cities (name, country_id) values ('Smolensk', 1)",
                    "insert into cities (name, country_id) values ('Bryansk', 1)",
                    "insert into cities (name, country_id) values ('Vladivostok', 1)",
                    "insert into cities (name, country_id) values ('Kiev', 2)",
                    "insert into cities (name, country_id) values ('Lviv', 2)",
                    "insert into cities (name, country_id) values ('Odessa', 2)",
                    "insert into cities (name, country_id) values ('Donetsk', 2)",
                    "insert into cities (name, country_id) values ('Kharkiv', 2)",
                    "insert into cities (name, country_id) values ('Minsk', 3)",
                    "insert into cities (name, country_id) values ('Brest', 3)",
                    "insert into cities (name, country_id) values ('Mogilev', 3)",
                    "insert into cities (name, country_id) values ('Korbin', 3)",
                    "insert into cities (name, country_id) values ('Borisov', 3)",
                    "insert into cities (name, country_id) values ('Astana', 4)",
                    "insert into cities (name, country_id) values ('Almaty', 4)",
                    "insert into cities (name, country_id) values ('Atyrau', 4)",
                    "insert into cities (name, country_id) values ('Aktobe', 4)",
                    "insert into cities (name, country_id) values ('Shalkar', 4)"};
            this.executeBatch(queries);
        }
        if ((this.select(this.settings.getSettings("SQL_CHECK_ADDRESS")) == 0)) {
            this.execute("INSERT INTO ADDRESS (COUNTRY_ID, CITY_ID) SELECT COUNTRY_ID, ID FROM CITIES WHERE ID <>0");
        }

        if (this.select(this.settings.getSettings("SQL_CHECK_ROLES")) == 0) {
            this.execute(this.settings.getSettings("SQL_ADD_ADMIN_ROLE"));
            this.execute(this.settings.getSettings("SQL_ADD_USER_ROLE"));
        }
        if (this.select(this.settings.getSettings("SQL_CHECK_USERS")) == 0) {
            this.execute(this.settings.getSettings("SQL_ADD_ADMIN"));
        }
    }

    /**
     * Очистить таблицу Users.
     */
    public void clearTable() {
        this.execute(this.settings.getSettings("SQL_CLEAR_TABLE"));
        this.execute(this.settings.getSettings("SQL_CLEAR_ROLES"));
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
            connection = this.getConnection();
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
            connection = this.getConnection();
            connection.setAutoCommit(false);
            st = connection.createStatement();
            result = st.executeUpdate(query);
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        ResultSet rs = null;
        try {
            connection = this.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < fields.length; i++) {
                ps.setObject(i + 1, fields[i]);
            }
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getInt(1);
            }
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
            this.closeResult(rs);
        }
        this.endConnection(connection);
        return result;
    }

    private void executeBatch(String[] queries) {
        Connection connection = null;
        Statement st = null;
        try {
            connection = this.getConnection();
            connection.setAutoCommit(false);
            st = connection.createStatement();
            for (int i = 0; i < queries.length; i++) {
                st.addBatch(queries[i]);
            }
            st.executeBatch();
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                this.closeStatement(st);
            }
            this.endConnection(connection);
        }
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
            connection = this.getConnection();
            ps = connection.prepareStatement(query);
            ps.setObject(1, fields[0]);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getTimestamp(4).getTime(), rs.getString(5));
                Role role = new Role(rs.getString(6));
                role.setId(rs.getInt(7));
                role.setAdmin(rs.getBoolean(8));
                user.setRole(role);
                Address address = new Address();
                address.setCountryId(rs.getInt(9));
                address.setCountry(rs.getString(10));
                address.setCityId(rs.getInt(11));
                address.setCity(rs.getString(12));
                user.setAddress(address);
                users.add(user);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        this.endConnection(connection);
        return users;
    }

    /**
     * Возвращает список Role, ограниченный полем fields.
     * @param query запрос.
     * @param fields поля ? для PreparedStatement.
     * @return запрошенный список.
     */
    private List<Role> getRoles(String query, Object[] fields) {
        Connection connection = null;
        List<Role> roles = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = this.getConnection();
            ps = connection.prepareStatement(query);
            ps.setObject(1, fields[0]);
            rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role(rs.getString(2));
                role.setId(rs.getInt(1));
                role.setAdmin(rs.getBoolean(3));
                roles.add(role);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        this.endConnection(connection);
        return roles;
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

