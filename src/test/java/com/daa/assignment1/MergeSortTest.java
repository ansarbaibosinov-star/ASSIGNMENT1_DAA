package com.daa.assignment1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    void shouldSortRandomArrays() {

        Random random = new Random(42);

        for (int test = 0; test < 100; test++) {

            int size = random.nextInt(100) + 1;

            int[] array = new int[size];

            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(1000);
            }

            int[] expected = array.clone();

            Arrays.sort(expected);

            Metrics metrics = new Metrics();

            MergeSort.sort(array, metrics);

            assertArrayEquals(expected, array);
        }
    }

    @Test
    void shouldHandleEmptyArray() {

        int[] array = {};

        MergeSort.sort(array, new Metrics());

        assertArrayEquals(new int[]{}, array);
    }

    @Test
    void shouldHandleOneElement() {

        int[] array = {5};

        MergeSort.sort(array, new Metrics());

        assertArrayEquals(new int[]{5}, array);
    }

    @Test
    void shouldHandleEqualElements() {

        int[] array = {7, 7, 7, 7, 7};

        MergeSort.sort(array, new Metrics());

        assertArrayEquals(
                new int[]{7, 7, 7, 7, 7},
                array
        );
    }

    @Test
    void shouldHandleSortedArray() {

        int[] array = {1, 2, 3, 4, 5, 6};

        MergeSort.sort(array, new Metrics());

        assertArrayEquals(
                new int[]{1, 2, 3, 4, 5, 6},
                array
        );
    }
}