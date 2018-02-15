package ru.job4j.dao;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * Model DAO tests HSQL DB based.
 * @author aeremeev.
 * @version 1.1
 * @since 01.02.2018
 */
public class ModelDAOTest {

    private DAOInterface<Model> dao = new GenericDAO<>();

    /**
     * Clear table.
     */
    @Before
    public void clearTable() {
        dao.executeQuery("Truncate table model restart identity and commit no check");
    }
    /**
     * Test of adding new model.
     */
    @Test
    public void whenAddModelThenGetCorrectModel() {
        Model model = new Model();
        model.setName("Civic Type-R");
        dao.saveOrUpdate(model);
        List<Model> result = dao.getAll(Model.class);
        assertThat(result.get(0), is(model));

    }
    /**
     * Test of updating model.
     */
    @Test
    public void whenUpdateModelThenGetUpdatedResult() {
        Model model = new Model();
        model.setName("Civic Type-R");
        dao.saveOrUpdate(model);
        model.setName("Civic");
        dao.saveOrUpdate(model);
        List<Model> result = dao.getAll(Model.class);
        assertThat(result.get(0), is(model));
    }
    /**
     * Test of deleting model.
     */
    @Test
    public void whenDeleteModelThenResultListIsEmpty() {
        Model model = new Model();
        model.setName("Civic Type-R");
        dao.saveOrUpdate(model);
        dao.delete(model);
        List<Model> result = dao.getAll(Model.class);
        assertTrue(result.isEmpty());

    }
    /**
     * Test of getting models list.
     */
    @Test
    public void whenAddCoupleModelsThenGetAllModels() {
        Model civicTR = new Model();
        civicTR.setName("Civic Type-R");
        Model civic = new Model();
        civic.setName("Civic");
        dao.saveOrUpdate(civicTR);
        dao.saveOrUpdate(civic);

        List<Model> expected = new ArrayList<>(Arrays.asList(civicTR, civic));
        List<Model> result = dao.getAll(Model.class);

        assertThat(result, is(expected));
    }
    /**
     * Test of searching model by ID.
     */
    @Test
    public void whenSearchModelByIDThenGetModel() {
        Model expected = new Model();
        expected.setName("Civic");
        dao.saveOrUpdate(expected);

        Model result = dao.findById(Model.class, expected.getId());

        assertThat(result, is(expected));
    }

}