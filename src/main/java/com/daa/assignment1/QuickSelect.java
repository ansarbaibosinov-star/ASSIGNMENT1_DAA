package com.daa.assignment1;

public class QuickSelect {

    public static int select(int[] array, int k, Metrics metrics) {

        if (array == null || array.length == 0) {
            throw new IllegalArgumentException(
                    "Array must not be empty"
            );
        }

        if (k < 0 || k >= array.length) {
            throw new IllegalArgumentException(
                    "k must be between 0 and array.length - 1"
            );
        }

        int left = 0;
        int right = array.length - 1;
        int depth = 1;

        while (left <= right) {

            metrics.updateDepth(depth);

            int pivotIndex =
                    left + (int) (Math.random() * (right - left + 1));

            int pivot = array[pivotIndex];

            int[] bounds =
                    QuickSort.partition(
                            array,
                            left,
                            right,
                            pivot,
                            metrics
                    );

            int lessEnd = bounds[0];
            int greaterStart = bounds[1];

            if (k < lessEnd) {

                right = lessEnd - 1;

            } else if (k > greaterStart) {

                left = greaterStart + 1;

            } else {

                return array[k];
            }

            depth++;
        }

        throw new IllegalStateException("QuickSelect failed");
    }
}