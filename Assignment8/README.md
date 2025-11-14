# KD-Tree Benchmark Results

| Dimensions | Points | Build Time | KD Search Time | Brute Force Time |
|-----------|--------|------------|----------------|------------------|
| 10 | 10 | 4.598802 ms | 3.140577 ms | 1.987715 ms |
| 10 | 100 | 0.782057 ms | 6.427593 ms | 1.814433 ms |
| 10 | 1000 | 4.148527 ms | 27.894027 ms | 10.006560 ms |
| 10 | 10000 | 19.371083 ms | 111.845472 ms | 111.369742 ms |
| 100 | 10 | 0.071700 ms | 1.139591 ms | 1.062109 ms |
| 100 | 100 | 0.098402 ms | 9.970674 ms | 9.635913 ms |
| 100 | 1000 | 0.876497 ms | 121.586783 ms | 94.546278 ms |
| 100 | 10000 | 13.749677 ms | 2673.832166 ms | 1551.723633 ms |
| 1000 | 10 | 0.026996 ms | 6.941823 ms | 8.186614 ms |
| 1000 | 100 | 0.079255 ms | 68.062119 ms | 69.881291 ms |
| 1000 | 1000 | 0.794307 ms | 1266.503625 ms | 1199.079752 ms |
| 1000 | 10000 | 13.770061 ms | 20633.397292 ms | 19477.053967 ms |
| 10000 | 10 | 0.059422 ms | 113.423432 ms | 112.784400 ms |
| 10000 | 100 | 0.086040 ms | 1697.184993 ms | 1780.327749 ms |
| 10000 | 1000 | 1.030815 ms | 19558.217816 ms | 18606.801307 ms |
| 10000 | 10000 | 18.388604 ms | 194523.123221 ms (≈3m 14s) | 184412.030101 ms (≈3m 4s) |

# Explanation

The brute search algorithm was not noticeably faster or slower than the KDTree
search. This was surprising, especially since on larger inputs, my brute search
started outperforming the KD Search. Constructing the KDTree took negligible
time, but searching for a thousand points took a long time for both search
algorithms.