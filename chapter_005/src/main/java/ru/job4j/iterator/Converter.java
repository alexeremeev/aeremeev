package ru.job4j.iterator;

import java.util.Iterator;

/**
 * class Converter - итератор итераторов.
 */
public class Converter {

    /**
     * Метод convert, перебирает все итераторы по очереди.
     * @param it входные итераторы.
     * @return итератор, который переберет все входные по очереди.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            private Iterator<Integer> currentIterator = null;

            @Override
            public boolean hasNext() {
                selectIterator();
                return currentIterator != null && currentIterator.hasNext();
            }

            @Override
            public Integer next() {
                selectIterator();
                return currentIterator.next();
            }

            private void selectIterator() {
                if (currentIterator == null || !currentIterator.hasNext()) {
                    while (it.hasNext()) {
                        Iterator<Integer> nextIterator = it.next();
                        if (nextIterator.hasNext()) {
                            currentIterator = nextIterator;
                            break;
                        }
                    }
                }
            }
        };
    }
}