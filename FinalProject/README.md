# Problem write-ups

## Backtracking

### Problem 1 - Word Search

[Problem Link](https://leetcode.com/problems/word-search/)

Initial Thoughts and Approach: My first thought upon seeing this problem is using recursion. I will declare a new function I can recursively call. I'll start by seeing if the first word in the target string is in the array. For each time it appears in the array, I'll call a function that passes in the board (with the first letter deleted, so no letter is visited twice), the truncated target string with the first letter missing, and the row and col that the first letter was found. This recursion function will check all the neighbours for the next letter. If the next letter is found, the function recurses again for each time the letter is found. If the next letter is not one of the neighbours the function returns false. Otherwise it recurses again. If at any point the target string is empty the function returns true as the string has been found. 

Solution:

Reflection: I messed up by trying to initialize a new array at every level of recursion, which was a copy of the array in the previous level except the target row col was '_'. I instead started modifying the array and storing the value I modified, and filling the array back in after traversing back through the array.

### Problem 2 - Combination Sum 1

[Problem Link](https://leetcode.com/problems/combination-sum/description/)

My intuition when approaching this problem was really solid, and almost got me to the finish line right away. I made a DFS function, and I had three inputs. The current combination of numbers you were working with, the current target number you needed to get to, and the current index at which you are taking numbers from. Thanks to all the numbers being distinct, you knew for each target number and list you had to pull from, if you simply added the first number in candidates to the combination and subtract it from target then proceed, or ignore the first element of candidates, you would eventually find every possible combination. This solution skips over elements that are bigger than the target instantly; this solution surpasses the brute force solution entirely. My biggest error I ran into was similar to the first problem. I was modifying the same combination array in memory, instead of creating a new array for each new path. Definitely the trend so far is issues with temporary memory.


### Problem 3 - Combination Sum 2

[Problem Link](https://leetcode.com/problems/combination-sum-ii/description/)

I ran into a similar problem as I have with the previous problems, where the biggest blocker was me being rusty with python syntax. I was modifying the same combination list every single pass through of the loop, instead of branching with multiple unique combinations. The key bit of intuition necessary to solve this problem was sorting the list before reading it. Because the backtracking algorithm has worst case run time O(2^n), sorting the list before hand O(nlogn) is not gonna significantly affect function run time. Sorting the list allows the code to branch by either adding the current number to the combination and appending the index by 1, or move onto the next bigger number in the list, appending index as necessary. This way there is only one branch concerned with repeating the same number, and duplicates are avoided. The intuition to sort the list was something I had from solving 3 sum over the summer (a similar leetcode problem that also involved sorting the list to avoid duplicates).


### Problem 4 - Subsets

[Problem Link](https://leetcode.com/problems/subsets/description/)

The practice has definitely been paying off! I was able to complete this problem in about fifteen minutes, and I used a lot of the skills I developed from the last three problems in solving this one. I was able to correctly modify lists and not overwrite them. I was able to recognize the edge case of the empty list [ ] being double counted in my implementation before running the code a single time. There isn’t much else for me to write here, I am feeling a lot more confident solving these kind of backtracking problems.

## 1-D Dynamic Programming

### Problem 1 - Word Break

[Problem Link](https://leetcode.com/problems/word-break/description/)

I had to watch the entire neetcode video to really understand the dynamic programming approach to this problem. I started out by trying to solve this problem with a backtracking solution, and that was both clunky and slower than the dynamic programming approach. I constructed an array that tracked what substrings were valid word breaks, and filled that array starting from the back. I did have a moment of confusion where I would occasionally overwrite values in my array that should have been true to be false, but I fixed that issue pretty fast. Overall, this problem was a good refresher on DP problems, and I should be much better equipped to do the next few problems in this set.

### Problem 2 - Decode Ways

[Problem Link](https://leetcode.com/problems/decode-ways/description/)

This problem was also a little difficult for me to build an intuition on, I had to watch a video tutorial from neetcode to understand it. After watching his video, and then closing his solution and trying to implement it on my own from memory, I was eventually successful. This problem was very similar to the staircase problem we did in class where you could take one or two steps on a staircase, and your goal was to minimize the total weight of all your steps combined. The big difference for this problem was rather than trying to minimize the weight of your steps, you had to take into account what 1 and 2 digit numbers could encode a number. I am feeling a lot more comfortable with the python syntax, and that has been less of a blocker for these past few problems, which has allowed me to solve them much faster. That being said, I’m not sure I would have had the correct intuition to solve this problem if it was not for the video tutorial I watched beforehand. I am going to try to solve the next problem with no video help if possible, and document that process.

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
