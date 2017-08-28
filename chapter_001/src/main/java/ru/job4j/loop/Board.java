package ru.job4j.loop;
/**
 * Class Board - рисование шахматной доски с помощью "х" и " ".
 * @author aeremeev.
 * @since 28.08.2017.
 * @version 1.
 */
public class Board {
    /**
     * Метод для рисования доски в строке с помощью StringBuilder.
     * @param width ширина доски
     * @param height высота доски
     * @return строка с доской.
     */

    public String paint(int width, int height) {
        final String line = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < height; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < width; j++) {
                    if (j % 2 == 0) {
                        builder.append("x");
                    } else {
                        builder.append(" ");
                    }
                }
                builder.append(line);
            } else {
                for (int j = 0; j < width; j++) {
                    if (j % 2 == 0) {
                        builder.append(" ");
                    } else {
                        builder.append("x");
                    }
                }
                builder.append(line);
            }
        }
        return builder.toString();
    }
}
