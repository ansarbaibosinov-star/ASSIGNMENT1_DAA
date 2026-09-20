# Assignment 1 — Divide and Conquer & Asymptotic Notations

## 1. Introduction

The goal of this project is to implement and analyze MergeSort, QuickSort and QuickSelect for large integer arrays.

The project uses Java and JUnit 5. The `Metrics` class measures comparisons, execution time and maximum recursion depth.

The benchmark uses input sizes of 1,000, 10,000, 100,000 and 1,000,000 with random, sorted and duplicate-value arrays. Each case is executed 5 times and the median time is stored in `results.csv`.

## 2. Algorithm Implementations

### 2.1 MergeSort

MergeSort divides the array into two parts and recursively sorts them. The parts are then merged in linear time.

The implementation uses one helper buffer allocated at the top level. For subarrays of 15 elements or fewer, InsertionSort is used.

Recurrence:

```text
T(n) = 2T(n/2) + Θ(n)
```

Result:

```text
Θ(n log n)
```

### 2.2 QuickSort

QuickSort uses a random pivot and 3-way partitioning:

```text
< pivot | = pivot | > pivot
```

The algorithm recursively processes the smaller part and uses a loop for the larger part. This limits recursion depth.

Expected complexity:

```text
Θ(n log n)
```

Worst case:

```text
O(n²)
```

### 2.3 QuickSelect

QuickSelect finds the `k`-th smallest element without sorting the complete array.

It reuses the QuickSort partition method and continues only in the part containing `k`.

Balanced recurrence:

```text
T(n) = T(n/2) + Θ(n)
```

Result:

```text
Θ(n)
```

Worst case:

```text
O(n²)
```

## 3. Asymptotic Complexity

| Algorithm     | Best       | Average / Expected  | Worst      |
| ------------- | ---------- | ------------------- | ---------- |
| MergeSort     | Ω(n log n) | Θ(n log n)          | O(n log n) |
| QuickSort     | Ω(n log n) | Θ(n log n) expected | O(n²)      |
| QuickSelect   | Ω(n)       | Θ(n) expected       | O(n²)      |
| InsertionSort | Ω(n)       | Θ(n²)               | O(n²)      |

MergeSort always divides the array and performs linear merging at every level.

QuickSort has expected `Θ(n log n)` complexity with random pivots, but an unfavorable sequence of pivots can produce `O(n²)` behavior.

QuickSelect processes only one partition at each step, giving expected linear complexity.

InsertionSort is linear when the input is already sorted and quadratic for average and worst cases.

## 4. Recurrence Relations

### MergeSort

```text
T(n) = 2T(n/2) + Θ(n)
```

```text
a = 2
b = 2
f(n) = Θ(n)
```

Master Theorem:

```text
n^(log₂2) = n
f(n) = Θ(n)
```

This is Case 2.

Result:

```text
Θ(n log n)
```

### Balanced QuickSort

```text
T(n) = 2T(n/2) + Θ(n)
```

```text
a = 2
b = 2
f(n) = Θ(n)
```

Master Theorem Case 2 gives:

```text
Θ(n log n)
```

The random pivot makes this the expected complexity. The worst case remains `O(n²)`.

### Balanced QuickSelect

```text
T(n) = T(n/2) + Θ(n)
```

```text
a = 1
b = 2
f(n) = Θ(n)
```

Since:

```text
n^(log₂1) = 1
```

and `f(n)` grows faster than this term, Master Theorem Case 3 gives:

```text
Θ(n)
```

## 5. Benchmark Methodology

The benchmark contains:

```text
3 algorithms × 3 input types × 4 sizes = 36 cases
```

Each case is executed 5 times:

```text
36 × 5 = 180 runs
```

Input sizes:

```text
1,000
10,000
100,000
1,000,000
```

Input types:

```text
random
sorted
duplicates
```

Duplicate arrays contain values from `0` to `9`.

The benchmark records:

```text
algorithm
input
n
time_ms
comparisons
max_depth
```

Execution time is measured using `System.nanoTime()`.

Array generation and CSV writing are not included in algorithm timing.

## 6. Benchmark Results

For `n = 1,000,000`:

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

The complete benchmark data is stored in `results.csv`.

## 7. Recursion Depth

MergeSort has predictable recursion depth:

|         n | Random | Sorted | Duplicates |
| --------: | -----: | -----: | ---------: |
|     1,000 |      8 |      8 |          8 |
|    10,000 |     11 |     11 |         11 |
|   100,000 |     14 |     14 |         14 |
| 1,000,000 |     18 |     18 |         18 |

QuickSort:

|         n | Random | Sorted | Duplicates |
| --------: | -----: | -----: | ---------: |
|     1,000 |      6 |      6 |          2 |
|    10,000 |      8 |      9 |          2 |
|   100,000 |     11 |     11 |          3 |
| 1,000,000 |     13 |     13 |          2 |

For `n = 100,000`:

```text
2 × log₂(100000) ≈ 33.22
```

The measured maximum depth is 11, so the required bound is satisfied.

## 8. Θ Check

For MergeSort and QuickSort:

```text
R(n) = comparisons / (n × log₂(n))
```

For QuickSelect:

```text
R(n) = comparisons / n
```

For random input, the measured ratios were approximately:

MergeSort:

```text
0.956
0.958
0.987
0.996
```

QuickSort:

```text
2.036
2.088
2.075
2.063
```

QuickSelect:

```text
5.265
4.742
4.045
6.915
```

These values remain within a bounded range in the tested data.

For the experimental data, approximate bounds can be written as:

```text
0.4(n log₂ n) ≤ comparisons ≤ 1.1(n log₂ n)
```

for MergeSort,

```text
0.25(n log₂ n) ≤ comparisons ≤ 2.2(n log₂ n)
```

for QuickSort, and

```text
2.5n ≤ comparisons ≤ 7.2n
```

for QuickSelect, with `n₀ = 1000`.

These are experimental observations and do not constitute mathematical proofs of Θ complexity.

## 9. Input-Type Effects

Random input provides a general test case.

Sorted input is useful for evaluating QuickSort because poor pivot selection can lead to unbalanced partitions. Random pivot selection reduces this predictable behavior.

Duplicate values work well with 3-way partitioning because equal elements are processed together.

For `n = 1,000,000`, QuickSort with duplicate values reached a maximum depth of only 2.

## 10. Practical Performance

Practical execution time can be affected by:

* JVM warmup and JIT compilation;
* garbage collection;
* CPU cache;
* random pivot selection;
* the InsertionSort cutoff of 15 elements.

Therefore, measured execution time does not depend only on asymptotic complexity.

## 11. Testing

JUnit 5 tests are used to verify correctness.

The tests include:

* random arrays;
* empty arrays;
* one-element arrays;
* sorted arrays;
* arrays with equal elements;
* comparison with `Arrays.sort()`;
* QuickSort recursion depth for `n = 100,000`;
* QuickSelect results compared with `sorted[k]`;
* invalid QuickSelect input.

Invalid empty arrays and invalid `k` values produce `IllegalArgumentException`.

## 12. Design Quality

The implementation follows the main requirements of the assignment.

MergeSort uses one reusable buffer and an InsertionSort cutoff.

QuickSort uses a random pivot, 3-way partitioning, smaller-side recursion and a loop for the larger side.

QuickSelect reuses the partition method and processes only the required side.

The `Metrics` class keeps measurements separate from the algorithm logic.

## 13. Conclusion

The project implements MergeSort, QuickSort and QuickSelect and evaluates them using theoretical and experimental analysis.

MergeSort has `Θ(n log n)` complexity. QuickSort has expected `Θ(n log n)` complexity with a worst case of `O(n²)`. QuickSelect has expected `Θ(n)` complexity with a worst case of `O(n²)`.

The benchmark results, recursion-depth measurements, CSV data and Θ-check provide experimental evidence consistent with the expected asymptotic behavior.
