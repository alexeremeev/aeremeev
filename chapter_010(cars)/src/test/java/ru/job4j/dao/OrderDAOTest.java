package ru.job4j.dao;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.*;

import java.sql.Timestamp;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class OrderDAOTest {

    private DAOInterface dao = new GenericDAO();

    private Engine testEngine = new Engine();
    private Gearbox testGearbox = new Gearbox();
    private Transmission testTrnsmsn = new Transmission();
    private Model testModel = new Model();
    private Body testBody = new Body();
    private Car testCar = new Car();
    private User testUser = new User();

    /**
     * Clear table.
     */
    @Before
    public void clearTable() {
        dao.executeQuery("Truncate table orders restart identity and commit no check");
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
        testCar.setName("Test car");
        testCar.setBody(testBody);
        testCar.setEngine(testEngine);
        testCar.setGearbox(testGearbox);
        testCar.setModel(testModel);
        testCar.setTransmission(testTrnsmsn);
        dao.saveOrUpdate(testCar);
        testUser.setLogin("login");
        testUser.setPassword("password");
        dao.saveOrUpdate(testUser);
    }

    /**
     * Test of adding new order.
     */
    @Test
    public void whenAddOrderThenGetCorrectOrder() {
        Order testOrder = new Order();
        testOrder.setUser(testUser);
        testOrder.setCar(testCar);
        testOrder.setReleaseDate(new Timestamp(0L));
        testOrder.setMileage(1);
        testOrder.setPrice(1);
        dao.saveOrUpdate(testOrder);
        List<Order> result = dao.getAll(Order.class);
        assertThat(result.get(0), is(testOrder));
    }

    /**
     * Test of updating order.
     */
    @Test
    public void whenUpdateOrderThenGetUpdatedResult() {
        Order testOrder = new Order();
        testOrder.setUser(testUser);
        testOrder.setCar(testCar);
        testOrder.setReleaseDate(new Timestamp(0L));
        testOrder.setMileage(1);
        testOrder.setPrice(1);
        dao.saveOrUpdate(testOrder);
        testOrder.setPrice(1000);
        testOrder.setMileage(1000);
        dao.saveOrUpdate(testOrder);
        List<Order> result = dao.getAll(Order.class);
        assertThat(result.get(0), is(testOrder));
    }

    /**
     * Test of deleting order.
     */
    @Test
    public void whenDeleteOrderThenResultListIsEmpty() {
        Order testOrder = new Order();
        testOrder.setUser(testUser);
        testOrder.setCar(testCar);
        testOrder.setReleaseDate(new Timestamp(0L));
        testOrder.setMileage(1);
        testOrder.setPrice(1);
        dao.saveOrUpdate(testOrder);
        dao.delete(testOrder);
        List<Order> result = dao.getAll(Order.class);
        assertTrue(result.isEmpty());
    }

    /**
     * Test of getting orders list.
     */
    @Test
    public void whenAddCoupleOrdersThenGetAllOrders() {
        Order firstOrder = new Order();
        firstOrder.setUser(testUser);
        firstOrder.setCar(testCar);
        firstOrder.setReleaseDate(new Timestamp(0L));
        firstOrder.setMileage(1);
        firstOrder.setPrice(1);
        dao.saveOrUpdate(firstOrder);

        Order secondOrder = new Order();
        secondOrder.setUser(testUser);
        secondOrder.setCar(testCar);
        secondOrder.setReleaseDate(new Timestamp(0L));
        secondOrder.setMileage(100);
        secondOrder.setPrice(100);
        dao.saveOrUpdate(secondOrder);

        List<Order> expected = new ArrayList<>(Arrays.asList(firstOrder, secondOrder));
        List<Order> result = dao.getAll(Order.class);

        assertThat(result, is(expected));
    }

    /**
     * Test of searching order by ID.
     */
    @Test
    public void whenSearchOrderByIDThenGetOrder() {
        Order expected = new Order();
        expected.setUser(testUser);
        expected.setCar(testCar);
        expected.setReleaseDate(new Timestamp(0L));
        expected.setMileage(1);
        expected.setPrice(1);
        dao.saveOrUpdate(expected);
        Order result = (Order) dao.findById(Order.class, expected.getId());

        assertThat(result, is(expected));
    }
    /**
     * Test of searching orders by user filter query.
     */
    @Test
    public void whenFilterOrdersByUserQueryThenGetListOfOrders() {

        Order firstOrder = new Order();
        firstOrder.setUser(testUser);
        firstOrder.setCar(testCar);
        firstOrder.setReleaseDate(new Timestamp(0L));
        firstOrder.setMileage(10000);
        firstOrder.setPrice(1);
        dao.saveOrUpdate(firstOrder);

        Order secondOrder = new Order();
        secondOrder.setUser(testUser);
        secondOrder.setCar(testCar);
        secondOrder.setReleaseDate(new Timestamp(100L));
        secondOrder.setMileage(100);
        secondOrder.setPrice(100);
        dao.saveOrUpdate(secondOrder);

        Order thirdOrder = new Order();
        thirdOrder.setUser(testUser);
        thirdOrder.setCar(testCar);
        thirdOrder.setReleaseDate(new Timestamp(1000L));
        thirdOrder.setMileage(999);
        thirdOrder.setPrice(100);
        dao.saveOrUpdate(thirdOrder);

        List<Order> expected = new ArrayList<>(Arrays.asList(secondOrder, thirdOrder));

        String hql = "FROM Order as o WHERE o.images.size != :isEmpty and o.price < :price and o.mileage < :mileage";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("isEmpty", -1);
        parameters.put("price", 1000);
        parameters.put("mileage", 1000);

        List<Order> result = new OrderDAO().getOrdersByFilter(hql, parameters);

        assertThat(result, is(expected));

    }
}
