package ru.job4j.exchange;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест для класса CoinExchange.
 */
public class CoinExchangeTest {
    /**
     * Тест размена 12 монетами 1, 5, 10. Должно получиться четыре комбинации.
     */
    @Test
    public void thenExchangingTwelveThenPrintFourValidCombination() {
        ArrayList<String> result = new ArrayList<>();
        CoinExchange coinExchange = new CoinExchange(new int[] {1, 5, 10}, result);
        coinExchange.findExchangeCombinations(12, 0, 0, new ArrayList<>(), result);
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]", "[1, 1, 1, 1, 1, 1, 1, 5]", "[1, 1, 5, 5]", "[1, 1, 10]"));

        assertThat(result, is(expected));
    }
}
