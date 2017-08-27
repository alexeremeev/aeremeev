package ru.job4j.loop;
/**
 * Class Factorial - подсчет факториала целого положительного числа.
 * @author aeremeev.
 * @since 27.08.2017.
 * @version 1.
 */

public class Factorial {
    /**
     * Подсчет факториала для числа n, для 0 равен 1.
     * @param n число, для которого считается факториал.
     * @return факториал n.
     */
    public int calc(int n) {
        int result = 1;
        if (n == 0) {
            return result;
        } else {
            for (int i = 1; i <= n; i++) {
                result = result * i;
            }
        }
        return result;
    }
}
