# DAA Assignment 1 — Divide and Conquer & Asymptotic Notations

## Fast Sorting & Selection Engine

This project implements and evaluates three Divide-and-Conquer algorithms for large integer arrays:

* **MergeSort**
* **QuickSort**
* **QuickSelect**

The project was developed for **Design and Analysis of Algorithms — Assignment 1**.

---

## 1. Project Objectives

The main objectives of this project are:

* implement MergeSort, QuickSort and QuickSelect in Java;
* analyze their asymptotic complexity;
* use recurrence relations and the Master Theorem;
* measure execution time, comparisons and recursion depth;
* test algorithm correctness using JUnit 5;
* benchmark different input sizes and input types;
* export benchmark results to CSV;
* generate performance plots;
* compare experimental results with theoretical complexity.

---

## 2. Technologies

The project uses:

* **Java 17**
* **Maven**
* **JUnit 5**
* **JFreeChart**

---

## 3. Algorithms

### MergeSort

The implementation uses:

* Divide-and-Conquer;
* one reusable helper buffer;
* linear-time merge;
* InsertionSort cutoff for subarrays of 15 elements or fewer.

Theoretical complexity:

```text
Best:    Θ(n log n)
Average: Θ(n log n)
Worst:   Θ(n log n)
```

---

### QuickSort

The implementation uses:

* random pivot selection;
* three-way partitioning;
* smaller-side-first recursion;
* a loop for processing the larger side.

The three-way partition creates:

```text
< pivot | = pivot | > pivot
```

This makes QuickSort efficient for arrays containing many duplicate values.

Theoretical complexity:

```text
Best:     Θ(n log n)
Expected: Θ(n log n)
Worst:    O(n²)
```

---

### QuickSelect

QuickSelect finds the `k`-th smallest element without sorting the entire array.

The implementation:

* reuses QuickSort's partition method;
* continues only in the part containing `k`;
* throws `IllegalArgumentException` for invalid input.

Theoretical complexity:

```text
Best:     Θ(n)
Expected: Θ(n)
Worst:    O(n²)
```

---

## 4. Project Structure

```text
DAA_Assignment1
│
├── pom.xml
├── README.md
├── REPORT.md
├── results.csv
│
├── plots
│   ├── time_vs_n.png
│   ├── depth_vs_n.png
│   └── ratio_vs_n.png
│
└── src
    ├── main
    │   └── java
    │       └── com.daa.assignment1
    │           ├── Main.java
    │           ├── Metrics.java
    │           ├── InsertionSort.java
    │           ├── MergeSort.java
    │           ├── QuickSort.java
    │           ├── QuickSelect.java
    │           ├── Benchmark.java
    │           └── PlotGenerator.java
    │
    └── test
        └── java
            └── com.daa.assignment1
                ├── MergeSortTest.java
                ├── QuickSortTest.java
                └── QuickSelectTest.java
```

---

# 5. Requirements

Before running the project, make sure the following software is installed:

* Java 17 or newer;
* Maven.

Check Java:

```bash
java -version
```

Check Maven:

```bash
mvn -version
```

---

# 6. Build the Project

Open a terminal in the project root directory and run:

```bash
mvn clean compile
```

This removes previous build files and compiles the project.

If the project compiles successfully, Maven should display:

```text
BUILD SUCCESS
```

---

# 7. Run Tests

The project uses JUnit 5.

Run all tests with:

```bash
mvn clean test
```

The tests verify:

### Sorting correctness

MergeSort and QuickSort are compared with:

```java
Arrays.sort()
```

using random arrays.

### Edge cases

The tests cover:

* empty arrays;
* one-element arrays;
* already sorted arrays;
* arrays where all elements are equal.

### QuickSort recursion depth

A sorted array with:

```text
n = 100,000
```

is used to check that:

```text
maxDepth <= 2 × log₂(n)
```

### QuickSelect correctness

QuickSelect is tested against the expected:

```text
sorted[k]
```

value on random arrays.

Invalid `k` values and empty arrays are also tested.

---

# 8. Run the Program

The `Main` class provides a simple demonstration of the algorithms.

It can be run from IntelliJ IDEA by opening:

```text
Main.java
```

and pressing the Run button.

It demonstrates:

* MergeSort;
* QuickSort;
* QuickSelect;
* comparison counts;
* recursion depth.

---

# 9. Run the Benchmark

The benchmark evaluates all three algorithms using different input sizes and input types.

The tested sizes are:

```text
1,000
10,000
100,000
1,000,000
```

The tested input types are:

```text
random
sorted
duplicates
```

where duplicate input contains random values from `0` to `9`.

Each case is executed five times and the median execution time is saved.

Run:

```text
Benchmark.java
```

from IntelliJ IDEA.

The benchmark generates:

```text
results.csv
```

with the following columns:

```text
algorithm,input,n,time_ms,comparisons,max_depth
```

---

# 10. Benchmark Results

The benchmark contains:

```text
3 algorithms × 3 input types × 4 sizes = 36 cases
```

Each case is executed five times:

```text
36 × 5 = 180 runs
```

The resulting CSV contains the median time for each case.

Example:

```text
algorithm,input,n,time_ms,comparisons,max_depth
MergeSort,random,1000,0.6302,9523,8
QuickSort,random,1000,0.1598,20287,6
QuickSelect,random,1000,0.0138,5265,15
```

---

# 11. Generate Performance Plots

The project uses JFreeChart to generate three PNG plots.

Run:

```text
PlotGenerator.java
```

The following files will be created:

```text
plots/time_vs_n.png
plots/depth_vs_n.png
plots/ratio_vs_n.png
```

---

## Time vs n

The first graph shows:

```text
Input size (n)
        vs
Execution time (ms)
```

There is one line for each algorithm and input type.

---

## Maximum Recursion Depth vs n

The second graph shows:

```text
Input size (n)
        vs
Maximum recursion depth
```

This graph demonstrates the effect of the recursion strategies used by MergeSort and QuickSort.

---

## Ratio vs n

The third graph is used for the Θ-check.

For MergeSort and QuickSort:

```text
comparisons / (n × log₂(n))
```

For QuickSelect:

```text
comparisons / n
```

If the ratio stays approximately constant as `n` increases, this supports the expected Θ growth.

---

# 12. Metrics

The `Metrics` class records three important measurements.

### Comparisons

The number of comparisons performed by the algorithm.

### Maximum recursion depth

The maximum recursion depth reached during execution.

### Execution time

Execution time is measured using:

```java
System.nanoTime()
```

The benchmark converts the measured time to milliseconds.

The `Metrics` object is passed to the algorithms instead of using global variables.

---

# 13. Experimental Results

The benchmark results are stored in:

```text
results.csv
```

The largest tested input size is:

```text
n = 1,000,000
```

For this input size, the measured results include:

| Algorithm   | Input      | Time (ms) | Comparisons | Max Depth |
| ----------- | ---------- | --------: | ----------: | --------: |
| MergeSort   | random     |   94.9851 |  19,889,337 |        18 |
| MergeSort   | sorted     |   22.6914 |   9,071,040 |        18 |
| MergeSort   | duplicates |   42.0524 |  18,922,524 |        18 |
| QuickSort   | random     |  124.6862 |  41,157,103 |        13 |
| QuickSort   | sorted     |   60.4088 |  40,487,617 |        13 |
| QuickSort   | duplicates |   11.9042 |   5,501,150 |         2 |
| QuickSelect | random     |   13.8425 |   6,915,053 |        27 |
| QuickSelect | sorted     |    2.8555 |   4,743,129 |        25 |
| QuickSelect | duplicates |    9.6466 |   3,903,050 |         4 |

More detailed analysis is available in:

```text
REPORT.md
```

---

# 14. Complexity Summary

| Algorithm     | Best       | Average / Expected  | Worst      |
| ------------- | ---------- | ------------------- | ---------- |
| MergeSort     | Θ(n log n) | Θ(n log n)          | Θ(n log n) |
| QuickSort     | Θ(n log n) | Θ(n log n) expected | O(n²)      |
| QuickSelect   | Θ(n)       | Θ(n) expected       | O(n²)      |
| InsertionSort | Θ(n)       | Θ(n²)               | Θ(n²)      |

---

# 15. Recurrences

### MergeSort

```text
T(n) = 2T(n/2) + Θ(n)
```

Master Theorem:

```text
a = 2
b = 2
f(n) = Θ(n)
```

Result:

```text
Θ(n log n)
```

---

### Balanced QuickSort

```text
T(n) = 2T(n/2) + Θ(n)
```

Master Theorem:

```text
a = 2
b = 2
f(n) = Θ(n)
```

Result:

```text
Θ(n log n)
```

Random pivot gives this complexity in expectation.

---

### Balanced QuickSelect

```text
T(n) = T(n/2) + Θ(n)
```

Master Theorem:

```text
a = 1
b = 2
f(n) = Θ(n)
```

Result:

```text
Θ(n)
```

Only one partition is processed after each step.

---

# 16. Θ Check

The experimental Θ-check uses normalized comparison counts.

For sorting algorithms:

```text
R(n) = comparisons / (n log₂ n)
```

For QuickSelect:

```text
R(n) = comparisons / n
```

The ratio should remain approximately bounded for an algorithm that follows the expected asymptotic growth.

The measured data supports:

```text
MergeSort = Θ(n log n)
QuickSort = Θ(n log n) expected
QuickSelect = Θ(n) expected
```

The exact ratios vary because of input distribution, random pivot selection, comparison-counting details, and implementation overhead.

---

# 17. Git Workflow

The assignment requires a clean Git workflow.

The main branch should contain only working code:

```text
main
```

The feature branches are:

```text
feature/mergesort
feature/quicksort
feature/select
feature/metrics
```

Example meaningful commits:

```text
feat(mergesort): add reusable merge buffer
feat(mergesort): add insertion sort cutoff
feat(quicksort): add random pivot and 3-way partition
feat(select): implement quickselect
feat(metrics): add comparison and depth metrics
test(quicksort): check recursion depth
docs(report): add benchmark analysis and plots
```

Avoid unclear commit messages such as:

```text
update
fix
final
assignment
done
```

The final working version should be merged into:

```text
main
```

and tagged:

```text
v1.0
```

---

# 18. Final Deliverables

The final submission should contain:

```text
1. Source code
2. results.csv
3. Three PNG plots
4. REPORT.md
5. README.md
6. pom.xml
7. JUnit 5 tests
```

The project should be uploaded as a ZIP archive named according to the assignment requirements:

```text
DAA_Assignment1_name_surname_group.zip
```

The GitHub repository link should also be included in the Moodle submission.

---

# 19. Running the Project — Quick Guide

The basic workflow is:

### 1. Compile

```bash
mvn clean compile
```

### 2. Run tests

```bash
mvn clean test
```

### 3. Run benchmark

Run:

```text
Benchmark.java
```

This creates:

```text
results.csv
```

### 4. Generate plots

Run:

```text
PlotGenerator.java
```

This creates:

```text
plots/time_vs_n.png
plots/depth_vs_n.png
plots/ratio_vs_n.png
```

### 5. Check the report

Open:

```text
REPORT.md
```

### 6. Prepare GitHub

Make sure the final working version is on:

```text
main
```

and create:

```text
v1.0
```

---

# 20. Conclusion

This project demonstrates the implementation and experimental analysis of Divide-and-Conquer algorithms for large integer arrays.

MergeSort provides predictable `Θ(n log n)` performance, QuickSort provides expected `Θ(n log n)` performance with randomized pivots and bounded recursion depth, and QuickSelect provides expected linear-time selection.

The benchmark, CSV data, plots, and Θ-check are used to compare practical measurements with theoretical asymptotic analysis.

The complete experimental analysis is provided in `REPORT.md`.
