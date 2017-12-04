package ru.job4j.control.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.control.models.MusicType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * class PSQLpool - пул коннектов к БД.
 */
public class PSQLpool {
    /**
     * Instance.
     */
    private static final PSQLpool INSTANCE = new PSQLpool();
    /**
     * DBCP datasource.
     */
    private BasicDataSource dataSource;
    /**
     * Загрузчик настроек.
     */
    private Settings settings = new Settings("dao.properties");

    /**
     * Конструктор.
     */
    private PSQLpool() {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(this.settings.getSettings("DB_driver"));
        dataSource.setUsername(this.settings.getSettings("DB_username"));
        dataSource.setPassword(this.settings.getSettings("DB_password"));
        dataSource.setUrl(this.settings.getSettings("DB_url"));

        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(100);
        dataSource.setMaxOpenPreparedStatements(180);
        List<String> initSQLs = new ArrayList<>(Arrays.asList(this.settings.getSettings("SQL_CREATE_ADDRESS"),
                this.settings.getSettings("SQL_CREATE_MUSICTYPES"),
                this.settings.getSettings("SQL_CREATE_ROLES"),
                this.settings.getSettings("SQL_CREATE_USERS"),
                this.settings.getSettings("SQL_CREATE_USER_MUSICTYPES")));
        dataSource.setConnectionInitSqls(initSQLs);
    }

    /**
     * Get instance.
     * @return instance.
     */
    public static PSQLpool getInstance() {
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
     * Получить DAO модели Address.
     * @return DAO модели Address.
     */
    public AddressDAO getAddressDAO() {
        return new AddressDAO(this);
    }
    /**
     * Получить DAO модели MusicType.
     * @return DAO модели MusicType.
     */
    public MusicTypeDAO getMusicTypeDAO() {
        return new MusicTypeDAO(this);
    }
    /**
     * Получить DAO модели Role.
     * @return DAO модели Role.
     */
    public RoleDAO getRoleDAO() {
        return new RoleDAO(this);
    }
    /**
     * Получить DAO модели User.
     * @return DAO модели User.
     */
    public UserDAO getUserDAO() {
        return new UserDAO(this);
    }
}
