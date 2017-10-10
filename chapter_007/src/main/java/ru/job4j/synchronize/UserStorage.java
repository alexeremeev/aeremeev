package ru.job4j.synchronize;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Хранилище User.
 */
@ThreadSafe
public class UserStorage {
    /**
     * Карта для хранения User.
     */
    private Map<Integer, User> storage = new HashMap<>();

    /**
     * Добавить User.
     * @param user User.
     * @return true, если добавлен.
     */
    @GuardedBy("this")
    public synchronized boolean add(User user) {
        boolean result = false;
        if (!storage.containsKey(user.getId())) {
            storage.put(user.getId(), user);
            result = true;
        }
        return result;
    }

    /**
     * Обновить User.
     * @param user User.
     * @return true, если найден и обновлен.
     */
    @GuardedBy("this")
    public synchronized boolean update(User user) {
        boolean result = false;
        if (storage.containsKey(user.getId())) {
            storage.put(user.getId(), user);
            result = true;
        }
        return result;
    }

    /**
     * Удалить User.
     * @param user User.
     * @return true, если найден и удален.
     */
    @GuardedBy("this")
    public synchronized boolean delete(User user) {
        boolean result = false;
        if (storage.containsKey(user.getId())) {
            storage.remove(user.getId());
            result = true;
        }
        return result;
    }

    /**
     * Перевести сумму между User.
     * @param fromId ID отправителя.
     * @param toId ID получателя.
     * @param amount сумма.
     * @return true, если успешно выполнено.
     */
    @GuardedBy("this")
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        if (storage.containsKey(fromId) && storage.containsKey(toId)) {
            if (storage.get(fromId).getAmount() > amount) {
                int temp = storage.get(fromId).getAmount();
                storage.replace(fromId, new User(fromId, temp - amount));
                temp = storage.get(toId).getAmount();
                storage.replace(toId, new User(toId, temp + amount));
                result = true;
            }
        }
        return result;
    }

    /**
     * Получить список всех пользователей в UserStorage.
     * @return список всех пользователей в UserStorage.
     */
    @GuardedBy("this")
    public synchronized List<User> getUsers() {
        List<User> users = new ArrayList<User>(storage.values());
        return users;
    }
}
