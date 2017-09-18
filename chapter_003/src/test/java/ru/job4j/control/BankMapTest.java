package ru.job4j.control;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса BankMap.
 */
public class BankMapTest {
    /**
     * Тест добавления нового клиента.
     */
    @Test
    public void tryAddUserToBank() {
        BankMap bankMap = new BankMap();
        User user = new User("John Doe", 123987);
        bankMap.addUser(user);
        assertThat(bankMap.getUsers().contains(user), is(true));
    }
    /**
     * Тест удаления клиента.
     */
    @Test
    public void tryDeleteUserFromBank() {
        BankMap bankMap = new BankMap();
        User user = new User("John Doe", 123987);
        bankMap.addUser(user);
        bankMap.deleteUser(user);
        assertThat(bankMap.getUsers().contains(user), is(false));
    }
    /**
     * Тест добавления нового счета клиенту.
     */
    @Test
    public void tryAddAccountToUser() {
        BankMap bankMap = new BankMap();
        User user = new User("John Doe", 123987);
        Account deposit = new Account(100000D, 1);
        bankMap.addUser(user);
        bankMap.addAccountToUser(user, deposit);
        assertThat(bankMap.getUserAccounts(user).contains(deposit), is(true));
    }
    /**
     * Тест удаления счета у клиенту.
     */
    @Test
    public void tryDeleteAccountFromUser() {
        BankMap bankMap = new BankMap();
        User user = new User("John Doe", 123987);
        Account deposit = new Account(100000D, 1);
        bankMap.addUser(user);
        bankMap.addAccountToUser(user, deposit);
        bankMap.deleteAccountFromUser(user, deposit);
        assertThat(bankMap.getUserAccounts(user).contains(deposit), is(false));
    }

    /**
     * Тест запроса всех счетов клиента.
     */
    @Test
    public void tryGetAccountsFromUser() {
        BankMap bankMap = new BankMap();
        User user = new User("John Doe", 123987);
        Account deposit = new Account(100000D, 1);
        Account pension = new Account(10000D, 2);
        bankMap.addUser(user);
        bankMap.addAccountToUser(user, deposit);
        bankMap.addAccountToUser(user, pension);
        List<Account> expected = new ArrayList<>();
        expected.addAll(Arrays.asList(deposit, pension));
        assertThat(bankMap.getUserAccounts(user), is(expected));
    }
    /**
     * Тест перевода между клиентами банка.
     */
    @Test
    public void tryTranferMoneyFromOneUserToAnother() {
        BankMap bankMap = new BankMap();
        User johnDoe = new User("John Doe", 123987);
        Account deposit = new Account(100000D, 1);
        User janeDoe = new User("Jane Doe", 987123);
        Account pension = new Account(10000D, 2);
        bankMap.addUser(johnDoe);
        bankMap.addAccountToUser(johnDoe, deposit);
        bankMap.addUser(janeDoe);
        bankMap.addAccountToUser(janeDoe, pension);
        bankMap.transferMoney(johnDoe, deposit, janeDoe, pension, 50000D);
        assertThat(pension.getValue(), is(60000D));
    }

}
