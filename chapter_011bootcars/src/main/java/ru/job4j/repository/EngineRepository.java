package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.Engine;

public interface EngineRepository extends CrudRepository<Engine, Integer> {
}
