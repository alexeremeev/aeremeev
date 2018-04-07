package ru.job4j.structures.arrays;

import org.junit.Test;

import static org.junit.Assert.*;

public class UniqueCharTest {

    @Test
    public void whenUseUniqueSequenceThenResultTrue() {
        String original = "Здание";
        assertTrue(new UniqueChar().isUnique(original.toCharArray()));
        System.out.println(new UniqueChar().isUnique(original.toCharArray()));
    }

    @Test
    public void whenUseNonUniqueSequenceThenResultFalse() {
        String original = "Уникальный";
        assertFalse(new UniqueChar().isUnique(original.toCharArray()));
    }

}