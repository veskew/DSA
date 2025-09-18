import org.example.MyStack
import org.example.reverseStack
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ReverseStackTest {
    @Test
    fun reverseStack() {
        val stk = MyStack<Int>()
        val resultStk = MyStack<Int>()
        assertEquals(stk, reverseStack(resultStk))
        stk.push(3)
        stk.push(4)
        stk.push(5)
        stk.push(1)
        resultStk.push(1)
        resultStk.push(5)
        resultStk.push(4)
        resultStk.push(3)
        assertEquals(stk, reverseStack(resultStk))

    }

}