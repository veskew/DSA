package org.example
/*
Function for exercise 3, reversing a stack.
 */
fun <T> reverseStack(stk: MyStack<T>) : MyStack<T> {
    // temporary stack
    val result: MyStack<T> = MyStack()
    // loop through all the contents in the stack
    while (!stk.isEmpty()) {
        // cast temp to value to avoid issues with using push
        val temp = stk.peek()
        when(temp) {
            null -> return result
            else -> {
                // add data to new stack, remove from old stack
                result.push(temp)
                stk.pop()
            }
        }

    }
    return result
}
/*
Function for exercise 4, finding out if a string has valid parentheses.
 */
fun validParentheses(s: String) : Boolean {
    // array size
    val num: Int = s.length
    // temp variable to increment while looping
    var temp = 0
    // dictionary for matching open and closing parentheses
    val par: Map<Char, Char> = mapOf('(' to ')', '{' to '}', '[' to ']')
    // stack for keeping track of seen parentheses
    val stk: MyStack<Char> = MyStack()
    // loop through every char in string
    while (temp < num){
        //check if char is open or closing par
        when (s[temp] in par.keys) {
            // if open par, add it to top of stack
            true -> stk.push(s[temp])
            // if close par, make sure item on top is matching open parentheses, remove top char
            false -> {
                if (!stk.isEmpty() && (par[stk.peek()] == s[temp]))
                    stk.pop()
                else
                    return false
            }
        }
        temp++
    }
    // if stack is empty, no parentheses are unmatched.
    return stk.isEmpty()
}
