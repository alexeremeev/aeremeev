package ru.job4j.loop;
/**
 * Class Paint - рисование пирамиды с помощью "^".
 * @author aeremeev.
 * @since 29.08.2017.
 * @version 1.
 */
public class Paint {
    /**
     * Метод для рисования пирамиды с помощью StringBuilder.
     * @param h высота пирамиды.
     * @return строка с пирамидой.
     */
    public String piramid(int h) {
        StringBuilder builder = new StringBuilder();
        final String line = System.getProperty("line.separator");
        final int width = 2 * h - 1;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < (width / 2) - i; j++) {
                builder.append(" ");
            }
            for (int j = (width / 2) - i; j <= (width / 2) + i; j++) {
                builder.append("^");
            }
            for (int j = (width / 2) + i + 1; j < width; j++) {
                builder.append(" ");
            }
            builder.append(line);
        }
        return builder.toString();
    }

}
