package ru.job4j.threads;

/**
 * class WordsAndSpacesCounter - считает слова и пробелы в тексте.
 */
public class WordsAndSpacesCounter implements Runnable {
    /**
     * Текст.
     */
    private String text;
    /**
     * Маркер, что считать.
     */
    private String whatToCount;


    /**
     * Конструктор.
     * @param text текст.
     * @param whatToCount маркер, что считать.
     */
    public WordsAndSpacesCounter(String text, String whatToCount) {
        this.text = text;
        this.whatToCount = whatToCount;
    }

    @Override
    public void run() {
            if (this.whatToCount.equals("Spaces")) {
                char[] spaces = text.toCharArray();
                int count = 0;
                for (Character character : spaces) {
                    if (character == ' ') {
                        count++;
                    }
                }
                System.out.println(count);
            } else if (this.whatToCount.equals("Words")) {
                String trim = text.trim();
                if (trim.isEmpty()) {
                    System.out.println(0);
                } else {
                    String[] words = trim.split("\\s+");
                    System.out.println(words.length);
                }
            }
        }

      /**
     * Основной метод для демонстрации нескольких потоков.
     * @param args аругменты.
     */
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        builder.append("Кадзуо Исигуро родился в 1954 году в Нагасаки.");
        builder.append(" В 1960 году его семья переехала в Великобританию. Исигуро написал семь романов.");
        builder.append(" Его «Остаток дня» (1989 год) был удостоен Букеровской премии и экранизирован.");
        builder.append(" Все романы Исигуро, включая «Погребенного великана», переведены на русский язык.");

        Thread threadWords = new Thread(new WordsAndSpacesCounter(builder.toString(), "Words"));
        Thread threadSpaces = new Thread(new WordsAndSpacesCounter(builder.toString(), "Spaces"));
        System.out.println("Start");
        try {
            threadSpaces.start();
            threadSpaces.join(2000);
            threadWords.start();
            threadWords.join();
        } catch (InterruptedException iee) {
            System.out.println("Interrupt");
        }

        System.out.println("Finish");

    }


}
