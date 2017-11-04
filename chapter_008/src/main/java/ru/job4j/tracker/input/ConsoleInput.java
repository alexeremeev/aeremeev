package ru.job4j.tracker.input;

import ru.job4j.tracker.MenuOutException;

import java.util.Scanner;

/**
 * Class ConsoleInput - ввод с консоли, использую интерфейс Input.
 */
public class ConsoleInput implements Input {
    /**
     * Scanner для считывания данных из консоли.
     */
    //CHECKSTYLE.OFF
    Scanner scanner = new Scanner(System.in);
    //CHECKSTYLE.

    /**
     * Реализация метода ask интерфейса Input.
     * @param question описание запрашиваемого значения.
     * @return пользовательский ввод.
     */
    public String ask(String question) {

        System.out.print(question);
        return scanner.nextLine();
    }

    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value: range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Out of menu range.");
        }
    }
}
