package ru.job4j.control.dao;

import org.apache.log4j.Logger;
import ru.job4j.control.models.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * сlass AddressDAO - реализация DAO для модели Address.
 */
public class AddressDAO extends BasicDAO {

    private final static Logger LOGGER = Logger.getLogger(AddressDAO.class);
    /**
     * Пул коннетов к БД.
     */
    private PSQLpool pool;

    /**
     * Конструктор.
     * @param pool пул коннектов к БД.
     */
    public AddressDAO(PSQLpool pool) {
        super(pool);
    }

    @Override
    protected List<Address> parseResultSet(ResultSet rs) throws SQLException {
        List<Address> result = new ArrayList<>();
        while (rs.next()) {
            Address address = new Address();
            address.setId(rs.getInt("id"));
            address.setName(rs.getString("name"));
            result.add(address);
        }
        return result;
    }

    /**
     * Создать адрес.
     * @param address адрес.
     * @return ID созданной строки в БД.
     */
    public int createAddress(Address address) {
        String query = "INSERT INTO ADDRESS (NAME) VALUES (?)";
        Object[] fields = new Object[] {address.getName()};
        return super.create(fields, query);
    }

    /**
     * Удалить адрес.
     * @param id ID адреса.
     * @return true, если удален.
     */
    public boolean deleteAddress(int id) {
        String query = "DELETE FROM ADDRESS WHERE ID = ?";
        return super.delete(id, query);
    }

    /**
     * Обновить адрес.
     * @param address адрес.
     * @return true, если обновлен.
     */
    public boolean updateAddress(Address address) {
        String query = "UPDATE address SET name = ? WHERE id = ?";
        Object[] fields = new Object[] {address.getName(), address.getId()};
        return super.update(fields, query);
    }

    /**
     * Получить список всех адресов.
     * @return список всех адресов.
     */
    public List<Address> getAll() {
        String query = "SELECT * FROM ADDRESS WHERE ID <> ?";
        Object[] fields = new Object[] {0};
        return super.getAll(fields, query);
    }

    /**
     * Найти адрес по ID.
     * @param id ID адреса.
     * @return адрес, если найден, иначе null.
     */
    public Address findAddressById(int id) {
        String query = "SELECT * FROM ADDRESS WHERE ID = ?";
        Object[] fields = new Object[] {id};
        List<Address> result = super.getAll(fields, query);
        return result.isEmpty() ? null : result.get(0);
    }
    /**
     * Найти адрес по названию.
     * @param name название адреса.
     * @return адрес, если найден, иначе null.
     */
    public Address findAddressByName(String name) {
        String query = "SELECT * FROM ADDRESS WHERE NAME = ?";
        Object[] fields = new Object[] {name};
        List<Address> result = super.getAll(fields, query);
        return result.isEmpty() ? null : result.get(0);
    }

    /**
     * Сбросить таблицу.
     */
    public void clearTable() {
        String query = "TRUNCATE TABLE ADDRESS RESTART IDENTITY CASCADE";
        super.clearTable(query);
    }
}
