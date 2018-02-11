package ru.job4j.dao;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.Gearbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * Gearbox DAO tests.
 * @author aeremeev.
 * @version 1
 * @since 01.02.2018
 */
public class GearboxDAOTest {

    private DAOInterface<Gearbox> dao = new GenericDAO<>();
    /**
     * Clear table.
     */
    @Before
    public void clearTable() {
        dao.executeQuery("Truncate table gearbox restart identity cascade");
    }
    /**
     * Test of adding new gearbox.
     */
    @Test
    public void whenAddGearboxThenGetCorrectGearbox() {
        Gearbox gearbox = new Gearbox();
        gearbox.setName("auto");
        dao.saveOrUpdate(gearbox);
        List<Gearbox> result = dao.getAll(Gearbox.class);
        assertThat(result.get(0), is(gearbox));

    }
    /**
     * Test of updating gearbox.
     */
    @Test
    public void whenUpdateGearboxThenGetUpdatedResult() {
        Gearbox gearbox = new Gearbox();
        gearbox.setName("auto");
        dao.saveOrUpdate(gearbox);
        gearbox.setName("mechanical");
        dao.saveOrUpdate(gearbox);
        List<Gearbox> result = dao.getAll(Gearbox.class);
        assertThat(result.get(0), is(gearbox));
    }
    /**
     * Test of deleting gearbox.
     */
    @Test
    public void whenDeleteGearboxThenResultListIsEmpty() {
        Gearbox gearbox = new Gearbox();
        gearbox.setName("auto");
        dao.saveOrUpdate(gearbox);
        dao.delete(gearbox);
        List<Gearbox> result = dao.getAll(Gearbox.class);
        assertTrue(result.isEmpty());

    }
    /**
     * Test of getting gearboxes list.
     */
    @Test
    public void whenAddCoupleGearboxesThenGetAllGearboxes() {
        Gearbox auto = new Gearbox();
        auto.setName("auto");
        Gearbox mechanical = new Gearbox();
        mechanical.setName("mechanical");
        dao.saveOrUpdate(auto);
        dao.saveOrUpdate(mechanical);

        List<Gearbox> expected = new ArrayList<>(Arrays.asList(auto, mechanical));
        List<Gearbox> result = dao.getAll(Gearbox.class);

        assertThat(result, is(expected));
    }
    /**
     * Test of searching gearbox by ID.
     */
    @Test
    public void whenSearchGearboxByIDThenGetGearbox() {
        Gearbox expected = new Gearbox();
        expected.setName("auto");
        dao.saveOrUpdate(expected);

        Gearbox result = dao.findById(Gearbox.class, expected.getId());

        assertThat(result, is(expected));
    }
}