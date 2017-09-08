package ru.job4j.strategy;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты класса Paint.
 */
public class PaintTest {
    /**
     * Тест рисования квадрата.
     */
    @Test
    public void tryDrawSquare() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Paint paint = new Paint();
        paint.draw(new Square());
        final String line = System.getProperty("line.separator");
        String expected = String.format("X X X X X%sX       X%sX       X%sX       X%sX X X X X%s",
                line, line, line, line, line);
        assertThat(out.toString(), is(expected));
    }

    /**
     * Тест рисования треугольника.
     */
    @Test
    public void tryDrawTriangle() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Paint paint = new Paint();
        paint.draw(new Triangle());
        final String line = System.getProperty("line.separator");
        String expected = String.format("  X  %s X X %sX X X%s", line, line, line);
        assertThat(out.toString(), is(expected));
    }
}
