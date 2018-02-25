package ru.job4j.springmvc.repo;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.springmvc.models.Image;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image, Integer> {
    List<Image> findByOrderId(Integer orderId);
}
