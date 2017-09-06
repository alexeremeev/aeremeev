package ru.job4j.tracker;

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
}
