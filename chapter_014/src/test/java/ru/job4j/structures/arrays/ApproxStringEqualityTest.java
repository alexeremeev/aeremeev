package ru.job4j.structures.arrays;

import org.junit.Test;

import static org.junit.Assert.*;

public class ApproxStringEqualityTest {

    @Test
    public void whenCompareStringWithOneLessCharThenResultTrue() {
        assertTrue(new ApproxStringEquality().approxEquality("Привет", "Приве"));
    }

    @Test
    public void whenCompareStringWithOneMoreCharThenResultTrue() {
        assertTrue(new ApproxStringEquality().approxEquality("Привет", "!Привет"));
    }

    @Test
    public void whenCompareStringWithTwoSwappedCharsThenResultTrue() {
        assertTrue(new ApproxStringEquality().approxEquality("Привет", "Приевт"));
    }

    @Test
    public void whenCompareStringWithMoreThanOneDifferenceThenResultFalse() {
        assertFalse(new ApproxStringEquality().approxEquality("Привет", "Приевт!"));
    }
}