# Assignment 1 — Divide and Conquer & Asymptotic Notations

**Course:** Design and Analysis of Algorithms
**Assignment:** 1 — Divide and Conquer & Asymptotic Notations
**Scenario:** Fast Sorting & Selection Engine

---

# 1. Introduction

The goal of this assignment is to implement and analyze three Divide-and-Conquer algorithms for large integer arrays:

* MergeSort;
* QuickSort;
* QuickSelect.

The algorithms were implemented in Java and tested using JUnit 5. The project also contains a `Metrics` class for measuring the number of comparisons, maximum recursion depth, and execution time.

The benchmark was performed for four input sizes:

* `n = 1,000`;
* `n = 10,000`;
* `n = 100,000`;
* `n = 1,000,000`.

Three input types were used:

* `random` — random integers;
* `sorted` — already sorted integers;
* `duplicates` — random values from 0 to 9.

Each benchmark case was executed five times, and the median execution time was saved to `results.csv`.

The purpose of the experiment is to compare the measured results with the theoretical asymptotic complexity of the algorithms.

---

# 2. Algorithm Implementations

## 2.1 MergeSort

MergeSort follows the Divide-and-Conquer strategy.

First, the array is divided into two approximately equal parts. Each part is recursively sorted, and then the two sorted parts are merged.

The implementation satisfies the required memory optimization: the helper buffer is allocated only once in the top-level `sort()` method and is passed to the recursive calls.

For subarrays containing 15 or fewer elements, the implementation uses InsertionSort instead of continuing the recursion.

The merge operation compares elements from the two sorted halves and copies the result into the reusable buffer. Therefore, the merge operation takes linear time.

The main recurrence is:

```text
T(n) = 2T(n/2) + Θ(n)
```

Therefore, the asymptotic complexity is:

```text
Θ(n log n)
```

for all input cases.

---

## 2.2 QuickSort

QuickSort uses a randomly selected pivot.

The partition operation is a three-way partition:

```text
< pivot | = pivot | > pivot
```

This is useful for arrays containing many duplicate values because all elements equal to the pivot are processed together.

The implementation also recursively processes the smaller partition and handles the larger partition using a `while` loop. This limits the recursion depth and reduces the risk of `StackOverflowError`.

For a balanced partition, the recurrence is:

```text
T(n) = 2T(n/2) + Θ(n)
```

which gives:

```text
Θ(n log n)
```

The expected complexity of randomized QuickSort is therefore `Θ(n log n)`.

However, randomization does not mathematically remove the worst case. If the pivot repeatedly produces very unbalanced partitions, the running time can still become:

```text
O(n²)
```

---

## 2.3 QuickSelect

QuickSelect finds the element with rank `k` without sorting the entire array.

The implementation reuses the same three-way partition method as QuickSort.

After partitioning, only the part containing position `k` is processed.

For example:

```text
< pivot | = pivot | > pivot
```

If `k` belongs to the left part, the algorithm continues only in the left part. If `k` belongs to the right part, it continues only in the right part. If `k` belongs to the equal-to-pivot region, the answer is returned immediately.

For a balanced split, the recurrence is:

```text
T(n) = T(n/2) + Θ(n)
```

This gives an expected linear complexity:

```text
Θ(n)
```

The worst case is:

```text
O(n²)
```

if the pivot repeatedly produces very unbalanced partitions.

---

# 3. Asymptotic Bounds

The following table gives the best, average, and worst-case complexity of the implemented algorithms.

| Algorithm         | Best Case                                                            | Average Case                                                              | Worst Case                                            |
| ----------------- | -------------------------------------------------------------------- | ------------------------------------------------------------------------- | ----------------------------------------------------- |
| **MergeSort**     | `Θ(n log n)` — the array is still divided and merged                 | `Θ(n log n)` — every input is divided into two halves                     | `Θ(n log n)` — merging remains linear at every level  |
| **QuickSort**     | `Θ(n log n)` — partitions are approximately balanced                 | `Θ(n log n)` expected — random pivot gives balanced partitions on average | `O(n²)` — repeated highly unbalanced partitions       |
| **QuickSelect**   | `Θ(n)` — pivot is close to the required position                     | `Θ(n)` expected — only one partition is processed at each level           | `O(n²)` — repeated highly unbalanced partitions       |
| **InsertionSort** | `Θ(n)` — already sorted input requires only a small number of shifts | `Θ(n²)` — random input requires many shifts/comparisons                   | `Θ(n²)` — reverse-ordered input causes maximum shifts |

### Explanation

MergeSort has the same asymptotic complexity for all input arrangements because the array is always divided into halves and the halves are merged.

QuickSort depends on the quality of the pivot. A balanced partition gives logarithmic recursion depth, while repeatedly choosing a pivot near one end of the partition can produce quadratic behavior.

QuickSelect is faster than full sorting when only one order statistic is required because after each partition it discards the part that cannot contain `k`.

InsertionSort is very efficient for an already sorted or nearly sorted array, but its number of operations can become quadratic when many elements have to be shifted.

---

# 4. Recurrence Relations and Master Theorem

## 4.1 Master Theorem

The standard form of the Master Theorem is:

```text
T(n) = aT(n/b) + f(n)
```

where:

* `a` is the number of recursive subproblems;
* `n/b` is the size of each subproblem;
* `f(n)` is the non-recursive work.

We compare `f(n)` with:

```text
n^(log_b(a))
```

The theorem then determines the asymptotic complexity.

---

## 4.2 MergeSort Recurrence

For MergeSort:

```text
T(n) = 2T(n/2) + Θ(n)
```

Therefore:

```text
a = 2
b = 2
f(n) = Θ(n)
```

Calculate:

```text
n^(log_b(a))
= n^(log₂2)
= n
```

Thus:

```text
f(n) = Θ(n)
```

and it matches:

```text
n^(log₂2) = n
```

This is **Master Theorem Case 2**.

Therefore:

```text
T(n) = Θ(n log n)
```

The cutoff at 15 elements does not change the asymptotic complexity because it only affects small subarrays.

---

## 4.3 QuickSort Recurrence

For the required recurrence analysis, a balanced QuickSort split is assumed.

The recurrence is:

```text
T(n) = 2T(n/2) + Θ(n)
```

Therefore:

```text
a = 2
b = 2
f(n) = Θ(n)
```

Again:

```text
n^(log₂2) = n
```

So this is **Master Theorem Case 2**.

Therefore:

```text
T(n) = Θ(n log n)
```

In the actual implementation the pivot is random. A random pivot does not guarantee a perfectly balanced split every time, but over many partitions the expected behavior is balanced enough to produce expected `O(n log n)` running time. The smaller-side recursion strategy also keeps the actual recursion depth bounded even when partitions are not perfectly balanced.

The worst-case running time remains:

```text
O(n²)
```

because a sequence of very unbalanced random pivot choices is still theoretically possible.

---

## 4.4 QuickSelect Recurrence

QuickSelect has a different recurrence because only one side of the partition is processed.

Assuming a balanced split:

```text
T(n) = T(n/2) + Θ(n)
```

Therefore:

```text
a = 1
b = 2
f(n) = Θ(n)
```

Calculate:

```text
n^(log₂1)
= n^0
= 1
```

Thus:

```text
f(n) = Θ(n)
```

grows polynomially faster than:

```text
n^0 = 1
```

This corresponds to **Master Theorem Case 3**.

Therefore:

```text
T(n) = Θ(n)
```

This is different from MergeSort and balanced QuickSort because QuickSelect continues into only one subproblem.

---

# 5. Benchmark Methodology

The benchmark uses three algorithms, three input types, and four input sizes.

Therefore, the total number of benchmark cases is:

```text
3 algorithms × 3 input types × 4 sizes = 36 cases
```

Every case is executed five times:

```text
36 × 5 = 180 runs
```

The median time is stored in the CSV file.

The benchmark measures:

* execution time in milliseconds;
* number of comparisons;
* maximum recursion depth.

Execution time is measured with:

```java
System.nanoTime()
```

The results are saved to:

```text
results.csv
```

with the following columns:

```text
algorithm,input,n,time_ms,comparisons,max_depth
```

The duplicate input contains random values from 0 to 9, which creates many equal elements and allows the effect of three-way partitioning to be observed.

---

# 6. Benchmark Results

## 6.1 Results for n = 1,000,000

The largest benchmark size gives the following results:

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

These results demonstrate several important implementation effects.

MergeSort has the same recursion depth for all three input types because its division structure does not depend on the input ordering.

QuickSort performs particularly well on duplicate-heavy input. The three-way partition allows the algorithm to process all elements equal to the pivot together, which explains the very small maximum depth of 2 for `n = 1,000,000`.

QuickSelect has substantially lower running time than the complete sorting algorithms because it only searches for the required order statistic.

---

# 7. Time vs n Analysis

The `time_vs_n.png` graph shows the measured execution time as the input size increases.

In general, the execution time increases with `n`.

MergeSort follows the expected `n log n` growth. The sorted input is considerably faster in the experiment because the merge comparisons are favorable and the implementation uses a cutoff to InsertionSort for small subarrays.

QuickSort also shows approximately `n log n` behavior for random and sorted inputs. The random pivot prevents the sorted input from becoming the classical deterministic QuickSort worst case.

The duplicate input is significantly faster for QuickSort. At `n = 1,000,000`, QuickSort requires only `11.9042 ms` for the duplicate input compared with `124.6862 ms` for random input.

QuickSelect grows more slowly than the sorting algorithms. This agrees with its expected linear complexity because only one side of each partition is processed.

---

# 8. Maximum Recursion Depth Analysis

The MergeSort depths were:

|         n | Random | Sorted | Duplicates |
| --------: | -----: | -----: | ---------: |
|     1,000 |      8 |      8 |          8 |
|    10,000 |     11 |     11 |         11 |
|   100,000 |     14 |     14 |         14 |
| 1,000,000 |     18 |     18 |         18 |

The depth increases slowly as `n` increases, which is consistent with logarithmic recursion depth.

For QuickSort:

|         n | Random | Sorted | Duplicates |
| --------: | -----: | -----: | ---------: |
|     1,000 |      6 |      6 |          2 |
|    10,000 |      8 |      9 |          2 |
|   100,000 |     11 |     11 |          3 |
| 1,000,000 |     13 |     13 |          2 |

The results show that the smaller-side-first strategy successfully keeps the recursion depth small.

The required depth test for sorted input with `n = 100,000` is:

```text
maxDepth <= 2 × log₂(n)
```

For `n = 100,000`:

```text
2 × log₂(100000) ≈ 33.22
```

The measured QuickSort depth is:

```text
11
```

Therefore, the measured value is within the required bound.

---

# 9. Θ Check

The Θ-check compares the measured number of comparisons with the expected asymptotic growth.

For MergeSort and QuickSort:

```text
R(n) = comparisons / (n log₂(n))
```

For QuickSelect:

```text
R(n) = comparisons / n
```

If the algorithm has the expected Θ complexity, the ratio should remain approximately bounded instead of continuously increasing with `n`.

This is an experimental check rather than a mathematical proof.

---

## 9.1 MergeSort Θ Check

For random input:

|         n | Comparisons | `comparisons / (n log₂ n)` |
| --------: | ----------: | -------------------------: |
|     1,000 |       9,523 |                    ≈ 0.956 |
|    10,000 |     127,212 |                    ≈ 0.958 |
|   100,000 |   1,639,343 |                    ≈ 0.987 |
| 1,000,000 |  19,889,337 |                    ≈ 0.996 |

The ratio stays close to a constant near 1.

For the measured data, a rough Θ bound can be described using:

```text
c₁ ≈ 0.4
c₂ ≈ 1.1
n₀ = 1,000
```

Thus, for the tested MergeSort data:

```text
0.4(n log₂ n) <= comparisons <= 1.1(n log₂ n)
```

approximately holds for `n >= 1,000`.

This supports the experimental conclusion:

```text
MergeSort = Θ(n log n)
```

The exact constants depend on the input type and on the comparison-counting implementation.

---

## 9.2 QuickSort Θ Check

For random input:

|         n | Comparisons | `comparisons / (n log₂ n)` |
| --------: | ----------: | -------------------------: |
|     1,000 |      20,287 |                    ≈ 2.036 |
|    10,000 |     277,439 |                    ≈ 2.088 |
|   100,000 |   3,450,915 |                    ≈ 2.075 |
| 1,000,000 |  41,157,103 |                    ≈ 2.063 |

The ratio stays close to approximately 2 for random input.

A rough bound covering the measured input types can be chosen as:

```text
c₁ ≈ 0.25
c₂ ≈ 2.2
n₀ = 1,000
```

Therefore, for the observed data:

```text
0.25(n log₂ n) <= comparisons <= 2.2(n log₂ n)
```

approximately describes the measured range.

The ratio is not exactly constant because pivot selection is randomized and different input distributions produce different partition behavior.

The experimental results are nevertheless consistent with expected:

```text
QuickSort = Θ(n log n)
```

for the tested non-pathological cases.

---

## 9.3 QuickSelect Θ Check

For QuickSelect the ratio is:

```text
comparisons / n
```

For random input:

|         n | Comparisons | `comparisons / n` |
| --------: | ----------: | ----------------: |
|     1,000 |       5,265 |             5.265 |
|    10,000 |      47,424 |             4.742 |
|   100,000 |     404,464 |             4.045 |
| 1,000,000 |   6,915,053 |             6.915 |

The ratio does not grow like `log n` or `n`. Instead, it stays within a constant-sized range.

A rough bound for the measured data can be given as:

```text
c₁ ≈ 2.5
c₂ ≈ 7.2
n₀ = 1,000
```

Thus:

```text
2.5n <= comparisons <= 7.2n
```

approximately describes the measured results for the tested inputs.

This supports the experimental conclusion:

```text
QuickSelect = Θ(n)
```

for expected behavior.

Because QuickSelect uses a random pivot, the exact ratio can vary significantly between different executions.

---

# 10. Ratio Plot Analysis

The `ratio_vs_n.png` graph visualizes the Θ-check.

For MergeSort and QuickSort, the expected normalization is:

```text
comparisons / (n log₂ n)
```

For QuickSelect, the normalization is:

```text
comparisons / n
```

The purpose of this graph is to see whether the ratio remains approximately constant as `n` increases.

The measured results show that the ratios remain within bounded ranges rather than increasing without limit.

For MergeSort, the random-input ratio changes from approximately `0.956` to `0.996`.

For QuickSort, the random-input ratio stays around `2.0`.

For QuickSelect, the random-input ratio remains within a constant-sized range from approximately `4.0` to `6.9`.

Therefore, the ratio plots provide experimental evidence supporting the theoretical complexity of the algorithms.

---

# 11. Input-Type Effects

The three input distributions demonstrate that asymptotic complexity does not describe every constant factor of practical execution.

## Random Input

Random input provides a general case for the algorithms.

MergeSort remains predictable because its structure does not depend on input ordering.

QuickSort uses a random pivot, so its behavior is generally consistent with expected `O(n log n)` performance.

QuickSelect also shows expected near-linear behavior.

## Sorted Input

The sorted input is particularly useful for testing QuickSort.

A deterministic QuickSort that always chooses the first or last element as pivot could degrade to `O(n²)` on sorted data.

The implemented randomized pivot avoids this predictable failure pattern.

## Duplicate Input

The duplicate input contains only values from 0 to 9.

This means a very large array contains many equal elements.

The three-way partition is especially effective in this situation because elements equal to the pivot are grouped together.

This is reflected in the QuickSort results:

```text
n = 1,000,000
QuickSort duplicates:
11.9042 ms
maxDepth = 2
```

This demonstrates the practical benefit of the required three-way partition.

---

# 12. Discussion

The experimental results generally match the theoretical complexity of the implemented algorithms. MergeSort shows stable `Θ(n log n)` behavior because it always divides the array into two parts and performs a linear merge. QuickSort demonstrates expected `Θ(n log n)` behavior, while the randomized pivot reduces the risk of repeatedly choosing a bad pivot on sorted data. QuickSelect grows more slowly because it processes only the partition containing the requested element, which agrees with its expected `Θ(n)` complexity. The measured execution times can differ from theoretical predictions because the Java Virtual Machine uses Just-In-Time (JIT) compilation and the first executions can be slower during JVM warm-up. Garbage Collection can also introduce additional delays that are unrelated to the algorithm itself. CPU cache behavior can change execution time because sequential memory access can be faster than less predictable access patterns. The MergeSort cutoff of 15 elements also affects practical performance because InsertionSort has low overhead and can be efficient for very small subarrays. Finally, random pivot selection means that QuickSort and QuickSelect measurements can vary between executions even when the input array is the same.

---

# 13. Testing

JUnit 5 tests were created to verify the correctness of the algorithms.

The sorting algorithms are compared with Java's reference implementation:

```java
Arrays.sort()
```

Random arrays are used to check correctness across many different input configurations.

The tests also cover the required edge cases:

* empty array;
* one-element array;
* all elements equal;
* already sorted array.

QuickSort has an additional recursion-depth test using a sorted array of:

```text
n = 100,000
```

The requirement is:

```text
maxDepth <= 2 × log₂(n)
```

The implementation satisfies this condition.

QuickSelect is tested by comparing its result with:

```text
sorted[k]
```

for at least 100 random test arrays.

Invalid QuickSelect inputs are also checked. An `IllegalArgumentException` is thrown when the input array is empty or `k` is outside the valid range.

---

# 14. Design Quality

The implementation follows the main design requirements of the assignment.

### MergeSort

The helper array is allocated only once and reused during recursive calls.

The cutoff is set to:

```text
15
```

and InsertionSort is used for smaller subarrays.

### QuickSort

The implementation uses:

* random pivot selection;
* three-way partitioning;
* recursion into the smaller partition;
* an iterative loop for the larger partition.

These techniques reduce unnecessary memory usage and prevent excessive recursion depth.

### QuickSelect

QuickSelect reuses QuickSort's partition method and continues only in the part containing `k`.

This avoids sorting the complete array when only one element is required.

### Metrics

The `Metrics` object is passed to the algorithms rather than using global variables.

It records:

* comparisons;
* maximum recursion depth;
* elapsed time.

This keeps the measurement logic separated from the algorithm implementation.

---

# 15. Conclusion

This assignment implemented three Divide-and-Conquer algorithms: MergeSort, QuickSort, and QuickSelect.

The theoretical analysis shows that MergeSort has `Θ(n log n)` complexity, QuickSort has expected `Θ(n log n)` complexity with a theoretical `O(n²)` worst case, and QuickSelect has expected `Θ(n)` complexity with a theoretical `O(n²)` worst case.

The benchmark results generally agree with these theoretical expectations.

The Θ-check also supports the analysis: the normalized comparison ratios remain approximately bounded as the input size increases.

The experiments demonstrate the importance of implementation details. The reusable MergeSort buffer reduces memory allocation overhead, the cutoff improves small-subarray performance, and the three-way QuickSort partition is highly effective for duplicate-heavy data.

The smaller-side-first QuickSort strategy successfully keeps recursion depth low, including for sorted arrays.

QuickSelect demonstrates the advantage of selection when the complete sorted order is not required.

Overall, the implementation and experimental results show how theoretical asymptotic analysis and practical benchmarking can be used together to evaluate algorithm performance.
