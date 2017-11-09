package ru.job4j.control;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * class Vacancies - собирает все вакансии по JAVA с SQL.ru.
 */
public class Vacancies {
    /**
     * Ссылка на страницу поиска.
     */
    private URL url;
    /**
     * Запрашиваемый период в днях.
     */
    private int period;

    /**
     * Конструктор.
     * @param url ссылка на страницу поиска.
     * @param period запрашиваемый период в днях.
     */
    public Vacancies(URL url, int period) {
        this.url = url;
        this.period = period;
    }

    /**
     * Получить все вакансии.
     * @return список всех вакансий.
     */
    public List<Vacancy> getAllVacancies() {
        List<Vacancy> vacancies = new LinkedList<>();
        URL periodUrl = this.changePeriod(this.url, this.period);
        List<URL> searchPages = this.getSearchPages(periodUrl);

        for (URL url : searchPages) {
            vacancies.addAll(this.getVacancies(url));
        }
        return vacancies;
    }

    /**
     * Модифицировать ссылку, изменить период запроса.
     * @param url ссылка с пустым периодом запроса.
     * @param period период запроса.
     * @return измененная ссылка.
     */

    private URL changePeriod(URL url, int period) {
        String sKey = "&s";
        URL result = null;
        try {
            result = new URL(String.format("%s%s%d%s", url, "=", period, sKey));
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        }
        return result;
    }

    /**
     * Получить все вакансии с одной страницы выдачи поиска.
     * @param url ссылка на страницу.
     * @return список вакансий.
     */
    private List<Vacancy> getVacancies(URL url) {

        List<Vacancy> vacancies = new LinkedList<>();

        try {
            Document document = Jsoup.connect(url.toString()).get();
            Elements topics = document.getElementsByAttributeValue("class", "postslisttopic");

            if (topics.size() != 0) {
                for (Element element : topics) {
                    String topicUrl = element.child(0).attr("href");
                    String topicDesc = element.text();

                    vacancies.add(new Vacancy(new URL(topicUrl), topicDesc));
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return vacancies;
    }

    /**
     * Получить все ссылки на страницы с результатами поиска.
     * @param firstUrl ссылка на первую страницу поиска.
     * @return список всех ссылок на страницы с результатами поиска.
     */
    private List<URL> getSearchPages(URL firstUrl) {

        List<URL> searchResults = new LinkedList<>();
        searchResults.add(firstUrl);

        try {
            Document document = Jsoup.connect(firstUrl.toString()).get();
            Elements forumMenu = document.getElementsByAttributeValue("class", "forummenu");
            String menu = forumMenu.select("a").get(0).attr("href");

            Elements forumSearchResults = document.getElementsByAttributeValue("class", "forumtable_results");

            if (forumSearchResults.size() != 0) {
                Element forumSearchResultElement = forumSearchResults.get(1);

                for (Element element : forumSearchResultElement.getElementsByAttribute("href")) {
                    searchResults.add(new URL(String.format("%s/%s", menu, element.attr("href"))));
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return searchResults;
    }
}
