package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.Image;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image, Integer> {
    List<Image> findByOrderId(Integer orderId);
}
