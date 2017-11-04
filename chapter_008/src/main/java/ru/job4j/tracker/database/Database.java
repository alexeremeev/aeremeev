package ru.job4j.tracker.database;

import ru.job4j.tracker.Item;

import java.io.*;
import java.nio.file.Paths;
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
     * @param settingsFileName файл с настройками подключения.
     *                         Формат файла:
     *                         url;
     *                         username;
     *                         password;
     */
    public void setConnection(String settingsFileName) {
        try {
            String[] settings = this.fileToQuery(settingsFileName);
            this.connection = DriverManager.getConnection(settings[0], settings[1], settings[2]);
        } catch (IOException ioe) {
            ioe.printStackTrace();
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
     * Проверка, существует ли уже структура в БД.
     * Если нет, то создает новую из файла со скриптом.
     * @param sqlFile файл со скриптом.
     */
    public void createTable(String sqlFile) {
        ResultSet rs = null;
        try {
            rs = this.connection.getMetaData().getTables(null, null, "items", null);
            if (!rs.next()) {
                this.runScript(sqlFile);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            this.closeResult(rs);
        }
    }

    /**
     * Сброс таблицы.
     * @param sqlFile файл со скриптом.
     */
    public void clearTable(String sqlFile) {
        this.runScript(sqlFile);
    }

    /**
     * Исполняет запросы к SQL БД типа Insert, Update, Delete.
     * @param query запрос.
     * @param fields поля ? для PreparedStatement.
     */
    public void execute(String query, Object[] fields) {
        PreparedStatement ps = null;
        try {
            ps = this.connection.prepareStatement(query);

            for (int i = 0; i < fields.length; i++) {
                ps.setObject(i + 1, fields[i]);
            }

            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
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
     * Метод считывает и выполняет SQL скрипты.
     * Скрипт не должен содержать маркеров -- * /.
     */
    private void runScript(String filename) {
        try {
            String[] query = this.fileToQuery(filename);
            Statement st = this.connection.createStatement();
            for (int i = 0; i < query.length; i++) {
                if (!query[i].trim().equals("")) {
                    st.executeUpdate(query[i]);
                }
            }
            st.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     * Конвертирует файл в массив строковых запросов.
     * @param filename имя файла
     * @return массив String[].
     * @throws IOException IOException
     */
    private String[] fileToQuery(String filename) throws IOException {
        String line;
        StringBuilder builder = new StringBuilder();
        String path = Paths.get(".").toAbsolutePath().normalize().toString();
        FileReader fr = new FileReader(new File(String.format("%s/%s", path, filename)));
        BufferedReader br = new BufferedReader(fr);

        while ((line = br.readLine()) != null) {
            builder.append(line);
        }

        br.close();

        String[] query = builder.toString().split(";");
        return query;
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
}
