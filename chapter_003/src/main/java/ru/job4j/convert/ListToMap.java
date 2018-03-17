package ru.job4j.convert;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * class ListToMap - конвертация списка в карту.
 */
public class ListToMap {
    /**
     * Метод конветрации списка в карту.
     * @param list входной список.
     * @return карта, где ключом является Id пользователя.
     */
    public Map<Integer, User> process(List<User> list) {
        return list.stream().collect(Collectors.toMap(User::getId, Function.identity()));
    }
}
