package ru.job4j.dao;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.Car;
import ru.job4j.models.Engine;
import ru.job4j.models.Gearbox;
import ru.job4j.models.Transmission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * Car DAO tests.
 * @author aeremeev.
 * @version 1
 * @since 01.02.2018
 */
public class CarDAOTest {

    private CarDAO carDAO = new CarDAO();
    private EngineDAO engineDAO = new EngineDAO();
    private GearboxDAO gearboxDAO = new GearboxDAO();
    private TransmissionDAO transmissionDAO = new TransmissionDAO();

    /**
     * Clear tables.
     */
    @Before
    public void clearTable() {
        carDAO.clearTable();
        engineDAO.clearTable();
        gearboxDAO.clearTable();
        transmissionDAO.clearTable();
    }

    /**
     * Test of adding new car.
     */
    @Test
    public void whenAddCarThenGetCorrectCar() {
        Car car = new Car();

        Engine engine = new Engine();
        engine.setForce(108);
        engineDAO.saveOrUpdate(engine);

        Gearbox gearbox = new Gearbox();
        gearbox.setShift("auto");
        gearboxDAO.saveOrUpdate(gearbox);

        Transmission transmission = new Transmission();
        transmission.setDrive("front");
        transmissionDAO.saveOrUpdate(transmission);

        car.setName("test car");
        car.setEngine(engine);
        car.setGearbox(gearbox);
        car.setTransmission(transmission);
        carDAO.saveOrUpdate(car);
        List<Car> result = carDAO.getAll();
        assertThat(result.get(0), is(car));

    }

    /**
     * Test of updating car.
     */
    @Test
    public void whenUpdateCarThenGetUpdatedResult() {
        Car car = new Car();

        Engine engine = new Engine();
        engine.setForce(108);
        engineDAO.saveOrUpdate(engine);

        Gearbox gearbox = new Gearbox();
        gearbox.setShift("auto");
        gearboxDAO.saveOrUpdate(gearbox);

        Transmission transmission = new Transmission();
        transmission.setDrive("front");
        transmissionDAO.saveOrUpdate(transmission);

        car.setName("test car");
        car.setEngine(engine);
        car.setGearbox(gearbox);
        car.setTransmission(transmission);
        carDAO.saveOrUpdate(car);
        car.setName("updated name");
        carDAO.saveOrUpdate(car);
        List<Car> result = carDAO.getAll();
        assertThat(result.get(0), is(car));
    }

    /**
     * Test of deleting car.
     */
    @Test
    public void whenDeleteCarThenResultListIsEmpty() {
        Car car = new Car();

        Engine engine = new Engine();
        engine.setForce(108);
        engineDAO.saveOrUpdate(engine);

        Gearbox gearbox = new Gearbox();
        gearbox.setShift("auto");
        gearboxDAO.saveOrUpdate(gearbox);

        Transmission transmission = new Transmission();
        transmission.setDrive("front");
        transmissionDAO.saveOrUpdate(transmission);

        car.setName("test car");
        car.setEngine(engine);
        car.setGearbox(gearbox);
        car.setTransmission(transmission);
        carDAO.saveOrUpdate(car);
        carDAO.delete(car);
        List<Car> result = carDAO.getAll();
        assertTrue(result.isEmpty());

    }

    /**
     * Test of getting cars list.
     */
    @Test
    public void whenAddCoupleCarsThenGetAllCars() {
        Car firstCar = new Car();

        Engine firstEngine = new Engine();
        firstEngine.setForce(108);
        engineDAO.saveOrUpdate(firstEngine);

        Gearbox firstGearbox = new Gearbox();
        firstGearbox.setShift("auto");
        gearboxDAO.saveOrUpdate(firstGearbox);

        Transmission firstTransmission = new Transmission();
        firstTransmission.setDrive("front");
        transmissionDAO.saveOrUpdate(firstTransmission);

        firstCar.setName("test car one");
        firstCar.setEngine(firstEngine);
        firstCar.setGearbox(firstGearbox);
        firstCar.setTransmission(firstTransmission);
        carDAO.saveOrUpdate(firstCar);

        Car secondCar = new Car();

        Engine secondEngine = new Engine();
        secondEngine.setForce(160);
        engineDAO.saveOrUpdate(secondEngine);

        Gearbox secondGearbox = new Gearbox();
        secondGearbox.setShift("variator");
        gearboxDAO.saveOrUpdate(secondGearbox);

        Transmission secondTransmission = new Transmission();
        secondTransmission.setDrive("four-wheel");
        transmissionDAO.saveOrUpdate(secondTransmission);

        secondCar.setName("test car");
        secondCar.setEngine(secondEngine);
        secondCar.setGearbox(secondGearbox);
        secondCar.setTransmission(secondTransmission);
        carDAO.saveOrUpdate(secondCar);
        secondCar.setName("updated name");
        carDAO.saveOrUpdate(secondCar);

        List<Car> expected = new ArrayList<>(Arrays.asList(firstCar, secondCar));
        List<Car> result = carDAO.getAll();

        assertThat(result, is(expected));
    }

    /**
     * Test of searching car by ID.
     */
    @Test
    public void whenSearchCarByIDThenGetEngine() {
        Car expected = new Car();

        Engine engine = new Engine();
        engine.setForce(108);
        engineDAO.saveOrUpdate(engine);

        Gearbox gearbox = new Gearbox();
        gearbox.setShift("auto");
        gearboxDAO.saveOrUpdate(gearbox);

        Transmission transmission = new Transmission();
        transmission.setDrive("front");
        transmissionDAO.saveOrUpdate(transmission);

        expected.setName("test car");
        expected.setEngine(engine);
        expected.setGearbox(gearbox);
        expected.setTransmission(transmission);
        carDAO.saveOrUpdate(expected);
        Car result = carDAO.findById(expected.getId());

        assertThat(result,is(expected));
    }

}