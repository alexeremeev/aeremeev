package ru.job4j.filemanager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * class Settings - загрузчик настроек.
 */
public class Settings {
    /**
     * Properties.
     */
    private Properties properties = new Properties();

    /**
     * Констурктор.
     * @param settingsFilename имя файла настроек.
     */
    public Settings(String settingsFilename) {
        ClassLoader loader = Settings.class.getClassLoader();
        try (InputStream stream = loader.getResourceAsStream(settingsFilename)) {
            this.properties.load(stream);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    /**
     * Получить значение настроек по ключу.
     * @param key ключ.
     * @return значение соответствующей настройки.
     */
    public String getSettings(String key) {
        return this.properties.getProperty(key);
    }

}
