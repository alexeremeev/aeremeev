package ru.job4j.springmvc.repo;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.springmvc.models.Engine;

public interface EngineRepository extends CrudRepository<Engine, Integer> {
}
