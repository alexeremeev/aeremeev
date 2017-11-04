package ru.job4j.tracker.input;

import ru.job4j.tracker.MenuOutException;

/**
 * Консольный ввод с проверкой исключений.
 */
public class ValidateInput extends ConsoleInput {
    /**
     * Реализация метода ask интерфейса Input.
     * @param question описание запрашиваемого значения.
     * @param range массив номеров пунктов меню.
     * @return пользовательский ввод.
     */
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please select key from menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter valid data again.");
            }
        } while (invalid);
        return value;
    }
}
