import org.example.MyMatrix
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MyMatrixTest {
    @Test
    fun get() {
        val mat = MyMatrix(8)
        assertEquals(mat[0, 0], 0.0)
    }

    @Test
    fun set() {
        val mat = MyMatrix(8)
        mat[0, 0] = 6.7
        assertEquals(mat[0, 0], 6.7)
        assertEquals(mat[5, 4], 0.0)
    }

    @Test
    fun plus() {
        val mat1 = MyMatrix(8)
        val mat2 = MyMatrix(8)
        mat1[0, 0] = 4.5
        mat2[0, 0] = 5.5
        val mat3 = mat1 + mat2
        assertEquals(mat3[0, 0], 10.0)
        assertEquals(mat3[4, 7], 0.0)
    }

    @Test
    fun minus() {
        val mat1 = MyMatrix(8)
        val mat2 = MyMatrix(8)
        mat1[0, 0] = 4.5
        mat2[0, 0] = 5.5
        val mat3 = mat1 - mat2
        assertEquals(mat3[0, 0], -1.0)
        assertEquals(mat3[4, 7], 0.0)
    }

    @Test
    fun divide() {
        val mat1 = MyMatrix(8)
        val mats = mat1.divide()
        assertEquals(mats.size, 4)
    }

    @Test
    fun multiply() {
        val one = MyMatrix(2)
        val two = MyMatrix(2)
        one.set(0, 0, 1.0)
        one[0, 0] = 1.0
        one[1, 0] = 2.0
        one[1, 1] = 3.0
        one[0, 1] = 4.0
        two[0, 0] = 1.0
        two[1, 0] = 2.0
        two[1, 1] = 3.0
        two[0, 1] = 4.0
        val three = one.multiply(two)
        assertEquals(three[0, 0], 9.0)
        assertEquals(three[1, 0], 8.0)
        assertEquals(three[0, 1], 16.0)
        assertEquals(three[1, 1], 17.0)
    }

    @Test
    fun strassenMultiply() {
        val one = MyMatrix(2)
        val two = MyMatrix(2)
        one.set(0, 0, 1.0)
        one[0, 0] = 1.0
        one[1, 0] = 2.0
        one[1, 1] = 3.0
        one[0, 1] = 4.0
        two[0, 0] = 1.0
        two[1, 0] = 2.0
        two[1, 1] = 3.0
        two[0, 1] = 4.0
        val three = one.strassenMultiply(two)
        assertEquals(three[0, 0], 9.0)
        assertEquals(three[1, 0], 8.0)
        assertEquals(three[0, 1], 16.0)
        assertEquals(three[1, 1], 17.0)
    }

}