package ru.job4j.waitnotify;

/**
 * class SimpleLockDemo - демонстрация работы SimpleLock.
 */
public class SimpleLockDemo {
    /**
     * Main.
     * @param args args.
     */
    public static void main(String[] args) {
        final Counter counter = new SimpleLockDemo().new Counter();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(String.format("Count at thread %s %s",
                        Thread.currentThread().getId(), counter.increment()));
            }
        };
        for (int index = 0; index != 10; index++) {
            new Thread(runnable).start();
        }
    }

    /**
     * class Counter - счетчик.
     */
   final class Counter {
        /**
         * SimpleLock.
         */
        private final SimpleLock lock = new SimpleLock();
        /**
         * Счетчик.
         */
        private int count = 0;

        /**
         * Увеличивает счечик.
         * @return текущее значение счетчика.
         */
        public int increment() {
            try {
                lock.lock();
                count++;
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            } finally {
                lock.unlock();
            }
            return count;
        }


    }
}
