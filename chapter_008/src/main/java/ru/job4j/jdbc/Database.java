package ru.job4j.jdbc;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * class Database - демонстрация работы с SQL.
 */
public class Database {
    /**
     * Connection c SQL DB.
     */
    private Connection connection;

    /**
     * Устанавливает соединение с базой данных.
     * @param url ссыылка на базу данных.
     */
    public void setConnection(String url) {
        try {
            this.connection = DriverManager.getConnection(url);
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
     * Получение всех Entry в списке из БД.
     * @param rows количество строк для выдачи.
     * @return список всех Entry, ограниченный количеством rows.
     */
    public List<Entry> getTable(int rows) {
        List<Entry> table = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = this.connection.prepareStatement("SELECT * FROM test");
            if (rows > 0) {
                ps.setMaxRows(rows);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                table.add(new Entry(rs.getString(1)));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            this.closeResult(rs);
            this.closeStatement(ps);
        }
        return table;
    }

    /**
     * Заполнение таблицы test в БД.
     * @param rows количество строк для добавления.
     * @return количество добавленных строк.
     */
    public int fillTable(int rows) {
        int result = 0;
        PreparedStatement ps = null;
        try {
            this.connection.setAutoCommit(false);
            ps = this.connection.prepareStatement("INSERT INTO test (field) VALUES (?)");
            for (int index = 1; index <= rows; index++) {
                ps.setInt(1, index);
                result += ps.executeUpdate();
            }
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
        return result;
    }

    /**
     * Создание новой таблицы test в БД.
     * Если таблица уже существует, то она обнуляется.
     */
    public void createTable() {
        PreparedStatement ps = null;
        try {
            ps = this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS test (field INTEGER)");
            int result = ps.executeUpdate();
            if (result == 0) {
                ps = this.connection.prepareStatement("DELETE FROM test");
                ps.executeUpdate();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            this.closeStatement(ps);
        }
    }

    /**
     * Закрытие PreparedStatement.
     * @param ps PreparedStatement.
     */
    private void closeStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
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
     * All-In-One method.
     * @param rows количество рядов для обработки.
     */
    public void init(int rows) {
        XMLconverter xmlconverter = new XMLconverter();
        Entries entries = new Entries();
        String url = "jdbc:sqlite:test.db";
        this.setConnection(url);
        this.createTable();
        this.fillTable(rows);
        List<Entry> list = this.getTable(rows);
        this.endConnection();
        entries.setEntries(list);
        xmlconverter.entryToXML(entries, new File("1.xml"));
        xmlconverter.convertViaXSL(new File("1.xml"), new File("transform.xsl"), new File("2.xml"));
        System.out.println(xmlconverter.evaluateViaXPath(new File("2.xml"), "sum(/entries/entry/@field)"));
    }
}
