package ru.job4j.dao;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.Engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * Engine DAO tests HSQL DB based.
 * @author aeremeev.
 * @version 1.1
 * @since 01.02.2018
 */
public class EngineDAOTest {

    private DAOInterface<Engine> dao = new GenericDAO<>();

    /**
     * Clear table.
     */
    @Before
    public void clearTable() {
        dao.executeQuery("Truncate table engine restart identity and commit no check");
    }
    /**
     * Test of adding new engine.
     */
    @Test
    public void whenAddEngineThenGetCorrectEngine() {
        Engine engine = new Engine();
        engine.setName(108);
        dao.saveOrUpdate(engine);
        List<Engine> result = dao.getAll(Engine.class);
        assertThat(result.get(0), is(engine));

    }
    /**
     * Test of updating engine.
     */
    @Test
    public void whenUpdateEngineThenGetUpdatedResult() {
        Engine engine = new Engine();
        engine.setName(108);
        dao.saveOrUpdate(engine);
        engine.setName(160);
        dao.saveOrUpdate(engine);
        List<Engine> result = dao.getAll(Engine.class);
        assertThat(result.get(0), is(engine));
    }
    /**
     * Test of deleting engine.
     */
    @Test
    public void whenDeleteEngineThenResultListIsEmpty() {
        Engine engine = new Engine();
        engine.setName(108);
        dao.saveOrUpdate(engine);
        dao.delete(engine);
        List<Engine> result = dao.getAll(Engine.class);
        assertTrue(result.isEmpty());

    }
    /**
     * Test of getting engines list.
     */
    @Test
    public void whenAddCoupleEnginesThenGetAllEngines() {
        Engine engine108 = new Engine();
        engine108.setName(108);
        Engine engine160 = new Engine();
        engine160.setName(160);
        dao.saveOrUpdate(engine108);
        dao.saveOrUpdate(engine160);

        List<Engine> expected = new ArrayList<>(Arrays.asList(engine108, engine160));
        List<Engine> result = dao.getAll(Engine.class);

        assertThat(result, is(expected));
    }
    /**
     * Test of searching engine by ID.
     */
    @Test
    public void whenSearchEngineByIDThenGetEngine() {
        Engine expected = new Engine();
        expected.setName(108);
        dao.saveOrUpdate(expected);

        Engine result = dao.findById(Engine.class, expected.getId());

        assertThat(result, is(expected));
    }

}