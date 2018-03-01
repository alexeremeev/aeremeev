package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.Car;

public interface CarRepository extends CrudRepository<Car, Integer> {
}
