package com.daa.assignment1;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PlotGenerator {

    public static void main(String[] args) {

        String fileName = "results.csv";

        File plotsFolder = new File("plots");

        if (!plotsFolder.exists()) {
            plotsFolder.mkdirs();
        }

        try {
            generateTimeChart(fileName);
            generateDepthChart(fileName);
            generateRatioChart(fileName);

            System.out.println("All plots generated successfully.");
            System.out.println("Saved in: plots/");

        } catch (IOException e) {
            System.err.println("Error while generating plots.");
            e.printStackTrace();
        }
    }



    private static void generateTimeChart(String fileName)
            throws IOException {

        XYSeries mergeRandom =
                new XYSeries("MergeSort - random");

        XYSeries mergeSorted =
                new XYSeries("MergeSort - sorted");

        XYSeries mergeDuplicates =
                new XYSeries("MergeSort - duplicates");

        XYSeries quickRandom =
                new XYSeries("QuickSort - random");

        XYSeries quickSorted =
                new XYSeries("QuickSort - sorted");

        XYSeries quickDuplicates =
                new XYSeries("QuickSort - duplicates");

        XYSeries selectRandom =
                new XYSeries("QuickSelect - random");

        XYSeries selectSorted =
                new XYSeries("QuickSelect - sorted");

        XYSeries selectDuplicates =
                new XYSeries("QuickSelect - duplicates");

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(fileName))) {

            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                String algorithm = parts[0];
                String input = parts[1];

                int n = Integer.parseInt(parts[2]);
                double time = Double.parseDouble(parts[3]);

                if (algorithm.equals("MergeSort")) {

                    if (input.equals("random")) {
                        mergeRandom.add(n, time);
                    } else if (input.equals("sorted")) {
                        mergeSorted.add(n, time);
                    } else if (input.equals("duplicates")) {
                        mergeDuplicates.add(n, time);
                    }

                } else if (algorithm.equals("QuickSort")) {

                    if (input.equals("random")) {
                        quickRandom.add(n, time);
                    } else if (input.equals("sorted")) {
                        quickSorted.add(n, time);
                    } else if (input.equals("duplicates")) {
                        quickDuplicates.add(n, time);
                    }

                } else if (algorithm.equals("QuickSelect")) {

                    if (input.equals("random")) {
                        selectRandom.add(n, time);
                    } else if (input.equals("sorted")) {
                        selectSorted.add(n, time);
                    } else if (input.equals("duplicates")) {
                        selectDuplicates.add(n, time);
                    }
                }
            }
        }

        XYSeriesCollection dataset = new XYSeriesCollection();

        dataset.addSeries(mergeRandom);
        dataset.addSeries(mergeSorted);
        dataset.addSeries(mergeDuplicates);

        dataset.addSeries(quickRandom);
        dataset.addSeries(quickSorted);
        dataset.addSeries(quickDuplicates);

        dataset.addSeries(selectRandom);
        dataset.addSeries(selectSorted);
        dataset.addSeries(selectDuplicates);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Sorting and Selection Performance",
                "Input size (n)",
                "Time (ms)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartUtils.saveChartAsPNG(
                new File("plots/time_vs_n.png"),
                chart,
                1200,
                700
        );

        System.out.println("Created: plots/time_vs_n.png");
    }

    // ---------------------------------------------------------
    // MAX DEPTH VS N
    // ---------------------------------------------------------

    private static void generateDepthChart(String fileName)
            throws IOException {

        XYSeries mergeSort =
                new XYSeries("MergeSort");

        XYSeries quickSort =
                new XYSeries("QuickSort");

        XYSeries quickSelect =
                new XYSeries("QuickSelect");

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(fileName))) {

            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                String algorithm = parts[0];

                int n = Integer.parseInt(parts[2]);
                int depth = Integer.parseInt(parts[5]);

                /*
                 * For each algorithm/input/n there are several rows.
                 * Here we use the depth values directly.
                 */

                if (algorithm.equals("MergeSort")) {
                    mergeSort.add(n, depth);

                } else if (algorithm.equals("QuickSort")) {
                    quickSort.add(n, depth);

                } else if (algorithm.equals("QuickSelect")) {
                    quickSelect.add(n, depth);
                }
            }
        }

        XYSeriesCollection dataset = new XYSeriesCollection();

        dataset.addSeries(mergeSort);
        dataset.addSeries(quickSort);
        dataset.addSeries(quickSelect);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Maximum Recursion Depth",
                "Input size (n)",
                "Maximum depth",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartUtils.saveChartAsPNG(
                new File("plots/depth_vs_n.png"),
                chart,
                1200,
                700
        );

        System.out.println("Created: plots/depth_vs_n.png");
    }

    // ---------------------------------------------------------
    // RATIO VS N
    // ---------------------------------------------------------

    private static void generateRatioChart(String fileName)
            throws IOException {

        XYSeries mergeSort =
                new XYSeries("MergeSort comparisons / n log2(n)");

        XYSeries quickSort =
                new XYSeries("QuickSort comparisons / n log2(n)");

        XYSeries quickSelect =
                new XYSeries("QuickSelect comparisons / n");

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(fileName))) {

            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                String algorithm = parts[0];

                int n = Integer.parseInt(parts[2]);
                long comparisons = Long.parseLong(parts[4]);

                double ratio;

                if (algorithm.equals("QuickSelect")) {

                    ratio = (double) comparisons / n;

                    quickSelect.add(n, ratio);

                } else {

                    double log2n =
                            Math.log(n) / Math.log(2);

                    ratio =
                            comparisons /
                                    (n * log2n);

                    if (algorithm.equals("MergeSort")) {
                        mergeSort.add(n, ratio);
                    } else if (algorithm.equals("QuickSort")) {
                        quickSort.add(n, ratio);
                    }
                }
            }
        }

        XYSeriesCollection dataset = new XYSeriesCollection();

        dataset.addSeries(mergeSort);
        dataset.addSeries(quickSort);
        dataset.addSeries(quickSelect);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Asymptotic Ratio Check",
                "Input size (n)",
                "Comparison ratio",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartUtils.saveChartAsPNG(
                new File("plots/ratio_vs_n.png"),
                chart,
                1200,
                700
        );

        System.out.println("Created: plots/ratio_vs_n.png");
    }
}