import org.example.MyQueue
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MyQueueTest {
    @Test
    fun enqueue() {
        val q = MyQueue<Int>()
        q.enqueue(3)
        assertEquals(3, q.peek())
        q.enqueue(4)
        assertEquals(3, q.peek())
    }

    @Test
    fun dequeue() {
        val q = MyQueue<Int>()
        q.enqueue(3)
        assertEquals(3, q.dequeue())
        assertEquals(null, q.dequeue())
    }

    @Test
    fun peek() {
        val q = MyQueue<Int>()
        assertEquals(q.peek(), null)
        q.enqueue(3)
        assertEquals(3, q.peek())
    }

    @Test
    fun isEmpty() {
        val q = MyQueue<Int>()
        assertEquals(true, q.isEmpty())
        q.enqueue(3)
        assertEquals(false, q.isEmpty())
    }

}