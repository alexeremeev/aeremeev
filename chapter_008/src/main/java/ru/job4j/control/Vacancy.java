package ru.job4j.control;

import java.net.URL;

/**
 * class Vacancy - описание вакансии на форуме SQL.ru.
 */
public class Vacancy {
    /**
     * Ссылка на вакансию.
     */
    private URL url;
    /**
     * Краткое описание, топик темы.
     */
    private String description;

    /**
     * Конструктор.
     * @param url ссылка.
     * @param description описание.
     */
    public Vacancy(URL url, String description) {
        this.url = url;
        this.description = description;
    }

    /**
     * Геттер url.
     * @return url.
     */
    public URL getUrl() {
        return this.url;
    }

    /**
     * Геттер описания.
     * @return описание.
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format("%s | %s", this.description, this.url);
    }
}
