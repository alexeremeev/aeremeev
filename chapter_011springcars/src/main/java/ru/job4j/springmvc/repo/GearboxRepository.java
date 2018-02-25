package ru.job4j.springmvc.repo;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.springmvc.models.Gearbox;

public interface GearboxRepository extends CrudRepository<Gearbox, Integer> {
}
