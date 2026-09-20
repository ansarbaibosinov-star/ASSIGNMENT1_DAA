package com.daa.assignment1;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int[] array = {8, 3, 5, 1, 9, 2, 7};

        Metrics mergeMetrics = new Metrics();

        MergeSort.sort(array, mergeMetrics);

        System.out.println("MergeSort:");
        System.out.println(Arrays.toString(array));
        System.out.println("Comparisons: " + mergeMetrics.getComparisons());
        System.out.println("Max depth: " + mergeMetrics.getMaxDepth());


        int[] quickArray = {8, 3, 5, 1, 9, 2, 7};

        Metrics quickMetrics = new Metrics();

        QuickSort.sort(quickArray, quickMetrics);

        System.out.println();
        System.out.println("QuickSort:");
        System.out.println(Arrays.toString(quickArray));
        System.out.println("Comparisons: " + quickMetrics.getComparisons());
        System.out.println("Max depth: " + quickMetrics.getMaxDepth());


        int[] selectArray = {8, 3, 5, 1, 9, 2, 7};

        Metrics selectMetrics = new Metrics();

        int result = QuickSelect.select(selectArray, 3, selectMetrics);

        System.out.println();
        System.out.println("QuickSelect:");
        System.out.println("k = 3");
        System.out.println("Result: " + result);
    }
}