package ru.job4j.waitnotify;

/**
 * class SimpleLock - простая реализация механизма блокировок Lock.
 */
public class SimpleLock {
    /**
     * Изначально Lock не занят ни одним тредом, константа -1.
     */
    //CHECKSTYLE.OFF
    private final static long FREE = -1;
    //CHECKSTYLE.ON
    /**
     * Изначальный владелец Lock.
     */
    private long owner = FREE;

    /**
     * Установка блокировки.
     * @throws InterruptedException InterruptedException.
     * @throws IllegalStateException IllegalStateException.
     */
    public synchronized void lock() throws InterruptedException, IllegalStateException {
        throwIf(currentOwner(), "Lock is already in place!");
        long threadId = Thread.currentThread().getId();
        while (isLocked()) {
            logger("waiting for lock thread", threadId);
            wait();
        }
        owner = threadId;
        logger("locked, owner = ", owner);
    }

    /**
     * Снятие блокировки.
     * @throws IllegalStateException IllegalStateException.
     */
    public synchronized void unlock() throws IllegalStateException {
        throwIf(!isLocked(), "lock is free, nothing to unlock");
        throwIf(!currentOwner(), "only owner can unlock this lock");
        logger("unlocking, owner = ", owner);
        owner = FREE;
        notify();
    }

    /**
     * Проверка установленой блокировки.
     * @return true, если установлена.
     */
    private synchronized boolean isLocked() {
        return owner != FREE;
    }

    /**
     * Проверка треда, установившего блокировку.
     * @return true, если текущий ID треда совпал с ID, установившим блокировку.
     */
    private boolean currentOwner() {
        return owner == Thread.currentThread().getId();
    }

    /**
     * Метод выбрасывает исключение IllegalStateException.
     * @param condition условие выбрасывания.
     * @param message сообщение в консоль.
     * @throws IllegalStateException IllegalStateException.
     */
    private void throwIf(boolean condition, String message) throws IllegalStateException {
        if (condition) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * Логгер.
     * @param message сообщение.
     * @param threadId ID треда.
     */
    private void logger(String message, long threadId) {
        System.out.println(String.format("SimpleLock: %s %s", message, threadId));
    }
}
