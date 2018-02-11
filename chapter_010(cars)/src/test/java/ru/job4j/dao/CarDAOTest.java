package ru.job4j.dao;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * Car DAO tests.
 * @author aeremeev.
 * @version 1.1
 * @since 01.02.2018
 */
public class CarDAOTest {

    private DAOInterface dao = new GenericDAO();

    private Engine testEngine = new Engine();
    private Gearbox testGearbox = new Gearbox();
    private Transmission testTrnsmsn = new Transmission();
    private Model testModel = new Model();
    private Body testBody = new Body();

    /**
     * Clear tables.
     */
    @Before
    public void clearTable() {
        dao.executeQuery("Truncate table car restart identity");
        dao.executeQuery("Truncate table engine restart identity cascade");
        dao.executeQuery("Truncate table gearbox restart identity cascade");
        dao.executeQuery("Truncate table transmission restart identity cascade");
        dao.executeQuery("Truncate table body restart identity cascade");
        dao.executeQuery("Truncate table model restart identity cascade");
        testEngine.setName(108);
        dao.saveOrUpdate(testEngine);
        testGearbox.setName("auto");
        dao.saveOrUpdate(testGearbox);
        testTrnsmsn.setName("front");
        dao.saveOrUpdate(testTrnsmsn);
        testBody.setName("Hatch");
        dao.saveOrUpdate(testBody);
        testModel.setName("Audi");
        dao.saveOrUpdate(testModel);
    }

    /**
     * Test of adding new car.
     */
    @Test
    public void whenAddCarThenGetCorrectCar() {
        Car car = new Car();

        car.setName("test car");
        car.setEngine(testEngine);
        car.setGearbox(testGearbox);
        car.setTransmission(testTrnsmsn);
        car.setBody(testBody);
        car.setModel(testModel);
        dao.saveOrUpdate(car);
        List<Car> result = dao.getAll(Car.class);
        assertThat(result.get(0), is(car));
    }

    /**
     * Test of updating car.
     */
    @Test
    public void whenUpdateCarThenGetUpdatedResult() {
        Car car = new Car();

        car.setName("test car");
        car.setEngine(testEngine);
        car.setGearbox(testGearbox);
        car.setTransmission(testTrnsmsn);
        car.setBody(testBody);
        car.setModel(testModel);
        dao.saveOrUpdate(car);
        car.setName("updated car");
        dao.saveOrUpdate(car);
        List<Car> result = dao.getAll(Car.class);
        assertThat(result.get(0), is(car));
    }

    /**
     * Test of deleting car.
     */
    @Test
    public void whenDeleteCarThenResultListIsEmpty() {
        Car car = new Car();

        car.setName("test car");
        car.setEngine(testEngine);
        car.setGearbox(testGearbox);
        car.setTransmission(testTrnsmsn);
        car.setBody(testBody);
        car.setModel(testModel);
        dao.saveOrUpdate(car);
        dao.delete(car);
        List<Car> result = dao.getAll(Car.class);
        assertTrue(result.isEmpty());
    }

    /**
     * Test of getting cars list.
     */
    @Test
    public void whenAddCoupleCarsThenGetAllCars() {
        Car firstCar = new Car();
        firstCar.setName("First Car");
        firstCar.setEngine(testEngine);
        firstCar.setGearbox(testGearbox);
        firstCar.setTransmission(testTrnsmsn);
        firstCar.setBody(testBody);
        firstCar.setModel(testModel);
        dao.saveOrUpdate(firstCar);

        Car secondCar = new Car();
        secondCar.setName("Second Car");
        secondCar.setEngine(testEngine);
        secondCar.setGearbox(testGearbox);
        secondCar.setTransmission(testTrnsmsn);
        secondCar.setBody(testBody);
        secondCar.setModel(testModel);
        dao.saveOrUpdate(secondCar);

        List<Car> expected = new ArrayList<>(Arrays.asList(firstCar, secondCar));
        List<Car> result = dao.getAll(Car.class);

        assertThat(result, is(expected));
    }

    /**
     * Test of searching car by ID.
     */
    @Test
    public void whenSearchCarByIDThenGetEngine() {
        Car expected = new Car();
        expected.setName("Expected car");
        expected.setEngine(testEngine);
        expected.setGearbox(testGearbox);
        expected.setTransmission(testTrnsmsn);
        expected.setBody(testBody);
        expected.setModel(testModel);
        dao.saveOrUpdate(expected);
        Car result = (Car) dao.findById(Car.class, expected.getId());

        assertThat(result, is(expected));
    }

}