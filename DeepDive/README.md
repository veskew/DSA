# Timsort

## Explanation of Algorithm

Timsort is a hybrid sorting algorithm - it uses a combination of merge and
insertion sort to sort arrays, with the worst case runtime of O(nlog(n)). This
algorithm's main strategy is finding and isolating "runs" in the dataset, and
then merging these runs together. It takes advantage of the fact that insertion
sort is really fast on small lists, to sort the input array into runs of length
32, which are strictly increasing sets of integers. It then stores these runs
on a stack, and by following some invariants, runs merge sort on these runs to
combine them together and return the sorted list.

As mentioned earlier, this algorithm finds runs in the data, and benefits from
data sets with a lot of runs present. This strategy is not especially effective
on truly random inputs, but since many data sets observed in the real world do
have runs present in them due to underlying factors, timsort is a very
practical sorting algorithm.

## History of Timsort Algorithm

Timsort was originally implemented in 2002 by its namesake, Tim Peters. The
algorithm was written for Python, and was in use from Python version 2.3 all
the way to 3.11, when Powersort, an algorithm derived from Timsort, took over.
Timsort is also used in JAVA SE 7, Android, GNU Octave, V8, Swift, and Rust.
The algorithm was the default for Python for 20 years, and in that time only
one bug was discovered and fixed in 2015, where on large enough inputs, the
stack that kept track of runs would not be allocated enough memory to fit all
the runs in the input.

## My own implementation

My implementation has three helper functions that power my Timsort function.

`insertionSort`: Helper function that sorts an array by shuffling elements into
the right relative order starting from the first index and traversing the whole
list.

`merge`: Helper function that combines two sorted array into one bigger, sorted
array.

`detectRuns`: Helper function that identifies runs within the data set and also
reverses strictly decreasing runs.

With all these functions in place, I implemented the `timSort` function. I
run detectRuns on the input list, combine the runs all to be at least size 32,
using insertion sort, and then I merge those runs together. I ended up
implementing a simplified version of Timsort that didn't take into account the
invariants, and this was due to a lack of time on my end. I made sure that I
understood how the algorithm should be implemented, but since the actual
implementation used in Python is 636 lines of code, fully recreating the
algorithm was out of the scope of this project.

## Benchmark testing

I brought back the table I created for the writeup of assignment 4, and tested
my implementation of Timsort against the other sorting algorithms I
implemented.


|Array length | Kotlin Sort | Merge | Insertion | Selection | Quick | Timsort |
|---|---|---|---|---|---|---|
|10|.0012|.0014|6.3e-4|.0027|5.9e-4|5.5e-4|
|100|9.2e-5|3.3e-4|4.5e-4|7.5e-4|2.0e-4|2.7e-4|
|1,000|4.9e-4|.0016|.013|.014|.0010|.0021|
|10,000|.0045|.0052|.22|.27|.0035|.012|
|100,000|.042|.026|23|16|.023|.055|
|1,000,000|.31|.30|LONG|LONG|.26|.53|

This table came out to almost exactly what I expected it to be. I knew that
since the arrays I passed into Timsort were truly random, they would probably
not be sorted faster than all the O(nlog(n)) sorting algorithms. That being
said, Timsort was much faster than selection and insertion sort, which made
sense. These O(n^2) sorting algorithms are really only optimal on small input
sizes, and Timsort takes advantage of these algorithms, however never runs them
on lists greater than size 32. I was pleasantly surprised with the speed of my
Timsort implementation.