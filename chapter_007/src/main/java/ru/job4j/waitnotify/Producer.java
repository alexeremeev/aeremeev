package ru.job4j.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * class Producer - реализация шаблона Producer - Consumer с использованием wait(), notify().
 */
@ThreadSafe
public class Producer extends Thread {
    /**
     * Максимальный размер очереди.
     */
    //CHECKSTYLE.OFF
    private final static int MAX_QUEUE = 5;
    //CHECKSTYLE.ON
    /**
     * Очередь сообщений.
     */
    private final Queue<String> messages = new LinkedList<>();

    @Override
    public void run() {
        try {
            while (true) {
                sendMessage();
                sleep(100);
            }
        } catch (InterruptedException ie) {
            System.out.println("Producer interrupted!");
        }
    }

    /**
     * Метод добавляет сообщение в очередь.
     * @throws InterruptedException сообщение в консоль.
     */
    @GuardedBy("this")
    private synchronized void sendMessage() throws InterruptedException {
        while (messages.size() == MAX_QUEUE) {
            wait();
        }
        messages.add(new java.util.Date().toString());
        System.out.println("Sending message!");
        notify();

    }

    /**
     * Метод получает сообщение из очереди.
     * @return сообщение из очереди.
     * @throws InterruptedException сообщение в консоль.
     */
    @GuardedBy("this")
    public synchronized String getMessage() throws InterruptedException {
        notify();
        while (messages.size() == 0) {
            wait();
        }
        String message = messages.poll();
        return message;
    }

    /**
     * class Consumer.
     */
    public class Consumer extends Thread {
        /**
         * Принимает как параметр объект Producer.
         */
        private Producer producer;

        /**
         * Конструктор.
         * @param producer объект Producer.
         */
        public Consumer(Producer producer) {
            this.producer = producer;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String message = producer.getMessage();
                    System.out.println("Got message: " + message);
                    sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println("Consumer interrupted!");
            }
        }
    }

    /**
     * Main.
     * @param args args.
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long timer = 10000;
        Producer producer = new Producer();
        Consumer consumer = new Producer().new Consumer(producer);
        producer.start();
        consumer.start();
        while (producer.isAlive() || consumer.isAlive()) {
            if (System.currentTimeMillis() - start > timer) {
                producer.interrupt();
                consumer.interrupt();
            }
        }
    }
}