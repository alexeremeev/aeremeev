package ru.job4j.control;

import java.sql.*;

public class Database {
    /**
     * Connection.
     */
    private Connection connection;

    /**
     * Установление соединения с БД.
     * @param url url.
     * @param username username.
     * @param password password.
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
     * Исполняет запросы к SQL БД типа Insert, Update, Delete с полями ?.
     * @param query запрос.
     * @param fields поля ? для PreparedStatement.
     * @return количество обновленных строк.
     */
    public int executeWithArgs(String query, Object[] fields) {
        int result = 0;
        PreparedStatement ps = null;
        try {
            ps = this.connection.prepareStatement(query);
            for (int i = 0; i < fields.length; i++) {
                ps.setObject(i + 1, fields[i]);
            }
            result = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            this.closeStatement(ps);
        }
        return result;
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
