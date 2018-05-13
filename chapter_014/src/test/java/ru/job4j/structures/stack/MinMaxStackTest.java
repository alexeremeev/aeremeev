package ru.job4j.structures.stack;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinMaxStackTest {

    @Test
    public void whenAddValuesToStackWhenPopLast() {
        MinMaxStack<Integer> stack = new MinMaxStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertTrue(stack.pop() == 3);
        assertTrue(stack.size() == 2);
    }
    @Test
    public void whenPeekValueFromStackThenSizeNotChange() {
        MinMaxStack<Integer> stack = new MinMaxStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertTrue(stack.peek() == 3);
        assertTrue(stack.size() == 3);
    }

    @Test
    public void whenPeekMinAndMaxThenGetCorrectValues() {
        MinMaxStack<Integer> stack = new MinMaxStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(10);
        stack.push(-10);
        assertTrue(stack.min() == -10);
        assertTrue(stack.max() == 10);
    }


    @Test
    public void whenChangeStackSizeThenGetCorrectMinMaxValues() {
        MinMaxStack<Integer> stack = new MinMaxStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(10);
        stack.push(-10);

        stack.pop();
        stack.pop();

        assertTrue(stack.min() == 1);
        assertTrue(stack.max() == 3);
    }
}