# DAA Assignment 1 — Fast Sorting & Selection Engine

## 1. Project

This project implements and evaluates three Divide-and-Conquer algorithms for large integer arrays:

* MergeSort
* QuickSort
* QuickSelect

The project includes algorithm implementations, JUnit tests, benchmarks, CSV results and performance plots.

## 2. Technologies

* Java 17
* Maven
* JUnit 5
* JFreeChart

## 3. Algorithms

### MergeSort

* Reusable helper buffer
* InsertionSort cutoff for subarrays of 15 elements or fewer
* Linear merge

Complexity: `Θ(n log n)`

### QuickSort

* Random pivot
* 3-way partitioning
* Smaller-side recursion
* Loop for the larger side

Expected complexity: `Θ(n log n)`
Worst case: `O(n²)`

### QuickSelect

* Finds the `k`-th smallest element
* Reuses QuickSort partitioning
* Processes only the part containing `k`

Expected complexity: `Θ(n)`
Worst case: `O(n²)`

## 4. Project Structure

```text
DAA_Assignment1
├── pom.xml
├── README.md
├── REPORT.md
├── results.csv
├── plots
│   ├── time_vs_n.png
│   ├── depth_vs_n.png
│   └── ratio_vs_n.png
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
    └── test
        └── java
            └── com.daa.assignment1
                ├── MergeSortTest.java
                ├── QuickSortTest.java
                └── QuickSelectTest.java
```

## 5. Requirements

Install:

* Java 17 or newer
* Maven

Check installation:

```bash
java -version
mvn -version
```

## 6. Build and Test

Compile the project:

```bash
mvn clean compile
```

Run all tests:

```bash
mvn clean test
```

The tests check sorting correctness, edge cases, QuickSort recursion depth and QuickSelect correctness.

## 7. Benchmark

The benchmark uses:

```text
n = 1,000
n = 10,000
n = 100,000
n = 1,000,000
```

Input types:

```text
random
sorted
duplicates
```

Each case is executed 5 times and the median time is stored.

Run:

```text
Benchmark.java
```

The results are saved to:

```text
results.csv
```

CSV columns:

```text
algorithm,input,n,time_ms,comparisons,max_depth
```

## 8. Performance Plots

Run:

```text
PlotGenerator.java
```

It generates:

```text
plots/time_vs_n.png
plots/depth_vs_n.png
plots/ratio_vs_n.png
```

The plots show execution time, recursion depth and normalized comparison counts.

## 9. Results

The benchmark contains:

```text
3 algorithms × 3 input types × 4 sizes = 36 cases
```

With 5 runs per case:

```text
36 × 5 = 180 runs
```

The complete results are available in:

```text
results.csv
```

The detailed analysis is available in:

```text
REPORT.md
```

## 10. Git

Main branch:

```text
main
```

Feature branches:

```text
feature/mergesort
feature/quicksort
feature/select
feature/metrics
```

Final version:

```text
v1.0
```

## 11. Final Submission

The project should contain:

```text
Source code
README.md
REPORT.md
results.csv
3 PNG plots
pom.xml
JUnit tests
```

ZIP filename:

```text
DAA_Assignment1_name_surname_group.zip
```
