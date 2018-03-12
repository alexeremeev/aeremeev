package ru.job4j.service;

import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Url;
import ru.job4j.repository.UrlRepository;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class UrlServiceImpl implements UrlService {

    private UrlRepository repository;

    @Autowired
    public UrlServiceImpl(UrlRepository repository) {
        this.repository = repository;
    }

    @Override
    public Url add(Url url) {
        return this.repository.save(url);
    }

    @Override
    public List<Url> getAll() {
        return (List<Url>) this.repository.findAll();
    }

}
