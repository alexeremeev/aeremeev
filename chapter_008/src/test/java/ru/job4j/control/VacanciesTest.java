package ru.job4j.control;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест класса Vacancies.
 */
public class VacanciesTest {
    /**
     * Получить все вакансии с форума за год.
     * @throws MalformedURLException MalformedURLException.
     */
    @Test
    public void getAllVacancies() throws MalformedURLException {
        Vacancies vacancies = new Vacancies(new URL("http://www.sql.ru/forum/actualsearch.aspx?search=Java~Script~JavaScript&sin=1&bid=66&a=&ma=0&dt"), 365);
        List<Vacancy> allVacancies = vacancies.getAllVacancies();
        for (Vacancy vacancy : allVacancies) {
            System.out.println(vacancy);
        }
        assertThat(allVacancies.isEmpty(), is(false));
    }
}
