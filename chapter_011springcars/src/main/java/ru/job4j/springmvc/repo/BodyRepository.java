package ru.job4j.springmvc.repo;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.springmvc.models.Body;

public interface BodyRepository extends CrudRepository<Body, Integer> {
}
