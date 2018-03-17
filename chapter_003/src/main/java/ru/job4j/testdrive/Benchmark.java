package ru.job4j.testdrive;

import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;

/**
 * class Benchmark - тестрирование производительности коллеций.
 * ArrayList, LinkedList, TreeSet тестируются на добавление и удаление N строк.
 */
public class Benchmark {
    /**
     * Метод добавления amount количества элементов в коллекцию.
     * @param collection коллекция.
     * @param amount колличество элементов.
     * @return время выполнения операции в мс.
     */
    public long add(Collection<String> collection, int amount) {
        long start = System.currentTimeMillis();
        IntStream.range(0, amount).forEach(value -> collection.add(Integer.toString(value)));
        return System.currentTimeMillis() - start;
    }
    /**
     * Метод удаления amount количества элементов из коллекции.
     * @param collection коллекция.
     * @param amount колличество элементов.
     * @return время выполнения операции в мс.
     */
    public long delete(Collection<String> collection, int amount) {
        long start = System.currentTimeMillis();
        IntStream.range(0, amount).forEach(value -> collection.remove(Integer.toString(value)));
        return System.currentTimeMillis() - start;
    }

    /**
     * Основной метод для программы, т.к. я изначально не знаю времени этих операций и не применяю JUnit.
     * @param args аргументы.
     */
    public static void main(String[] args) {
        Benchmark benchmark = new Benchmark();
        List<String> arList = new ArrayList<>();
        List<String> lkList = new LinkedList<>();
        Set<String> tSet = new TreeSet<>();
        System.out.format("Time to add 1000000 strings to ArrayList is: %s ms \n",
                benchmark.add(arList, 1000000));
        System.out.format("Time to add 1000000 strings to LinkedList is: %s ms \n",
                benchmark.add(lkList, 1000000));
        System.out.format("Time to add 1000000 strings to TreeSet is: %s ms \n",
                benchmark.add(tSet, 1000000));
        System.out.format("Time to remove 10000 strings from ArrayList is: %s ms \n",
                benchmark.delete(arList, 10000));
        System.out.format("Time to remove 10000 strings from LinkedList is: %s ms \n",
                benchmark.delete(lkList, 10000));
        System.out.format("Time to remove 10000 strings from TreeSet is: %s ms \n",
                benchmark.delete(tSet, 10000));
    }
}
