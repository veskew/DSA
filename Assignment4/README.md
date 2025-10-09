
# Runtime Analysis

I implemented four sorting algorithms for this assignment. Two of my algorithms have O(n^2) time complexity (insertion and selection sort), and two of my alogrithms have O(nlog(n)) time complexity (merge and quick sort).

In order to test the runtimes, I used the code provided by Paul in the assignment description, and tested each of my algorithms on arrays of size 10, 100, 1,000, 10,000, 100,000, and 1,000,000. I included all the different runtimes I got for each algorithm and array size, with the builtin Kotlin sort method for reference. I ran each algorithm through the measurement code one time, and put the results here.

|Array length | Kotlin Sort | Merge | Insertion | Selection | Quick |
|---|---|---|---|---|---|
|10|.0012|.0014|6.3e-4|.0027|5.9e-4|
|100|9.2e-5|3.3e-4|4.5e-4|7.5e-4|2.0e-4|
|1,000|4.9e-4|.0016|.013|.014|.0010|
|10,000|.0045|.0052|.22|.27|.0035|
|100,000|.042|.026|23|16|.023|
|1,000,000|.31|.30|LONG|LONG|.26|

The algorithms with nlogn runtime performed a lot better than the O(n^2) algorithms, unsurprisingly. I was surprised to see some of my algorithms outperform the built in sort method on some input sizes, but the built in sort algorithm seems to be optimized for smaller lists, which I'd imagine is intentional, as people are typically sorting arrays with 10,000 or less elements.

I spent 40 minutes running insertion sort on a one million element array, and it did not return a result. I put Long for Insertion and selection sort on a million elements because I did not have the time to run them for that long.

I was also confused by how sorting ten element arrays was taking so long. My theory is that those miliseconds are counting both sorting the array and also initializing all the classes/helper functions neccessary for that algorithm.

# Master Theorem

Included in this folder is the [pdf scan](DSA%20Master%20theorem%20practice.pdf) of my one hour of work on the master theorem
