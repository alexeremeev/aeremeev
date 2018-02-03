package ru.job4j.dao;

import ru.job4j.models.Car;

import java.util.List;

/**
 * Car model DAO.
 * @author aeremeev.
 * @version 1
 * @since 01.02.2018
 */
public class CarDAO extends AbstractDAO {
    /**
     * Save new or update existing car.
     * @param car car.
     * @return id of car.
     */
    public int saveOrUpdate(Car car) {
        super.saveOrUpdate(car);
        return car.getId();
    }

    /**
     * Delete car.
     * @param car car.
     */
    public void delete(Car car) {
        super.delete(car);
    }

    /**
     * Find car by ID.
     * @param id id.
     * @return car.
     */
    public Car findById(int id) {
        return (Car) super.findById(Car.class, id);
    }

    /**
     * Get list of all cars from DB.
     * @return list of all cars.
     */
    public List<Car> getAll() {
        return super.getAll(Car.class);
    }

    /**
     * Clear table "car" in DB.
     */
    public void clearTable() {
        String query = "Truncate table car restart identity";
        super.clearTable(query);
    }
}
