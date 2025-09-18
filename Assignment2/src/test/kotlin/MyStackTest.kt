import org.example.MyStack
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MyStackTest {
    @Test
    fun push() {
        val stk = MyStack<Int>()
        stk.push(4)
        assertEquals(4, stk.peek())
    }

    @Test
    fun pop() {
        val stk = MyStack<Int>()
        stk.push(4)
        stk.push(3)
        assertEquals(3, stk.pop())
    }

    @Test
    fun peek() {
        val stk = MyStack<Int>()
        stk.push(4)
        assertEquals(stk.peek(), 4)
    }

    @Test
    fun isEmpty() {
        val stk = MyStack<Int>()
        assertEquals(true, stk.isEmpty())
    }

}