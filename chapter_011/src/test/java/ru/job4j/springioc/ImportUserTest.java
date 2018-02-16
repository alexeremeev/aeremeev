package ru.job4j.springioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.springioc.model.User;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Import user test.
 * @author aeremeev.
 * @version 1
 * @since 16.02.2018
 */
public class ImportUserTest {
    /**
     * Test of adding user to memory storage based user import.
     */
    @Test
    public void whenAddUserThenGetUsername() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config-test.xml");
        ImportUser testImport = (ImportUser) context.getBean("import");

        assertNotNull(testImport);

        testImport.add(new User("Alex"));

        assertThat(testImport.getAll().get(0).getName(), is("Alex"));
    }

}