package ru.job4j.waitnotify;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * class ThreadPool - простая реализация ThreadPool.
 */
public class ThreadPool {
    /**
     * Количество тредов.
     */
    private final int threadCount;
    /**
     * Блокирующая очередь.
     */
    private final LinkedBlockingQueue queue;
    /**
     * Массив запущенных тредов.
     */
    private final WorkingThread[] threads;

    /**
     * Коструктор, инцициализация по количеству процессорных потоков.
     */
    public ThreadPool() {
        this.threadCount = Runtime.getRuntime().availableProcessors();
        this.queue = new LinkedBlockingQueue();
        this.threads = new WorkingThread[threadCount];

        for (int i = 0; i != threadCount; i++) {
            threads[i] = new WorkingThread();
            threads[i].start();
        }
    }

    /**
     * Добавление Runnable объекта в очередь для исполнения его метода run().
     * @param work Runnable объект.
     */
    public void execute(Runnable work) {
        synchronized (queue) {
            queue.add(work);
            queue.notify();
        }
    }

    /**
     * Реализация Thread, забирающая объекты из блокирующей очереди.
     */
    private class WorkingThread extends Thread {

        @Override
        public void run() {
            Runnable work;
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException ie) {
                            ie.printStackTrace();
                        }
                    }
                    work = (Runnable) queue.poll();
                }
                try {
                    work.run();
                } catch (RuntimeException re) {
                    re.printStackTrace();
                }
            }
        }
    }

    /**
     * Main.
     * @param args args.
     */
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool();
        for (int index = 0; index != 100; index++) {
            Work work = new Work(index);
            pool.execute(work);
        }
    }
}
