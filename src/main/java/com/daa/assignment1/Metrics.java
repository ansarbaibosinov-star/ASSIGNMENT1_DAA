package com.daa.assignment1;

public class Metrics {

    private long comparisons;
    private int maxDepth;

    private long startTime;
    private long elapsedTime;

    public Metrics() {
        reset();
    }

    public void incrementComparisons() {
        comparisons++;
    }

    public long getComparisons() {
        return comparisons;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void updateDepth(int depth) {
        if (depth > maxDepth) {
            maxDepth = depth;
        }
    }

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        elapsedTime = System.nanoTime() - startTime;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public double getElapsedTimeMillis() {
        return elapsedTime / 1_000_000.0;
    }

    public void reset() {
        comparisons = 0;
        maxDepth = 0;
        startTime = 0;
        elapsedTime = 0;
    }
}