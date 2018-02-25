package ru.job4j.springmvc.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.springmvc.models.Order;

import java.sql.Timestamp;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    @Query("select o from Order o where o.images.size <> :imagesSize and o.car.model.name like :name and o.orderDate > :orderDate")
    List<Order> findByImagesSizeAndCarModelNameAndOrderDateGreaterThan(@Param("imagesSize") Integer imagesSize,
                                                                       @Param("name") String name,
                                                                       @Param("orderDate") Timestamp orderDate);
}
