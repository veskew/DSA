import org.example.MyLinkedList
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MyLinkedListTest {
    @Test
    fun pushFront() {
        val ll = MyLinkedList<Int>()
        ll.pushFront(3)
        ll.pushFront(4)
        assertEquals(4, ll.peekFront())
    }

    @Test
    fun pushBack() {
        val ll = MyLinkedList<Int>()
        ll.pushBack(3)
        ll.pushBack(4)
        assertEquals(3, ll.peekFront())
    }

    @Test
    fun popFront() {
        val ll = MyLinkedList<Int>()
        ll.pushFront(3)
        ll.pushFront(4)
        assertEquals(4, ll.popFront())
    }

    @Test
    fun popBack() {
        val ll = MyLinkedList<Int>()
        ll.pushFront(3)
        ll.pushFront(4)
        assertEquals(3, ll.popBack())
    }

    @Test
    fun peekFront() {
        val ll = MyLinkedList<Int>()
        assertEquals(null, ll.peekFront())
        ll.pushFront(3)
        ll.pushFront(4)
        assertEquals(4, ll.peekFront())
    }

    @Test
    fun peekBack() {
        val ll = MyLinkedList<Int>()
        assertEquals(null, ll.peekBack())
        ll.pushFront(3)
        ll.pushFront(4)
        assertEquals(3, ll.peekBack())
    }

    @Test
    fun isEmpty() {
        val ll = MyLinkedList<Int>()
        assertEquals(true, ll.isEmpty())
        ll.pushFront(3)
        assertEquals(false, ll.isEmpty())
    }

}