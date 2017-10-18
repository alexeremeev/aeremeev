package ru.job4j.nonblocking;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты класса NonBlockingCache.
 */
public class NonBlockingCacheTest {
    //CHECKSTYLE.OFF
    /**
     * Получаем количество тредов CPU.
     */
    private static final int CPU_THREADS = Runtime.getRuntime().availableProcessors();
    //CHECKSTYLE.ON
    /**
     * Тред пул.
     */
   private final ExecutorService executorService = Executors.newFixedThreadPool(CPU_THREADS);

    /**
     * Исполняем 2 треда.
     * @param first первый тред.
     * @param second второй тред.
     */
    public void executeThreads(Thread first, Thread second) {
        try {
            executorService.execute(first);
            executorService.execute(second);
            executorService.shutdown();
            executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    /**
     * Тест добавления задачи.
     */
    @Test
    public void whenAddNewTasksThenGetTasksInList() {
        NonBlockingCache cache = new NonBlockingCache();
        Task first = new Task(1, "First", "Test");
        Task second = new Task(2, "Second", "Test");
        Thread threadFirst = new Thread(() -> { cache.add(first); });
        Thread threadSecond = new Thread(() -> { cache.add(second); });
        executeThreads(threadFirst, threadSecond);
        List<Task> tasks = cache.getTasks();
        List<Task> expected = new ArrayList<>(Arrays.asList(first, second));
        assertThat(tasks, is(expected));
    }

    /**
     * Тест удаления задачи.
     */
    @Test
    public void whenRemoveTaskFromCacheThenListIsEmpty() {
        NonBlockingCache cache = new NonBlockingCache();
        Task first = new Task(1, "First", "Test");
        Thread threadFirst = new Thread(() -> { cache.add(first); });
        Thread threadSecond = new Thread(() -> { cache.remove(first); });
        executeThreads(threadFirst, threadSecond);
        List<Task> tasks = cache.getTasks();
        assertThat(tasks.isEmpty(), is(true));
    }

    /**
     * Демонстарция обновления задачи с выбрасываением OptimisticLockException.
     */
    @Test
    public void updateTest() {
        NonBlockingCache cache = new NonBlockingCache();
        Task first = new Task(1, "First", "Test");
        cache.add(first);


        for (int index = 0; index != 200; index++) {
            Task update = new Task(1, "First" + index, "Test");
            Task updateSecond = new Task(1, "First" + index * 2, "Test");
            executorService.execute(new Thread(() -> { cache.update(update); }));
            executorService.execute(new Thread(() -> { cache.update(updateSecond); }));

        }
        List<Task> tasks = cache.getTasks();
        System.out.println(tasks);
    }

}
