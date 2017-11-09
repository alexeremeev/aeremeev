package ru.job4j.control;

import org.apache.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * class Main - запуск проекта.
 */
public class Main {
    /**
     * Логгер.
     */
    private Logger logger = Logger.getLogger(Main.class);
    /**
     * Настройки settings.
     */
    private Settings settings = new Settings("aggregator.properties");

    /**
     * Инициализация.
     */
    public void init() {
        Database base = new Database();

        try {
            base.setConnection(settings.getSettings("DB_url"), settings.getSettings("DB_username"), settings.getSettings("DB_password"));

            if (base.select(settings.getSettings("SQL_CHECK_TABLE")) == 0) {
               base.execute(settings.getSettings("SQL_CREATE_TABLE"));
            }

            URL url = new URL(settings.getSettings("vacancies_url"));

            int period = base.select(settings.getSettings("SQL_SELECT_PERIOD"));

            List<Vacancy> vacancies = new Vacancies(url, period).getAllVacancies();

            for (Vacancy vacancy : vacancies) {
                String[] fields = new String[]{vacancy.getUrl().toString(), vacancy.getDescription(), vacancy.getUrl().toString()};
                if (base.executeWithArgs(settings.getSettings("SQL_INSERT_VACANCIES"), fields) > 0) {
                    logger.info(vacancy.toString());
                }
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } finally {
            base.endConnection();
        }
    }

    /**
     * Main.
     * @param args args,
     */
    public static void main(String[] args) {
        while (true) {
            Main start = new Main();
            start.init();
            try {
                int minutesToMs = 60000;
                Thread.sleep(minutesToMs * Integer.parseInt(start.settings.getSettings("sleep_timer")));
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
