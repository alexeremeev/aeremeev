package ru.job4j.map;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * class Directory<K, V> простая реализация HashMap, справочник.
 * @param <K> дженерик ключа.
 * @param <V> дженерик значения.
 */
public class Directory<K, V> implements Iterable<K> {
    /**
     * Массив внутреннего хранилища.
     */
    private Object[][] container;
    /**
     * Размер массива.
     */
    private int size;

    /**
     * Конструктор.
     * @param size размер внутреннего хранилища.
     */
    public Directory(int size) {
        this.size = size;
        this.container = new Object[size][2];
    }

    /**
     * Метод вычисляет хэшфункию.
     * @param key ключ.
     * @return вычисленное значение хэшфункии.
     */
    private int hash(K key) {
        int code = key.hashCode();
        return Math.abs(this.size * code % this.size);
    }

    /**
     * Вставка пары ключ-значение в хранилище.
     * @param key ключ.
     * @param value значение.
     * @return true, если пара вставлена, false, если такой ключ уже есть.
     */
    public boolean insert(K key, V value) {
        boolean result = false;
        int hCode = hash(key);
        if (container[hCode][0] == null) {
            container[hCode][0] = key;
            container[hCode][1] = value;
            result = true;
        }
        return result;
    }

    /**
     * Метод получает значение по ключу.
     * @param key ключ.
     * @return значение.
     */
    public V get(K key) {
        int hCode = hash(key);
        if (container[hCode][0] != null) {
            return (V) container[hCode][1];
        } else {
            throw new NoSuchElementException("No such key in Directory!");
        }

    }

    /**
     * Удаление пары ключ-значения по ключу.
     * @param key ключ.
     * @return true, если пара найдена и удалена.
     */
    public boolean delete(K key) {
        boolean result = false;
        int hCode = hash(key);
        if (container[hCode] != null) {
            container[hCode][0] = null;
            container[hCode][1] = null;
            result = true;
        }
        return result;
    }

    /**
     * Итератор по ключу.
     * @return итератор.
     */
    @Override
    public Iterator<K> iterator() {
        Iterator<K> it = new Iterator<K>() {
            private int currentCell = 0;

            private Object[] getArraysOfKeys() {
                Object[] keys = new Object[size];
                int currentIndex = 0;
                for (int index = 0; index != size; index++) {
                    if (container[index][0] != null) {
                        keys[currentIndex++] = container[index][0];
                    }
                }
                return keys;
            }
            @Override
            public boolean hasNext() {
                return getArraysOfKeys()[currentCell] != null;
            }

            @Override
            public K next() {
                if (hasNext()) {
                    return (K) getArraysOfKeys()[currentCell++];
                } else {
                    throw new NoSuchElementException("No such element in Directory!");
                }
            }
        };
        return it;
    }
}
