package ru.job4j.dao;

import ru.job4j.models.Transmission;

import java.util.List;

/**
 * Transmission model DAO.
 * @author aeremeev.
 * @version 1
 * @since 01.02.2018
 */
public class TransmissionDAO extends AbstractDAO {
    /**
     * Save new or update existing transmission.
     * @param transmission transmission.
     * @return id of transmission.
     */
    public int saveOrUpdate(Transmission transmission) {
        super.saveOrUpdate(transmission);
        return transmission.getId();
    }
    /**
     * Delete transmission.
     * @param transmission transmission.
     */
    public void delete(Transmission transmission) {
        super.delete(transmission);
    }
    /**
     * Find transmission by ID.
     * @param id id.
     * @return transmission.
     */
    public Transmission findById(int id) {
        return (Transmission) super.findById(Transmission.class, id);
    }
    /**
     * Get list of all transmissions from DB.
     * @return list of all transmissions.
     */
    public List<Transmission> getAll() {
        return super.getAll(Transmission.class);
    }
    /**
     * Clear table "transmission" in DB.
     */
    public void clearTable() {
        String query = "Truncate table transmission restart identity cascade";
        super.clearTable(query);
    }
}
