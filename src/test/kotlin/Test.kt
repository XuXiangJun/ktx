import com.github.xuxiangjun.javaext.toInt
import com.github.xuxiangjun.javaext.toLong
import kotlin.test.Test
import kotlin.test.assertEquals

class Test {

    @Test
    fun byteArrayTest() {
        var data = byteArrayOf(1, 2, 3, 4)
        assertEquals(0x04030201, data.toInt())
        assertEquals(0x01020304, data.toInt(true))

        data = byteArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(0x0807060504030201L, data.toLong())
        assertEquals(0x0102030405060708, data.toLong(true))
    }
}
