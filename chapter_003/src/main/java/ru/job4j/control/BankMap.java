package ru.job4j.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * class BankMap - реализация HashMap для банка.
 */
public class BankMap {
    /**
     * HashMap с клиентами и их счетами.
     */
    private Map<User, List<Account>> map = new HashMap<>();

    /**
     * Добавление клиента в банк.
     * @param user клиент.
     */
    public void addUser(User user) {
        map.put(user, new ArrayList<>());
    }

    /**
     * Удаление клиента из банка.
     * @param user клиент.
     */
    public void deleteUser(User user) {
        map.remove(user);
    }

    /**
     * Добавление счета клиенту банка.
     * @param user клиент.
     * @param account счет.
     */
    public void addAccountToUser(User user, Account account) {
        List<Account> list = map.get(user);
        list.add(account);
        map.replace(user, list);
    }

    /**
     * Удаление счета у клиента банка.
     * @param user клиент.
     * @param account счет.
     */
    public void deleteAccountFromUser(User user, Account account) {
        List<Account> list = map.get(user);
        list.remove(account);
        map.replace(user, list);
    }

    /**
     * Запрос списка счетов клиента банка.
     * @param user клиент.
     * @return список счетов.
     */
    public List<Account> getUserAccounts(User user) {
        return map.get(user);
    }

    /**
     * Перевод денег между клиентами банка.
     * @param srcUser отправитель.
     * @param srcAccount счет отправителя.
     * @param dstUser получатель.
     * @param dstAccount счет получателя.
     * @param amount сумма.
     * @return true, если перевод прошел успешно.
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {

        boolean result = false;

        List<Account> listSource = map.get(srcUser);
        List<Account> listDest = map.get(dstUser);

        if (listSource.contains(srcAccount) && listDest.contains(dstAccount) && srcAccount.getValue() > amount) {
            srcAccount.setValue(srcAccount.getValue() - amount);
            dstAccount.setValue(dstAccount.getValue() + amount);
                result = true;
        }
        return result;
    }

    /**
     * Вывод клиентов банка.
     * @return сет клиентов банка.
     */
    public Set<User> getUsers() {
        return map.keySet();
    }


}
