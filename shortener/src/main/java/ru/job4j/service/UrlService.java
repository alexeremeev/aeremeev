package ru.job4j.service;

import ru.job4j.domain.Url;

import java.util.List;

public interface UrlService {
    Url add(Url url);
    List<Url> getAll();
}
