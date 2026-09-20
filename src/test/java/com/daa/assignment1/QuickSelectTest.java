package com.daa.assignment1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class QuickSelectTest {

    @Test
    void shouldFindKthSmallestInRandomArrays() {

        Random random = new Random(42);

        for (int test = 0; test < 100; test++) {

            int size = random.nextInt(100) + 1;

            int[] array = new int[size];

            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(1000);
            }

            int[] sorted = array.clone();

            Arrays.sort(sorted);

            int k = random.nextInt(size);

            Metrics metrics = new Metrics();

            int result =
                    QuickSelect.select(
                            array,
                            k,
                            metrics
                    );

            assertEquals(sorted[k], result);
        }
    }

    @Test
    void shouldRejectEmptyArray() {

        int[] array = {};

        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(
                        array,
                        0,
                        new Metrics()
                )
        );
    }

    @Test
    void shouldRejectNegativeK() {

        int[] array = {1, 2, 3};

        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(
                        array,
                        -1,
                        new Metrics()
                )
        );
    }

    @Test
    void shouldRejectTooLargeK() {

        int[] array = {1, 2, 3};

        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(
                        array,
                        3,
                        new Metrics()
                )
        );
    }

    @Test
    void shouldFindMinimum() {

        int[] array = {5, 2, 9, 1, 7};

        int result =
                QuickSelect.select(
                        array,
                        0,
                        new Metrics()
                );

        assertEquals(1, result);
    }

    @Test
    void shouldFindMaximum() {

        int[] array = {5, 2, 9, 1, 7};

        int result =
                QuickSelect.select(
                        array,
                        4,
                        new Metrics()
                );

        assertEquals(9, result);
    }
}