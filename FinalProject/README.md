# Problem write-ups

## Backtracking

### Problem 1 - Word Search

[Problem Link](https://leetcode.com/problems/word-search/)

Initial Thoughts and Approach: My first thought upon seeing this problem is using recursion. I will declare a new function I can recursively call. I'll start by seeing if the first word in the target string is in the array. For each time it appears in the array, I'll call a function that passes in the board (with the first letter deleted, so no letter is visited twice), the truncated target string with the first letter missing, and the row and col that the first letter was found. This recursion function will check all the neighbours for the next letter. If the next letter is found, the function recurses again for each time the letter is found. If the next letter is not one of the neighbours the function returns false. Otherwise it recurses again. If at any point the target string is empty the function returns true as the string has been found. 

Solution:

Reflection: I messed up by trying to initialize a new array at every level of recursion, which was a copy of the array in the previous level except the target row col was '_'. I instead started modifying the array and storing the value I modified, and filling the array back in after traversing back through the array.

### Problem 2 - Combination Sum 1

[Problem Link](https://leetcode.com/problems/combination-sum/description/)

Initial Thoughts and Approach:

Solution:

Reflection:

### Problem 3 - Combination Sum 2

[Problem Link](https://leetcode.com/problems/combination-sum-ii/description/)

Initial Thoughts and Approach:

Solution:

Reflection:

### Problem 4 - Subsets

[Problem Link](https://leetcode.com/problems/subsets/description/)

Initial Thoughts and Approach:

Solution:

Reflection:


## 1-D Dynamic Programming

### Problem 1 - Word Break

[Problem Link](https://leetcode.com/problems/word-break/description/)

Initial Thoughts and Approach:

Solution:

Reflection:

### Problem 2 - Decode Ways

[Problem Link](https://leetcode.com/problems/decode-ways/description/)

Initial Thoughts and Approach:

Solution:

Reflection:

### Problem 3 - Longest Pallendromic Substring

[Problem Link](https://leetcode.com/problems/Longest-pallendromic-substring/description/)

Initial Thoughts and Approach:

Solution:

Reflection:


## 2-D Dynamic Programming

### Problem 1 - Edit Distance

[Problem Link](https://leetcode.com/problems/edit-distance/description/)

Initial Thoughts and Approach:

Solution:

Reflection:

### Problem 2 - Unique Paths

[Problem Link](https://leetcode.com/problems/unique-paths/description/)

Initial Thoughts and Approach:

Solution:

Reflection:

### Problem 3 - Best Time to Buy and Sell Stock w/ Cooldown

[Problem Link](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/)

Initial Thoughts and Approach:

Solution:

Reflection:
