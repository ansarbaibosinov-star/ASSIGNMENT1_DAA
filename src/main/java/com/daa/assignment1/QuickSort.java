package com.daa.assignment1;

import java.util.Random;

public class QuickSort {

    private static final Random RANDOM = new Random();

    public static void sort(int[] array, Metrics metrics) {
        if (array == null || array.length <= 1) {
            return;
        }

        quickSort(array, 0, array.length - 1, metrics, 1);
    }

    private static void quickSort(
            int[] array,
            int left,
            int right,
            Metrics metrics,
            int depth) {

        while (left < right) {

            metrics.updateDepth(depth);

            int pivotIndex = left + RANDOM.nextInt(right - left + 1);
            int pivot = array[pivotIndex];

            int[] bounds = partition(array, left, right, pivot, metrics);

            int lessEnd = bounds[0];
            int greaterStart = bounds[1];

            int leftSize = lessEnd - left;
            int rightSize = right - greaterStart;

            if (leftSize < rightSize) {

                if (left < lessEnd - 1) {
                    quickSort(array, left, lessEnd - 1, metrics, depth + 1);
                }

                left = greaterStart;

            } else {

                if (greaterStart + 1 < right) {
                    quickSort(array, greaterStart + 1, right, metrics, depth + 1);
                }

                right = lessEnd - 1;
            }
        }
    }

    public static int[] partition(
            int[] array,
            int left,
            int right,
            int pivot,
            Metrics metrics) {

        int less = left;
        int current = left;
        int greater = right;

        while (current <= greater) {

            metrics.incrementComparisons();

            if (array[current] < pivot) {

                swap(array, less, current);
                less++;
                current++;

            } else {

                metrics.incrementComparisons();

                if (array[current] > pivot) {
                    swap(array, current, greater);
                    greater--;
                } else {
                    current++;
                }
            }
        }

        return new int[]{less, greater};
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}