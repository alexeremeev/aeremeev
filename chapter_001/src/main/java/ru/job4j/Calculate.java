package ru.job4j;

/**
 * Class Класс для вывода строки в консоль.
 * @author aeremeev
 * @since 23.08.2017
 * @version 1
 */

public class Calculate {
  /**.
   * Вывод строки в консоль
   * @param args аргументы
   */
  public static void main(String[] args) {
    System.out.println("Hello World!");
  }


  /**
   * Method echo.
   * @param name Your name.
   * @return Echo plus your name.
   */
  public String echo(String name) {
    return "Echo, echo, echo : " + name;
  }
}