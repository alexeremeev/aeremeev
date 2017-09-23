package ru.job4j.exchange;

import java.util.ArrayList;
import java.util.List;

/**
 * class CoinExchange - размен суммы монетами.
 * Используется рекурсия с обратным путем по жадному алгоритму.
 * Изначально преполагается самая худшая выдача самымм мелкими монетами.
 * Постепенно пытаемся улучшить результат, используя предыдущий.
 */
public class CoinExchange {
    /**
     * Массив разменных монет.
     */
    private int[] coins;
    /**
     * Список всех возможных комбинаций размена.
     */
    private ArrayList<String> list = new ArrayList<>();

    /**
     * Конструктор.
     * @param coins массив разменных монет.
     * @param list список всех возможных валидных комбинаций размена.
     */
    public CoinExchange(int[] coins, ArrayList<String> list) {
        this.coins = coins;
        this.list = list;

    }

    /**
     * Выдачи вариантов размена суммы с использованием рекурсивного вызова.
     * @param target сумма, которую нужно разменять.
     * @param total текущая сумма монет, которую удалось собрать.
     * @param currentCoin позиция в массиве разменных монет, с которой начинается подбор комбинации монет.
     * @param combo список монет текущей комбинации.
     * @param list список всех возможных валидных комбинаций размена.
     */
    public void findExchangeCombinations(int target, int total, int currentCoin, List<Integer> combo, List<String> list) {
        for (int index = currentCoin; index != coins.length; index++) {

            int coin = coins[index];
            total += coin;

            if (total > target) {
                return;
            }

            combo.add(coin);

            if (total == target) {
                list.add(combo.toString());
                combo.remove(combo.size() - 1);
                return;
            }


            findExchangeCombinations(target, total, currentCoin, combo, list);

            combo.remove(combo.size() - 1);
            total -= coin;
            currentCoin++;
        }
    }

}
