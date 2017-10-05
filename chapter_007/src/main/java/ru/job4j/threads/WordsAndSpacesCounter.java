package ru.job4j.threads;

/**
 * class WordsAndSpacesCounter - считает слова и пробелы в тексте.
 */
public class WordsAndSpacesCounter {
    /**
     * Текст.
     */
    private String text;

    /**
     * Конструктор.
     * @param text текст.
     */
    public WordsAndSpacesCounter(String text) {
        this.text = text;
    }

    /**
     * Метод считает пробелы в тексте.
     * @return число пробелов.
     */
    public int countSpaces() {
        char[] spaces = this.text.toCharArray();
        int count = 0;
        for (Character character : spaces) {
            if (character == ' ') {
                count++;
            }
        }
        return count;
    }

    /**
     * Метод считает число слов в тексте.
     * @return число слов.
     */
    public int countWords() {
        String trim = this.text.trim();
        if (trim.isEmpty()) {
            return 0;
        } else {
            String[] words = trim.split("\\s+");
            return words.length;
        }
    }

    /**
     * Основной метод для демонстрации нескольких потоков.
     * @param args аругменты.
     */
    public static void main(String[] args) {
        System.out.println("Start!");
        StringBuilder builder = new StringBuilder();
        builder.append("Кадзуо Исигуро родился в 1954 году в Нагасаки.");
        builder.append(" В 1960 году его семья переехала в Великобританию. Исигуро написал семь романов.");
        builder.append(" Его «Остаток дня» (1989 год) был удостоен Букеровской премии и экранизирован.");
        builder.append(" Все романы Исигуро, включая «Погребенного великана», переведены на русский язык.");

        WordsAndSpacesCounter counter = new WordsAndSpacesCounter(builder.toString());
        new Thread() {
            @Override
            public void run() {
                int words = counter.countWords();
                System.out.println(words);
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                int spaces = counter.countSpaces();
                System.out.println(spaces);
            }
        }.start();
        System.out.println("Finish!");
    }
}
