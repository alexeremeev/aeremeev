package ru.job4j.convert;

import java.util.HashMap;
import java.util.List;

/**
 * class ListToMap - конвертация списка в карту.
 */
public class ListToMap {
    /**
     * Метод конветрации списка в карту.
     * @param list входной список.
     * @return карта, где ключом является Id пользователя.
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> map = new HashMap<>();
        for (User user : list) {
            map.put(user.getId(), user);
        }
        return map;
    }
}
