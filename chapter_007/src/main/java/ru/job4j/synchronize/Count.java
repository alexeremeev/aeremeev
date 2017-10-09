package ru.job4j.synchronize;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * class Count - потокобезопасный счетчик.
 */
@ThreadSafe
public class Count {
    /**
     * Начальный счетчик.
     */
    private int count = 0;

    /**
     * Геттер.
     * @return счетчик.
     */
    public int getCount() {
        return count;
    }

    /**
     * Метод инкременирует счетчик count на значение value.
     * @param value значение увеличения.
     * @return увеличенное значение count.
     */
    @GuardedBy("this")
    public synchronized int increment(int value) {
        this.count += value;
        return this.count;
    }

}
