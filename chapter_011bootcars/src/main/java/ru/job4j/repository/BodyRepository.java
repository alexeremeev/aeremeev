package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.Body;

public interface BodyRepository extends CrudRepository<Body, Integer> {
}
