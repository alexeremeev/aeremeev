package ru.job4j.jmm;

/**
 * class ProblemsExample - демонстрация Race Condition.
 * 2 потока пытаются увеличить значение счетчика counter,
 * периодически один поток прерывается и выполняется другой.
 */
public class ProblemsExample implements Runnable {
    /**
     * Счетчик.
     */
    private static int counter = 0;

    @Override
    public void run() {
        try {
            for (int index = 0; index != 200; index++) {
                increase();
                Thread.sleep(100);
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    /**
     * Записываем счетчик в локальную переменную треда, выводим на консоль и увеличиваем.
     */
    public static void increase() {
        int localCounter = counter;
        System.out.println(Thread.currentThread().getName() + " " + counter);
        counter = localCounter + 1;
    }

    /**
     * Main.
     * @param args args.
     */
    public static void main(String[] args) {
        new Thread(new ProblemsExample()).start();
        new Thread(new ProblemsExample()).start();
    }
}
