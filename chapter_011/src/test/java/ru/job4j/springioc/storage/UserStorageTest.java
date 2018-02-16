package ru.job4j.springioc.storage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.springioc.model.User;

import static org.junit.Assert.*;
/**
 * User storage test with different spring beans.
 * @author aeremeev.
 * @version 1
 * @since 15.02.2018
 */
public class UserStorageTest {

    @Test
    public void whenAddUserThenGetConsolePrint() {
        UserStorage storage = new UserStorage(new MemoryStorage());
        storage.add(new User("Alex"));
    }

    @Test
    public void whenUseSpringContextThenGetBeans() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        Storage memory = context.getBean(MemoryStorage.class);
        assertNotNull(memory);
        UserStorage storage = new UserStorage(memory);
        storage.add(new User("Alexander"));
    }

    @Test
    public void whenUseBeanWithConstructorThenGetCorrectNewObject() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        UserStorage storage = context.getBean(UserStorage.class);
        assertNotNull(storage);
        storage.add(new User("Aleksandr"));
    }

}