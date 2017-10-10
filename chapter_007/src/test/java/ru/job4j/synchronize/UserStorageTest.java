package ru.job4j.synchronize;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты класса UserStorage.
 */
public class UserStorageTest {
    /**
     * Запускает 2 треда и присоединяет к основному.
     * @param first первый тред.
     * @param second второй тред.
     */
    public void runThreads(Thread first, Thread second) {
        try {
            first.start();
            second.start();
            first.join();
            second.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    /**
     * Тест добавления пользователей в хранилище.
     */
    @Test
    public void whenAddNewUsersWithSameIDThenGetOneUser() {
        UserStorage storage = new UserStorage();
        User userFirst = new User(1, 100);
        User userSecond = new User(1, 50);

        Thread firstThread = new Thread() {
            @Override
            public void run() {
                storage.add(userFirst);
            }
        };
        Thread secondThread = new Thread() {
            @Override
            public void run() {
                storage.add(userSecond);
            }
        };
        runThreads(firstThread, secondThread);

        List<User> users = storage.getUsers();

        assertThat(users.contains(userFirst), is(true));
    }

    /**
     * Тест обновления пользователя в хранилище.
     */
    @Test
    public void whenUpdateUserInStorageThenGetUpdated() {
        UserStorage storage = new UserStorage();
        User userFirst = new User(1, 100);
        User userFirstUpdated = new User(1, 150);
        Thread firstThread = new Thread() {
            @Override
            public void run() {
                storage.add(userFirst);
            }
        };
        Thread secondThread = new Thread() {
            @Override
            public void run() {
                storage.update(userFirstUpdated);
            }
        };
        runThreads(firstThread, secondThread);

        List<User> users = storage.getUsers();

        assertThat(users.contains(userFirstUpdated), is(true));
    }

    /**
     * Тест удаления пользователя из хранлища.
     */
    @Test
    public void whenDeleteUserFromStorageThenResultIsTrue() {
        UserStorage storage = new UserStorage();
        User user = new User(1, 100);
        Thread firstThread = new Thread() {
            @Override
            public void run() {
                storage.add(user);
            }
        };
        Thread secondThread = new Thread() {
            @Override
            public void run() {
                storage.delete(user);
            }
        };
        runThreads(firstThread, secondThread);
        List<User> users = storage.getUsers();

        assertThat(users.isEmpty(), is(true));
    }

    /**
     * Тест перевода сумм между пользователями.
     */
    @Test
    public void whenTransferBetweenUsersThenRecieveUpdatedUsers() {
        UserStorage storage = new UserStorage();
        storage.add(new User(1, 100));
        storage.add(new User(2, 50));
        Thread firstThread = new Thread() {
            @Override
            public void run() {
                storage.transfer(1, 2, 50);
            }
        };
        Thread secondThread = new Thread() {
            @Override
            public void run() {
                storage.transfer(2, 1, 30);
            }
        };
        runThreads(firstThread, secondThread);
        List<User> users = storage.getUsers();

        User firstExpected = new User(1, 80);
        User secondExpected = new User(2, 70);

        assertThat(users.contains(firstExpected) && users.contains(secondExpected), is(true));
    }
}
