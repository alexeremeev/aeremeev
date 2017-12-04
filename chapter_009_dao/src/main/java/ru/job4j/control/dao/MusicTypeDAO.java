package ru.job4j.control.dao;

import org.apache.log4j.Logger;
import ru.job4j.control.models.MusicType;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * class MusicTypeDAO - реализация DAO для модели MusicType.
 */
public class MusicTypeDAO {

    private final static Logger LOGGER = Logger.getLogger(MusicTypeDAO.class);
    /**
     * Пул коннетов к БД.
     */
    private PSQLpool pool;

    /**
     * Конструктор.
     * @param pool пул коннектов к БД.
     */
    public MusicTypeDAO(PSQLpool pool) {
        this.pool = pool;
    }

    /**
     * Создать музыкальный стиль.
     * @param musicType музыкальный стиль.
     * @return ID созданной строки в БД.
     */
    public int createMusicType(MusicType musicType) {
        int result = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = this.pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("INSERT INTO MUSICTYPES (NAME) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, musicType.getName());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getInt(1);
            }
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            LOGGER.error(sqle.getMessage(), sqle);
            result = 0;
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * Удалить музыкальный стиль.
     * @param id ID музыкального стиля.
     * @return true, если удален.
     */
    public boolean deleteMusicType(int id) {
        boolean result = false;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = this.pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("DELETE FROM MUSICTYPES WHERE ID = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            result = true;
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            LOGGER.error(sqle.getMessage(), sqle);
            result = false;
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * Обновить музыкальный стиль.
     * @param musicType музыкальный стиль.
     * @return true, если обновлен.
     */
    public boolean updateMusicType(MusicType musicType) {
        boolean result = false;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = this.pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("UPDATE MUSICTYPES SET name = ? WHERE id = ?");
            ps.setString(1, musicType.getName());
            ps.setInt(2, musicType.getId());
            ps.executeUpdate();
            result = true;
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            LOGGER.error(sqle.getMessage(), sqle);
            result = false;
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * Получить список всех музыкальных стилей.
     * @return список всех музыкальных стилей.
     */
    public List<MusicType> getAll() {
        Connection connection = null;
        List<MusicType> result = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = this.pool.getConnection();
            ps = connection.prepareStatement("SELECT * FROM MUSICTYPES");
            rs = ps.executeQuery();
            while (rs.next()) {
                MusicType musicType = new MusicType();
                musicType.setId(rs.getInt(1));
                musicType.setName(rs.getString(2));
                result.add(musicType);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            LOGGER.error(sqle.getMessage(), sqle);
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * Найти музыкальный стиль по ID.
     * @param id ID музыкального стиля.
     * @return адрес, если найден, иначе null.
     */
    public MusicType findMusicTypeById(int id) {
        MusicType result = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = this.pool.getConnection();
            ps = connection.prepareStatement("SELECT * FROM MUSICTYPES WHERE ID = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = new MusicType();
                result.setId(rs.getInt(1));
                result.setName(rs.getString(2));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            LOGGER.error(sqle.getMessage(), sqle);
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * Найти музыкальный стиль по названию.
     * @param name название музыкального стиля.
     * @return адрес, если найден, иначе null.
     */
    public MusicType findMusicTypeByName(String name) {
        MusicType result = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = this.pool.getConnection();
            ps = connection.prepareStatement("SELECT * FROM MUSICTYPES WHERE NAME = ?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = new MusicType();
                result.setId(rs.getInt(1));
                result.setName(rs.getString(2));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            LOGGER.error(sqle.getMessage(), sqle);
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * Заполнить таблицу предзагруженными стилями.
     */
    public void fillMusicType() {
        String[] types = new String[] {"Pop", "Rock", "Rap", "R-n-B", "Jazz", "Funk", "Classic"};
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = this.pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("INSERT INTO MUSICTYPES (NAME) VALUES (?)");
            for (String type : types) {
                ps.setString(1, type);
                ps.addBatch();
            }
            ps.executeBatch();
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            LOGGER.error(sqle.getMessage(), sqle);
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Сбросить таблицу.
     */
    public void clearTable() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = this.pool.getConnection();
            ps = connection.prepareStatement("TRUNCATE TABLE MUSICTYPES RESTART IDENTITY CASCADE");
            ps.execute();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            LOGGER.error(sqle.getMessage(), sqle);
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Сбросить таблицу пользовательских предпочтений.
     */
    public void clearTableUserMusicTypes() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = this.pool.getConnection();
            ps = connection.prepareStatement("TRUNCATE TABLE USER_MUSICTYPES RESTART IDENTITY CASCADE");
            ps.execute();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            LOGGER.error(sqle.getMessage(), sqle);
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Найти музыкальные предпочтения по ID пользователя.
     */
    public Set<Integer> findUserMusicTypeByUserID(int id) {
        Set<Integer> result = new LinkedHashSet<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = this.pool.getConnection();
            ps = connection.prepareStatement("SELECT * FROM USER_MUSICTYPES WHERE USER_ID = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getInt("musictype_id")); //(column 2?)
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            LOGGER.error(sqle.getMessage(), sqle);
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage(), e);
            }
        }
        return result;
    }
}
