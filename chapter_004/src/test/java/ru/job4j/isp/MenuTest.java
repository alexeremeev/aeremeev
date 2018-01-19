package ru.job4j.isp;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Menu test.
 * @author aeremeev.
 * @version 1.1
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
        Item item11 = new Item("1.1", "test 1.1");
        Item item12 = new Item("1.2", "test 1.2");
        Item item1 = new Item("1", "test 1");
        item11.addSubItem(new Item("1.1.1", "test 1.1.1"));
        item11.addSubItem(new Item("1.1.2", "test 1.1.2"));
        item1.addSubItem(item11);
        item1.addSubItem(item12);

        Item item21 = new Item("2.1", "test 2.1");
        Item item22 = new Item("2.2", "test 2.2");
        Item item2 = new Item("2", "test 2");
        item21.addSubItem(new Item("2.1.1", "test 2.1.1"));
        item2.addSubItem(item21);
        item2.addSubItem(item22);

        menu.addItem(item1);
        menu.addItem(item2);
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
        expected.append("---1.2 test 1.2").append(separator).append(separator);
        expected.append("-2 test 2").append(separator);
        expected.append("---2.1 test 2.1").append(separator);
        expected.append("-----2.1.1 test 2.1.1").append(separator);
        expected.append("---2.2 test 2.2").append(separator).append(separator);

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
     * Test of incorrect menu item input.
     */
    @Test
    public void whenSelectIncorrectMenuItemThenGetErrorMessage() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        String select = "2.1.2";
        menu.select(select);
        StringBuilder expected = new StringBuilder();
        expected.append("Error, no such item in this menu").append(separator);
        assertThat(output.toString(), is(expected.toString()));
    }

    /**
     * Test of incorrect input to menu.
     */
    @Test
    public void whenUserInputIsNotMenuKeyThenGetErrorMessage() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        String select = "5.r.r.0.r";
        menu.select(select);
        StringBuilder expected = new StringBuilder();
        expected.append("Error, no such item in this menu").append(separator);
        assertThat(output.toString(), is(expected.toString()));
    }
}