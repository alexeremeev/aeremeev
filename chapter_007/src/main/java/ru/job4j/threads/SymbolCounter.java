package ru.job4j.threads;

/**
 * class SymbolCounter - подсчет символов в строке.
 */
public class SymbolCounter {
    /**
     * Поток подсчета символов.
     */
    private Thread count;
    /**
     * Поток проверки времени работы.
     */
    private Thread time;
    /**
     * Максимальное время работы Thread count в мс.
     */
    private long timer;

    /**
     * Конструктор.
     * @param text текст для обработки.
     * @param symbol символ для подсчета.
     * @param timer максимальное время обработки в мс.
     */
    public SymbolCounter(String text, char symbol, long timer) {
        this.count = new Thread(new SymbolCounter.CountChar(text, symbol));
        this.time = new Thread(new SymbolCounter.Time());
        this.timer = timer;
    }

    /**
     * Инициализация потоков.
     */
    public void init() {
        System.out.println("Start");
        try {
            count.start();
            count.join(1000);
            time.start();
            time.join();
        } catch (InterruptedException ie) {
            System.out.println("Interrupted in run!");
        }
        System.out.println("Finish");

    }

    /**
     * class Time - контроль времени выполнения программы.
     */
    public class Time implements Runnable {
        /**
         * Точка отсчета.
         */
        private long start = System.currentTimeMillis();

        @Override
        public void run() {
            while (count != null && count.isAlive()) {
                if (System.currentTimeMillis() - start > timer) {
                    System.out.println("Too long, interrupting!");
                    count.interrupt();
                    break;
                }
            }
        }
    }

    /**
     * class CountChar - подчет символов в строке.
     */
    public class CountChar implements Runnable {
        /**
         * Строка с текстом.
         */
        private String text;
        /**
         * Символ для поиска и подсчета.
         */
        private char symbol;

        /**
         * Конструктор.
         * @param text строка с текстом.
         * @param symbol символ для поиска и подсчета.
         */
        public CountChar(String text, char symbol) {
            this.text = text;
            this.symbol = symbol;
        }

        @Override
        public void run() {
                try {
                    Thread.sleep(2000);
                    char[] array = this.text.toCharArray();
                    int counter = 0;
                    for (Character symbol : array) {
                        if (symbol == this.symbol) {
                            counter++;
                        }
                    }
                    System.out.println("Количество символов -" + this.symbol + "- равно " + counter);
                } catch (InterruptedException ie) {
                    System.out.println("I wasn't done!");
                }

            }

    }

    /**
     * Main.
     * @param args args.
     */
    public static void main(String[] args) {
        new SymbolCounter("Все романы Исигуро, включая «Погребенного великана»,"
                + " переведены на русский язык.", 'и', 1000L).init();
    }


}
