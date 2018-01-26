package ru.job4j.softcache;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SoftCache - cache based on Map interface and soft references.
 * @author aeremeev.
 * @version 1
 * @since 25.01.2018
 */
public class SoftCache {
    /**
     * References map.
     */
    private Map<String, SoftReference<String>> cache;
    /**
     * Path.
     */
    private Path path;

    /**
     * Constructor.
     * @param filePath path to cache files.
     */
    public SoftCache(String filePath) {
        this.cache = new HashMap<>();
        this.path = Paths.get(filePath);
    }

    /**
     * Get value from cache.
     * @param key filename.
     * @return string representation of file.
     */
    public String getValue(String key) {
        String result;
        if (this.cache.containsKey(key)) {
            if (this.cache.get(key) != null) {
                result = this.cache.get(key).get();
            } else {
                this.addValue(key);
                result = this.cache.get(key).get();
            }
        } else {
            this.addValue(key);
            result = this.cache.get(key).get();
        }
        return result;
    }

    /**
     * Add file to cache.
     * @param filename filename.
     */
    public void addValue(String filename) {
        Path filepath = Paths.get(String.format("%s/%s", this.path, filename));
        StringBuilder builder = new StringBuilder();
        try {
            List<String> list = Files.readAllLines(filepath);
            for (String string: list) {
                builder.append(string).append(System.getProperty("line.separator"));
            }
            this.cache.put(filename, new SoftReference<>(builder.toString()));
        } catch (IOException ioe) {
            System.out.println("No such file.");
        }
    }
}
