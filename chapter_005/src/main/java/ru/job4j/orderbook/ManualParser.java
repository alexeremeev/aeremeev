package ru.job4j.orderbook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

/**
 * class ManualParser - парсер XML файла orders.xml на основе регулярных выражений.
 */
public class ManualParser {
    /**
     * Пусть к XML файлу orders.xml.
     */
    private String filepath;

    /**
     * Конструктор.
     * @param filepath пусть к XML файлу orders.xml.
     */
    public ManualParser(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Метод возвращает экземпляр класса Order, полученный из строки файла.
     * @param line строка файла XML.
     * @return экземпляр класса Order.
     */
    private Order getOrder(String line) {
        Order order;
        boolean start = false;
        int pos = -1;
        String[] values = new String[5];
        int current = 0;
        for (int index = 0; index != line.length(); index++) {
            if (line.charAt(index) == '"') {
                if (!start) {
                    start = true;
                    pos = index;
                } else {
                    start = false;
                    values[current++] = line.substring(pos + 1, index);
                }
            }
        }
        if (current == 5) {
            order = new Order(values[0], values[1],
                    Float.valueOf(values[2]), Integer.valueOf(values[3]),
                    Integer.valueOf(values[4]));
        } else {
            order = new Order(values[0], "Delete", 0f, 0, Integer.valueOf(values[1]));
        }
        return order;
    }

    /**
     * Метод парсинга строки на основе тэга AddOrder и DeleteOrder.
     * Добавляет или удаляет экземпляр Order в сет orders.
     * @param line строка файла XML.
     * @param orders сет заявок Order.
     */
    private void parse(String line, TreeSet<Order> orders) {
        if (line.startsWith("<A")) {
            orders.add(getOrder(line));
        } else if (line.startsWith("<D")) {
            orders.remove(getOrder(line));
        }
    }

    /**
     * Метод инициализации парсера.
     * @return сет заявок Order.
     */
    public TreeSet<Order> init() {
        TreeSet<Order> orders = new TreeSet<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                parse(line, orders);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return orders;
    }

}
