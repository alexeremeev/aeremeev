package ru.job4j.list;

/**
 * Class SimpleStack - простой стек на базе SimpleLinkedList.
 * @param <E> дженерик.
 */
public class SimpleStack<E> {
    /**
     * Хранилище стека.
     */
   private SimpleLinkedList<E> stack = new SimpleLinkedList<>();

    /***
     * Постановка элемента в стек.
     * @param value элемент.
     */
   public void push(E value) {
       stack.add(value);
   }

    /**
     * Получение последнего добавленного в стек элемента.
     * @return последний добавленный в стек элемент.
     */
   public E poll() {
       Object swap = stack.getTailElement();
       stack.remove((E) swap);
       return (E) swap;
   }

}
