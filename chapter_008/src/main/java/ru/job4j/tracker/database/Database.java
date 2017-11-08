package ru.job4j.tracker.database;

import ru.job4j.tracker.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    /**
     * Connection.
     */
    private Connection connection;

    /**
     * Установление соединения с БД.
     * @param url url БД.
     * @param username имя пользователя.
     * @param password пароль.
     */
    public void setConnection(String url, String username, String password) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     * Закрывает соединение с базой данных.
     */
    public void endConnection() {
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
    public int select(String query) {
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
        return result;
    }

    /**
     * Исполняет запросы Create к SQL БД.
     * @param query запрос.
     * @return количество обновленных строк.
     */
    public int execute(String query) {
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
        return result;
    }

    /**
     * Исполняет запросы к SQL БД типа Insert, Update, Delete.
     * @param query запрос.
     * @param fields поля ? для PreparedStatement.
     */
    public void executeWithArgs(String query, Object[] fields) {
        PreparedStatement ps = null;
        try {
            this.connection.setAutoCommit(false);
            ps = this.connection.prepareStatement(query);
            for (int i = 0; i < fields.length; i++) {
                ps.setObject(i + 1, fields[i]);
            }
            ps.executeUpdate();
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
    }

    /**
     * Возвращает список Item, ограниченный полем fields.
     * @param query запрос.
     * @param fields поля ? для PreparedStatement.
     * @return запрошенный список.
     */
    public List<Item> getItems(String query, Object[] fields) {
        List<Item> items = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = this.connection.prepareStatement(query);
            ps.setObject(1, fields[0]);
            rs = ps.executeQuery();
            while (rs.next()) {
                Item item = new Item(rs.getString(2), rs.getString(3), rs.getTimestamp(4).getTime());
                item.setId(rs.getInt(1));
                items.add(item);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return items;
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
