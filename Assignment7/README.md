# Markov Text Analysis

I implemented an associative array class with one parameter, the number of
buckets to hash inputs into. I then implemented markov text generation, where I
got a book online from Project Gutenberg, and made a Markov text analysis model 
from it. I did this by looping through every word in the file, and mapping that
word to a map of next words, with their frequencies also being stored. I then
generate a random word from the text, and then add new words from the possible
next words observed in the text. If "The" is followed by "people" 8 times and 
"woman" 2 times, my model would generate "the people" 80% of the time and "the
woman" 20% fo the time.

# Runtime Analysis

## Runtime Comparison by Hash Size

| Hash Size | Runtime (seconds) |
|------------|-------------------|
| 2          | 73.0915 |
| 4          | 12.5895 |
| 8          | 2.4281 |
| 16         | 0.5376 |
| 32         | 0.1446 |
| 53         | 0.0864 |
| 64         | 0.0744 |
| 97         | 0.0549 |
| 128        | 0.0526 |
| 193        | 0.0463 |
| 256        | 0.0490 |
| 389        | 0.0330 |
| 512        | 0.0328 |
| 769        | 0.0342 |
| 1024       | 0.0327 |
| 1543       | 0.0444 |

Runtimes decrease with increasing hash size, leveling off after around 389.
Past 389, the reward for increasing the hash size is negligible.