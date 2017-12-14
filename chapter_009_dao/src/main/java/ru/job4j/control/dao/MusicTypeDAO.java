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
public class MusicTypeDAO extends BasicDAO{

    /**
     * Конструктор.
     * @param pool пул коннектов к БД.
     */
    public MusicTypeDAO(PSQLpool pool) {
        super(pool);
    }

    @Override
    protected List<MusicType> parseResultSet(ResultSet rs) throws SQLException {
        List<MusicType> result = new ArrayList<>();
        while (rs.next()) {
            MusicType musicType = new MusicType();
            musicType.setId(rs.getInt("id"));
            musicType.setName(rs.getString("name"));
            result.add(musicType);
        }
        return result;
    }

    /**
     * Создать музыкальный стиль.
     * @param musicType музыкальный стиль.
     * @return ID созданной строки в БД.
     */
    public int createMusicType(MusicType musicType) {
        String query = "INSERT INTO MUSICTYPES (NAME) VALUES (?)";
        Object[] fields = new Object[] {musicType.getName()};
        return super.create(fields, query);
    }

    /**
     * Удалить музыкальный стиль.
     * @param id ID музыкального стиля.
     * @return true, если удален.
     */
    public boolean deleteMusicType(int id) {
        String query = "DELETE FROM MUSICTYPES WHERE ID = ?";
        return super.delete(id, query);
    }

    /**
     * Обновить музыкальный стиль.
     * @param musicType музыкальный стиль.
     * @return true, если обновлен.
     */
    public boolean updateMusicType(MusicType musicType) {
        String query = "UPDATE MUSICTYPES SET name = ? WHERE id = ?";
        Object[] fields = new Object[] {musicType.getName(), musicType.getId()};
        return super.update(fields, query);
    }

    /**
     * Получить список всех музыкальных стилей.
     * @return список всех музыкальных стилей.
     */
    public List<MusicType> getAll() {
        String query = "SELECT * FROM MUSICTYPES WHERE ID <> ?";
        Object[] fields = new Object[] {0};
        return super.getAll(fields, query);
    }

    /**
     * Найти музыкальный стиль по ID.
     * @param id ID музыкального стиля.
     * @return адрес, если найден, иначе null.
     */
    public MusicType findMusicTypeById(int id) {
        String query = "SELECT * FROM MUSICTYPES WHERE ID = ?";
        Object[] fields = new Object[] {id};
        List<MusicType> result = super.getAll(fields, query);
        return result.isEmpty() ? null : result.get(0);
    }

    /**
     * Найти музыкальный стиль по названию.
     * @param name название музыкального стиля.
     * @return адрес, если найден, иначе null.
     */
    public MusicType findMusicTypeByName(String name) {
        String query = "SELECT * FROM MUSICTYPES WHERE NAME = ?";
        Object[] fields = new Object[] {name};
        List<MusicType> result = super.getAll(fields, query);
        return result.isEmpty() ? null : result.get(0);
    }

    /**
     * Заполнить таблицу предзагруженными стилями.
     */
    public void fillMusicType() {
        String[] types = new String[] {"Pop", "Rock", "Rap", "R-n-B", "Jazz", "Funk", "Classic"};
        String query = "INSERT INTO MUSICTYPES (NAME) VALUES (?)";
        super.fill(types, query);
    }

    /**
     * Сбросить таблицу.
     */
    public void clearTable() {
        String query = "TRUNCATE TABLE MUSICTYPES RESTART IDENTITY CASCADE";
        super.clearTable(query);
    }

    /**
     * Сбросить таблицу пользовательских предпочтений.
     */
    public void clearTableUserMusicTypes() {
        String query = "TRUNCATE TABLE USER_MUSICTYPES RESTART IDENTITY CASCADE";
        super.clearTable(query);
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
            connection = super.pool.getConnection();
            ps = connection.prepareStatement("SELECT * FROM USER_MUSICTYPES WHERE USER_ID = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getInt("musictype_id")); //(column 2?)
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
