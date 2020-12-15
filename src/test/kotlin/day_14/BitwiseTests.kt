package day_14

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BitwiseTests {

    @Test
    fun `Bitmask on 11 should return 73`(){
        val bitmask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"
        val transformedValue = applyBitmask(11,bitmask)
        assertEquals(73,transformedValue)
    }

    @Test
    fun `Bitmask on 101 should return 101`(){
        val bitmask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"
        val transformedValue = applyBitmask(101,bitmask)
        assertEquals(101,transformedValue)
    }

    @Test
    fun `Bitmask on 0 should return 64`(){
        val bitmask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"
        val transformedValue = applyBitmask(0,bitmask)
        assertEquals(64,transformedValue)
    }
}