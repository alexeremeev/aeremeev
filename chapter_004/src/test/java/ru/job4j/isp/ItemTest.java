package ru.job4j.isp;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Item test.
 * @author aeremeev.
 * @version 1
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
        Item test = new Item("1.2.3", "Test item");
        test.execute();

        StringBuilder expected = new StringBuilder();
        expected.append("Executed item 1.2.3").append(System.getProperty("line.separator"));

        assertThat(output.toString(), is(expected.toString()));
    }

}