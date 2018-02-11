package ru.job4j.dao;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.Body;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BodyDAOTest {
    
    private DAOInterface<Body> dao = new GenericDAO<>();

    /**
     * Clear table.
     */
    @Before
    public void clearTable() {
        dao.executeQuery("Truncate table body restart identity cascade");
    }
    /**
     * Test of adding new body.
     */
    @Test
    public void whenAddBodyThenGetCorrectBody() {
        Body body = new Body();
        body.setName("Hatchback");
        dao.saveOrUpdate(body);
        List<Body> result = dao.getAll(Body.class);
        assertThat(result.get(0), is(body));

    }
    /**
     * Test of updating body.
     */
    @Test
    public void whenUpdateBodyThenGetUpdatedResult() {
        Body body = new Body();
        body.setName("Hatchback");
        dao.saveOrUpdate(body);
        body.setName("Sedan");
        dao.saveOrUpdate(body);
        List<Body> result = dao.getAll(Body.class);
        assertThat(result.get(0), is(body));
    }
    /**
     * Test of deleting body.
     */
    @Test
    public void whenDeleteBodyThenResultListIsEmpty() {
        Body body = new Body();
        body.setName("Hatchback");
        dao.saveOrUpdate(body);
        dao.delete(body);
        List<Body> result = dao.getAll(Body.class);
        assertTrue(result.isEmpty());

    }
    /**
     * Test of getting bodies list.
     */
    @Test
    public void whenAddCoupleBodiesThenGetAllBodies() {
        Body hatch = new Body();
        hatch.setName("Hatchback");
        Body sedan = new Body();
        sedan.setName("Sedan");
        dao.saveOrUpdate(hatch);
        dao.saveOrUpdate(sedan);

        List<Body> expected = new ArrayList<>(Arrays.asList(hatch, sedan));
        List<Body> result = dao.getAll(Body.class);

        assertThat(result, is(expected));
    }
    /**
     * Test of searching Body by ID.
     */
    @Test
    public void whenSearchBodyByIDThenGetBody() {
        Body expected = new Body();
        expected.setName("Hatchback");
        dao.saveOrUpdate(expected);

        Body result = dao.findById(Body.class, expected.getId());

        assertThat(result, is(expected));
    }
}