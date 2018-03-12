package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.domain.Url;

import java.util.List;

@Repository
public interface UrlRepository extends CrudRepository<Url, String> {
    Url findByShortUrl(String shortUrl);
    List<Url> findByUserUsername(String username);
}
