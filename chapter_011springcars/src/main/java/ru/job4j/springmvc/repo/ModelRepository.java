package ru.job4j.springmvc.repo;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.springmvc.models.Model;

public interface ModelRepository extends CrudRepository<Model, Integer> {
}
