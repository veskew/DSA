import org.example.MyStack
import org.example.validParentheses
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ValidParenthesesTest {
    @Test
    fun validParentheses() {
        val s1 = "(()"
        val s2 = "(())"
        val s3 = "[{}]()"
        val s4 = "({[)}]"
        assertEquals(false, validParentheses(s1))
        assertEquals(true, validParentheses(s2))
        assertEquals(true, validParentheses(s3))
        assertEquals(false, validParentheses(s4))
    }

}