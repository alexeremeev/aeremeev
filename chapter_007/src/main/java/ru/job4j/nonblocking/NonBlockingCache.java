package ru.job4j.nonblocking;

import javax.persistence.OptimisticLockException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * class NonBlockingCache - неблокирующий кэш на основе ConcurrentHashMap.
 */
public class NonBlockingCache {
    /**
     * Хранилище Task.
     */
    private final ConcurrentHashMap<Integer, Task> cache = new ConcurrentHashMap<>();

    /**
     * Добавление задачи хранилище.
     * @param task задачи.
     * @return true, если добавлено.
     */
    public boolean add(Task task) {
        this.cache.putIfAbsent(task.getId(), task);
        return true;
    }

    /**
     * Обновление задачи в хранилище.
     * @param task задача.
     * @throws OptimisticLockException OptimisticLockException.
     */
    public void update(Task task) throws OptimisticLockException {
        cache.computeIfPresent(task.getId(), new BiFunction<Integer, Task, Task>() {
            @Override
            public Task apply(Integer integer, Task current) {
                Task result;
                task.setVersion(current.getVersion());
                if (current.getVersion() == task.getVersion()) {
                    task.setVersion(current.getVersion() + 1);
                    result = task;
                } else {
                    throw new OptimisticLockException("Task is already modified by another user!");
                }
                return result;
            }
        });

    }

    /**
     * Удаление задачи из хранилища.
     * @param task задача.
     * @return true, если задача найдена и удалена.
     */
    public boolean remove(Task task) {
        boolean result = false;
        if (cache.containsKey(task.getId())) {
            cache.remove(task.getId());
            result = true;
        }
        return result;
    }

    /**
     * Получение списка всех задач в хранилище.
     * @return список всех задач в хранилище.
     */
    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>(cache.values());
        return tasks;
    }

}
