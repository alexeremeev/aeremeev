package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * class OddCheck - проверка числа на честность.
 */
public class OddCheck {

    /**
     * @param in входной поток.
     * @return true, если число четное.
     */
    public boolean isNumber(InputStream in) {
        boolean result = false;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String input;
        int number;
        try {
            System.out.println("Please enter integer number:");
            input = reader.readLine();
            number = Integer.parseInt(input);
            if (number % 2 == 0) {
                System.out.println(String.format("%s is odd number.", number));
                result = true;
            } else {
                System.out.println(String.format("%s is even number.", number));
                result = false;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }
}
