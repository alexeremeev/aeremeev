package ru.job4j.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;
import ru.job4j.domain.Url;
import ru.job4j.repository.UrlRepository;

@Controller
public class RedirectController {

    private UrlRepository urlRepository;

    @Autowired
    public RedirectController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @PreAuthorize("permitAll()")
    @GetMapping(value = "/{shortUrl}")
    public RedirectView redirect(@PathVariable String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl);
        String originalUrl = url.getOriginalUrl();
        url.setCounter(url.getCounter() + 1);
        urlRepository.save(url);
        RedirectView rv = new RedirectView(originalUrl);
        rv.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return rv;
    }
}
