package ru.job4j.isp;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Item test.
 * @author aeremeev.
 * @version 1.1
 * @since 13.01.2018
 */
public class ItemTest {
    /**
     * Test of item execution.
     */
    @Test
    public void whenExecuteItemThenGetCorrectOutput() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        Item test = new Item("1", "Test item 1");
        test.execute("1");

        StringBuilder expected = new StringBuilder();
        expected.append("Executed item 1").append(System.getProperty("line.separator"));

        assertThat(output.toString(), is(expected.toString()));
    }

    /**
     * Test of sub items execution.
     */
    @Test
    public void whenItemHaveSubItemsThenExecuteSubItem() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        Item test1 = new Item("1", "Test item 1");
        Item test11 = new Item("1.1", "Test item 1.1");
        Item test111 = new Item("1.1.1", "Test item 1.1.1");
        Item test112 = new Item("1.1.2", "Test item 1.1.2");
        test11.addSubItem(test111);
        test11.addSubItem(test112);
        test1.addSubItem(test11);
        test1.execute("1.1.1");
        StringBuilder expected = new StringBuilder();
        expected.append("Executed item 1.1.1").append(System.getProperty("line.separator"));
        assertThat(output.toString(), is(expected.toString()));
    }

}