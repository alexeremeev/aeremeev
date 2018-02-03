package ru.job4j.dao;

import ru.job4j.models.Engine;

import java.util.List;
/**
 * Engine model DAO.
 * @author aeremeev.
 * @version 1
 * @since 01.02.2018
 */
public class EngineDAO extends AbstractDAO {
    /**
     * Save new or update existing engine.
     * @param engine engine.
     * @return id of engine.
     */
    public int saveOrUpdate(Engine engine) {
        super.saveOrUpdate(engine);
        return engine.getId();
    }
    /**
     * Delete engine.
     * @param engine engine.
     */
    public void delete(Engine engine) {
        super.delete(engine);
    }
    /**
     * Find engine by ID.
     * @param id id.
     * @return engine.
     */
    public Engine findById(int id) {
        return (Engine) super.findById(Engine.class, id);
    }
    /**
     * Get list of all engines from DB.
     * @return list of all engines.
     */
    public List<Engine> getAll() {
        return super.getAll(Engine.class);
    }
    /**
     * Clear table "engine" in DB.
     */
    public void clearTable() {
        String query = "Truncate table engine restart identity cascade";
        super.clearTable(query);
    }
}
