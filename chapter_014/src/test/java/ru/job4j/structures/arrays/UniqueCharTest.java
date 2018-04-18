package ru.job4j.structures.arrays;

import org.junit.Test;

import static org.junit.Assert.*;

public class UniqueCharTest {

    @Test
    public void whenUseUniqueSequenceThenResultTrue() {
        String original = "Здание";
        assertTrue(new UniqueChar().isUnique(original.toCharArray()));
    }

    @Test
    public void whenUseNonUniqueSequenceThenResultFalse() {
        String original = "Уникальный";
        assertFalse(new UniqueChar().isUnique(original.toCharArray()));
    }

    @Test
    public void whenUseSortUniqueSequenceThenResultTrue() {
        String original = "Здание";
        assertTrue(new UniqueChar().isUniqueSort(original.toCharArray()));
    }

    @Test
    public void whenUseNonSortUniqueSequenceThenResultFalse() {
        String original = "Уникальный";
        assertFalse(new UniqueChar().isUniqueSort(original.toCharArray()));
    }

    @Test
    public void whenUseByteUniqueSequenceThenResultTrue() {
        String original = "Здание";
        assertTrue(new UniqueChar().isUniqueByteCheck(original.toCharArray()));
    }

    @Test
    public void whenUseNonByteUniqueSequenceThenResultFalse() {
        String original = "Уникальный";
        assertFalse(new UniqueChar().isUniqueByteCheck(original.toCharArray()));
    }

}