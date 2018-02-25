package ru.job4j.springmvc.repo;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.springmvc.models.Car;

public interface CarRepository extends CrudRepository<Car, Integer> {
}
