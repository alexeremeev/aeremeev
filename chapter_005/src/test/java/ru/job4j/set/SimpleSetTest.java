package ru.job4j.set;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест для класса SimpleSet.
 */
public class SimpleSetTest {
    /**
     * Добавление нескольких элементов с попыткой добавить дубль.
     */
    @Test
    public void whenAddToSimpleSetThenPrintNoDoubles() {
        SimpleSet<Integer> simpleSet = new SimpleSet<>();

        simpleSet.add(33);
        simpleSet.add(11);
        simpleSet.add(22);

        boolean addDouble = simpleSet.add(11);

        for (Integer value : simpleSet) {
            System.out.println(value);
        }
        assertThat(addDouble, is(false));
    }

    /**
     * Тест времени добавления 10000 рандомных чисел в Set с сортировкой.
     */
    @Test
    public void whenUsingBinarySearchContainsWorksFaster() {
        Random rn = new Random();
        SimpleSet<Integer> simpleSet = new SimpleSet<>();
        SimpleSet<Integer> fastSimpleSet = new SimpleSet<>();

        double slowTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            simpleSet.add(rn.nextInt());
        }
        slowTime = System.currentTimeMillis() - slowTime;
        System.out.println(slowTime);

        double fastTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            fastSimpleSet.addBinary(rn.nextInt());
        }
        fastTime = System.currentTimeMillis() - fastTime;
        System.out.println(fastTime);

        assertThat(fastTime < slowTime, is(true));
    }
}
