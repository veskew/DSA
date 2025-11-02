import org.example.MyAssociativeArray
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MyAssociativeArrayTest {
    @Test
    fun set() {
        val map = MyAssociativeArray<String, Int>(193)
        map["hello"] = 1
        map["world"] = 2
        map["!"] = 3
        assertEquals(1, map["hello"])
        assertEquals(null, map["Kotlin"])
    }

    @Test
    fun contains() {
        val map = MyAssociativeArray<String, Int>(193)
        map["hello"] = 1
        map["world"] = 2
        map["!"] = 3
        assertTrue("hello" in map)
        assertFalse("kotlin" in map)
    }

    @Test
    fun get() {
        val map = MyAssociativeArray<String, Int>(193)
        map["hello"] = 1
        map["world"] = 2
        map["!"] = 3
        assertEquals(1, map["hello"])
        assertEquals(null, map["Kotlin"])
    }

    @Test
    fun remove() {
        val map = MyAssociativeArray<String, Int>(193)
        map["hello"] = 1
        map["world"] = 2
        map["!"] = 3
        assertEquals(true, map.remove("hello"))
        assertEquals(false, map.remove("Kotlin"))
        assertEquals(false, map.contains("hello"))
    }

    @Test
    fun size() {
        val map = MyAssociativeArray<String, Int>(193)
        assertEquals(0, map.size())
        map["hello"] = 1
        map["world"] = 2
        map["!"] = 3
        assertEquals(3, map.size())
        map.remove("world")
        assertEquals(2, map.size())
    }

    @Test
    fun keyValuePairs() {
        val map = MyAssociativeArray<String, Int>(193)
    }

    @Test
    fun hash() {
        val map = MyAssociativeArray<String, Int>(193)
    }

}