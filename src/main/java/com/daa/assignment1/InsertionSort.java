package com.daa.assignment1;

public class InsertionSort {

    public static void sort(int[] array, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {

            int key = array[i];
            int j = i - 1;

            while (j >= left) {

                metrics.incrementComparisons();

                if (array[j] <= key) {
                    break;
                }

                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = key;
        }
    }
}