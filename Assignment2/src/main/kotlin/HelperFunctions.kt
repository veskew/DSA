package org.example

fun <T> reverseStack(stk: MyStack<T>) : MyStack<T> {
    val result: MyStack<T> = MyStack()
    while (!stk.isEmpty()) {
        val temp = stk.peek()
        when(temp) {
            null -> return result
            else -> {
                result.push(temp)
                stk.pop()
            }
        }

    }
    return result
}

fun validParentheses(s: String) : Boolean {
    val num: Int = s.length
    var temp = 0
    val par: Map<Char, Char> = mapOf('(' to ')', '{' to '}', '[' to ']')
    val stk: MyStack<Char> = MyStack()
    while (temp < num){
        when (s[temp] in par.keys) {
            true -> stk.push(s[temp])
            false -> {
                if (!stk.isEmpty() && (par[stk.peek()] == s[temp]))
                    stk.pop()
                else
                    return false
            }
        }
        temp++
    }

    return stk.isEmpty()
}
