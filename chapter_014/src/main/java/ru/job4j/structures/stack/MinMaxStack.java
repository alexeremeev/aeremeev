package ru.job4j.structures.stack;

import ru.job4j.structures.linked.SimpleLinkedList;

public class MinMaxStack<E extends Comparable<? super E>> {

    private SimpleLinkedList<Entry> stack = new SimpleLinkedList<>();

    public void push(E value) {
        Entry entry = new Entry(value);
        if (stack.size() == 0) {
            entry.max = value;
            entry.min = value;
        } else {
            E currentMax = this.stack.getHeadElement().max;
            E currentMin = this.stack.getHeadElement().min;
            entry.max = currentMax.compareTo(value) > 0 ? currentMax : value;
            entry.min = currentMin.compareTo(value) < 0 ? currentMin : value;

        }
        this.stack.addToHead(entry);
    }

    public E pop() {
        E result = null;
        if (this.stack.size() != 0) {
            Entry resultEntry = this.stack.getHeadElement();
            result = resultEntry.value;
            stack.remove(resultEntry);
        }
        return result;
    }
    public E peek() {
        return this.stack.getHeadElement().value;
    }

    public E max() {
        return this.stack.getHeadElement().max;
    }

    public E min() {
        return this.stack.getHeadElement().min;
    }

    public int size() {
        return this.stack.size();
    }


    private class Entry implements Comparable<Entry> {
        E min;
        E max;
        E value;

        public Entry(E value) {
            this.value = value;
        }

        @Override
        public int compareTo(Entry o) {
            return this.value.compareTo(o.value);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MinMaxStack{");
        sb.append("stack=").append(stack);
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args) {
        MinMaxStack<Integer> stack = new MinMaxStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.max());
        System.out.println(stack.min());
        System.out.println(stack.pop());
        System.out.println(stack.max());
    }
}
