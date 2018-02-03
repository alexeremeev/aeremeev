package ru.job4j.dao;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.Transmission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * Transmission DAO tests.
 * @author aeremeev.
 * @version 1
 * @since 01.02.2018
 */
public class TransmissionDAOTest {

    private TransmissionDAO dao = new TransmissionDAO();
    /**
     * Clear table.
     */
    @Before
    public void clearTable() {
        dao.clearTable();
    }
    /**
     * Test of adding new transmission.
     */
    @Test
    public void whenAddTransmissionThenGetCorrectTransmission() {
        Transmission transmission = new Transmission();
        transmission.setDrive("front");
        dao.saveOrUpdate(transmission);
        List<Transmission> result = dao.getAll();
        assertThat(result.get(0), is(transmission));

    }
    /**
     * Test of updating transmission.
     */
    @Test
    public void whenUpdateTransmissionThenGetUpdatedResult() {
        Transmission transmission = new Transmission();
        transmission.setDrive("front");
        dao.saveOrUpdate(transmission);
        transmission.setDrive("rear");
        dao.saveOrUpdate(transmission);
        List<Transmission> result = dao.getAll();
        assertThat(result.get(0), is(transmission));
    }
    /**
     * Test of deleting transmission.
     */
    @Test
    public void whenDeleteTransmissionThenResultListIsEmpty() {
        Transmission transmission = new Transmission();
        transmission.setDrive("front");
        dao.saveOrUpdate(transmission);
        dao.delete(transmission);
        List<Transmission> result = dao.getAll();
        assertTrue(result.isEmpty());

    }
    /**
     * Test of getting transmissions list.
     */
    @Test
    public void whenAddCoupleTransmissionsThenGetAllTransmissions() {
        Transmission front = new Transmission();
        front.setDrive("front");
        Transmission rear = new Transmission();
        rear.setDrive("rear");
        dao.saveOrUpdate(front);
        dao.saveOrUpdate(rear);

        List<Transmission> expected = new ArrayList<>(Arrays.asList(front, rear));
        List<Transmission> result = dao.getAll();

        assertThat(result, is(expected));
    }
    /**
     * Test of searching transmission by ID.
     */
    @Test
    public void whenSearchTransmissionByIDThenGetTransmission() {
        Transmission expected = new Transmission();
        expected.setDrive("front");
        dao.saveOrUpdate(expected);

        Transmission result = dao.findById(expected.getId());

        assertThat(result,is(expected));
    }
}