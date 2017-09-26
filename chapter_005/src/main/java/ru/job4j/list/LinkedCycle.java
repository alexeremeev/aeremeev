package ru.job4j.list;

/**
 * class LinkedCycle - проверка связного списка на закольцованность.
 */
public class LinkedCycle {
    /**
     * class Node<T> - нода.
     * @param <T> дженерик.
     */
    public static class Node<T> {
        /**
         * Значение элемента ноды.
         */
        private T value;
        /**
         * Ссылка на следующую ноду.
         */
        private Node<T> next;

        /**
         * Сеттер следующей ноды.
         * @param next следующая нода.
         */
        public void setNext(Node<T> next) {
            this.next = next;
        }

        /**
         * Конструктор.
         * @param value значение элемента.
         */
        public Node(T value) {
            this.value = value;
        }

    }

    /**
     * Проверка на цикличность списка.
     * @param start стартовая нода.
     * @return true, если список цикличен.
     */
    public boolean hasCycle(Node start) {
        Node current = start;
        Node currentDouble = start.next;
        boolean exist = false;
        while (current.next != null) {
            current = current.next;
            currentDouble = currentDouble.next;
            currentDouble = currentDouble.next;
            if (current.equals(currentDouble)) {
                if (current.next.equals(start) && currentDouble.next.equals(start)) {
                    exist = true;
                }
                break;
            }
        }
        return exist;
    }

}
