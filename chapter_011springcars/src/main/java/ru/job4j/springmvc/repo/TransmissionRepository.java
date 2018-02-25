package ru.job4j.springmvc.repo;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.springmvc.models.Transmission;

public interface TransmissionRepository extends CrudRepository<Transmission, Integer> {
}
