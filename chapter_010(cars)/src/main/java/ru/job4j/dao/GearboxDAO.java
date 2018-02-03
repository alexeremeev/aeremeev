package ru.job4j.dao;

import ru.job4j.models.Gearbox;

import java.util.List;
/**
 * Gearbox model DAO.
 * @author aeremeev.
 * @version 1
 * @since 01.02.2018
 */
public class GearboxDAO extends AbstractDAO {
    /**
     * Save new or update existing gearbox.
     * @param gearbox gearbox.
     * @return id of gearbox.
     */
    public int saveOrUpdate(Gearbox gearbox) {
        super.saveOrUpdate(gearbox);
        return gearbox.getId();
    }
    /**
     * Delete gearbox.
     * @param gearbox gearbox.
     */
    public void delete(Gearbox gearbox) {
        super.delete(gearbox);
    }
    /**
     * Find gearbox by ID.
     * @param id id.
     * @return gearbox.
     */
    public Gearbox findById(int id) {
        return (Gearbox) super.findById(Gearbox.class, id);
    }
    /**
     * Get list of all gearboxes from DB.
     * @return list of all gearboxes.
     */
    public List<Gearbox> getAll() {
        return super.getAll(Gearbox.class);
    }
    /**
     * Clear table "gearbox" in DB.
     */
    public void clearTable() {
        String query = "Truncate table gearbox restart identity cascade";
        super.clearTable(query);
    }
}
