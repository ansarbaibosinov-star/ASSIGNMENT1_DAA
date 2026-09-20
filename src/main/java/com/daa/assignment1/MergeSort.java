package com.daa.assignment1;

public class MergeSort {

    private static final int CUTOFF = 15;

    public static void sort(int[] array, Metrics metrics) {
        if (array == null || array.length <= 1) {
            return;
        }

        int[] buffer = new int[array.length];

        mergeSort(array, buffer, 0, array.length - 1, metrics, 1);
    }

    private static void mergeSort(
            int[] array,
            int[] buffer,
            int left,
            int right,
            Metrics metrics,
            int depth) {

        metrics.updateDepth(depth);

        if (left >= right) {
            return;
        }

        int size = right - left + 1;

        if (size <= CUTOFF) {
            InsertionSort.sort(array, left, right, metrics);
            return;
        }

        int middle = left + (right - left) / 2;

        mergeSort(array, buffer, left, middle, metrics, depth + 1);
        mergeSort(array, buffer, middle + 1, right, metrics, depth + 1);

        merge(array, buffer, left, middle, right, metrics);
    }

    private static void merge(
            int[] array,
            int[] buffer,
            int left,
            int middle,
            int right,
            Metrics metrics) {

        int i = left;
        int j = middle + 1;
        int k = left;

        while (i <= middle && j <= right) {

            metrics.incrementComparisons();

            if (array[i] <= array[j]) {
                buffer[k] = array[i];
                i++;
            } else {
                buffer[k] = array[j];
                j++;
            }

            k++;
        }

        while (i <= middle) {
            buffer[k] = array[i];
            i++;
            k++;
        }

        while (j <= right) {
            buffer[k] = array[j];
            j++;
            k++;
        }

        for (int index = left; index <= right; index++) {
            array[index] = buffer[index];
        }
    }
}