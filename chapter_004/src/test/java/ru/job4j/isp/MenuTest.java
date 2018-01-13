package ru.job4j.isp;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Menu test.
 * @author aeremeev.
 * @version 1
 * @since 13.01.2018
 */
public class MenuTest {
    /**
     * Menu.
     */
    private final Menu menu = new Menu();
    /**
     * Line separator.
     */
    private final String separator = System.getProperty("line.separator");

    /**
     * Fill menu.
     */
    @Before
    public void fillMenu() {
        menu.addItem(new Item("1.1.1", "test 1.1.1"));
        menu.addItem(new Item("1.1.2", "test 1.1.2"));
        menu.addItem(new Item("1.1", "test 1.1"));
        menu.addItem(new Item("1.2", "test 1.2"));
        menu.addItem(new Item("1", "test 1"));
        menu.addItem(new Item("2.1.1", "test 2.1.1"));
        menu.addItem(new Item("2.1", "test 2.1"));
        menu.addItem(new Item("2.2", "test 2.2"));
        menu.addItem(new Item("2", "test 2"));
    }

    /**
     * Test of menu view.
     */
    @Test
    public void whenPrintMenuThenGetCorrectMenuView() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        menu.view();
        StringBuilder expected = new StringBuilder();
        expected.append("-1 test 1").append(separator);
        expected.append("---1.1 test 1.1").append(separator);
        expected.append("-----1.1.1 test 1.1.1").append(separator);
        expected.append("-----1.1.2 test 1.1.2").append(separator);
        expected.append("---1.2 test 1.2").append(separator);
        expected.append("-2 test 2").append(separator);
        expected.append("---2.1 test 2.1").append(separator);
        expected.append("-----2.1.1 test 2.1.1").append(separator);
        expected.append("---2.2 test 2.2").append(separator);

        assertThat(output.toString(), is(expected.toString()));
    }

    /**
     * Test of correct input to menu.
     */
    @Test
    public void whenSelectCorrectMenuItemThenGetItemExecuted() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        String select = "1.1.2";
        menu.select(select);

        StringBuilder expected = new StringBuilder();
        expected.append("Executed item 1.1.2").append(separator);

        assertThat(output.toString(), is(expected.toString()));
    }

    /**
     * Test of incorrect input to menu.
     */
    @Test
    public void whenSelectIncorrectMenuItemThenGetErrorMessage() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        String select = "5.r.r.0.r";
        menu.select(select);
        StringBuilder expected = new StringBuilder();
        expected.append("Error, no such item in this menu").append(separator);
        assertThat(output.toString(), is(expected.toString()));
    }
}