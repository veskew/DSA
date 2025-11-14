import org.example.MyKDTree
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MyKDTreeTest {
    @Test
    fun construct() {
        val points = listOf(listOf(-2.0, 8.0), listOf(-3.0, 3.0), listOf(-5.0, -5.0), listOf(-1.0, -7.0), listOf(2.0, -3.0), listOf(4.0, 1.0), listOf(1.0, 6.0))
        val tree = MyKDTree()
        tree.construct(points)
        assertNotNull(tree.head)
        assertEquals(tree.head?.value, listOf(-1.0, -7.0))
        assertEquals(tree.head?.right?.right?.value, listOf(1.0, 6.0))

    }

    @Test
    fun search() {
        val points = listOf(listOf(-2.0, 8.0), listOf(-3.0, 3.0), listOf(-5.0, -5.0), listOf(-1.0, -7.0), listOf(2.0, -3.0), listOf(4.0, 1.0), listOf(1.0, 6.0))
        val tree = MyKDTree()
        tree.construct(points)
        assertEquals(tree.search(listOf(4.1, 0.9)), listOf(4.0, 1.0))
    }

}