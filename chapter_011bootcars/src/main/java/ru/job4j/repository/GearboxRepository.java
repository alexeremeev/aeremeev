package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.Gearbox;


public interface GearboxRepository extends CrudRepository<Gearbox, Integer> {
}
