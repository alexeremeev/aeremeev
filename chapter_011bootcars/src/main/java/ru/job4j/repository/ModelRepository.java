package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.Model;

public interface ModelRepository extends CrudRepository<Model, Integer> {
}
