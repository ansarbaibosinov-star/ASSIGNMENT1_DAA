package com.daa.assignment1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class Benchmark {

    private static final int[] SIZES = { 1_000,  10_000,  100_000,  1_000_000 };

    private static final int RUNS = 5;

    public static void main(String[] args) {

        String[] algorithms = {
                "MergeSort",
                "QuickSort",
                "QuickSelect"
        };

        String[] inputTypes = {
                "random",
                "sorted",
                "duplicates"
        };

        try (PrintWriter writer = new PrintWriter(new FileWriter("results.csv"))) {

            writer.println(
                    "algorithm,input,n,time_ms,comparisons,max_depth"
            );

            for (String algorithm : algorithms) {

                for (String inputType : inputTypes) {

                    for (int n : SIZES) {

                        System.out.println(
                                algorithm + " | " +
                                        inputType + " | n=" + n
                        );

                        BenchmarkResult result =
                                runBenchmark(
                                        algorithm,
                                        inputType,
                                        n
                                );

                        writer.printf(
                                java.util.Locale.US,
                                "%s,%s,%d,%.4f,%d,%d%n",
                                algorithm,
                                inputType,
                                n,
                                result.timeMs,
                                result.comparisons,
                                result.maxDepth
                        );

                        writer.flush();
                    }
                }
            }

            System.out.println();
            System.out.println(
                    "Benchmark finished successfully."
            );
            System.out.println(
                    "Results saved to results.csv"
            );

        } catch (IOException e) {

            System.err.println(
                    "Could not write results.csv"
            );

            e.printStackTrace();
        }
    }

    private static BenchmarkResult runBenchmark(
            String algorithm,
            String inputType,
            int n) {

        double[] times = new double[RUNS];
        long[] comparisons = new long[RUNS];
        int[] depths = new int[RUNS];

        int[] original = generateArray(inputType, n);

        for (int run = 0; run < RUNS; run++) {

            int[] array = original.clone();

            Metrics metrics = new Metrics();

            metrics.startTimer();

            if (algorithm.equals("MergeSort")) {

                MergeSort.sort(array, metrics);

            } else if (algorithm.equals("QuickSort")) {

                QuickSort.sort(array, metrics);

            } else if (algorithm.equals("QuickSelect")) {

                int k = n / 2;

                QuickSelect.select(
                        array,
                        k,
                        metrics
                );

            } else {

                throw new IllegalArgumentException(
                        "Unknown algorithm: " + algorithm
                );
            }

            metrics.stopTimer();

            times[run] =
                    metrics.getElapsedTimeMillis();

            comparisons[run] =
                    metrics.getComparisons();

            depths[run] =
                    metrics.getMaxDepth();
        }

        Arrays.sort(times);
        Arrays.sort(comparisons);
        Arrays.sort(depths);

        double medianTime =
                times[RUNS / 2];

        long medianComparisons =
                comparisons[RUNS / 2];

        int medianDepth =
                depths[RUNS / 2];

        return new BenchmarkResult(
                medianTime,
                medianComparisons,
                medianDepth
        );
    }

    private static int[] generateArray(
            String inputType,
            int n) {

        int[] array = new int[n];

        Random random = new Random(42);

        if (inputType.equals("random")) {

            for (int i = 0; i < n; i++) {
                array[i] = random.nextInt();
            }

        } else if (inputType.equals("sorted")) {

            for (int i = 0; i < n; i++) {
                array[i] = i;
            }

        } else if (inputType.equals("duplicates")) {

            for (int i = 0; i < n; i++) {
                array[i] = random.nextInt(10);
            }

        } else {

            throw new IllegalArgumentException(
                    "Unknown input type: " + inputType
            );
        }

        return array;
    }

    private static class BenchmarkResult {

        double timeMs;
        long comparisons;
        int maxDepth;

        BenchmarkResult(
                double timeMs,
                long comparisons,
                int maxDepth) {

            this.timeMs = timeMs;
            this.comparisons = comparisons;
            this.maxDepth = maxDepth;
        }
    }
}