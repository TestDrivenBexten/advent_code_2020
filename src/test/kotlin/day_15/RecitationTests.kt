package day_15

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class RecitationTests {
    @Test
    fun `Small Recitation Examples`(){
        assertAll(
            { assertEquals(436,reciteMemory("0,3,6",2020)) },
            { assertEquals(1,reciteMemory("1,3,2",2020)) },
            { assertEquals(10,reciteMemory("2,1,3",2020)) },
            { assertEquals(27,reciteMemory("1,2,3",2020)) },
            { assertEquals(78,reciteMemory("2,3,1",2020)) },
            { assertEquals(438,reciteMemory("3,2,1",2020)) },
            { assertEquals(1836,reciteMemory("3,1,2",2020)) }
        )
    }

    @Test
    fun `Puzzle Recitation Test`(){
        assertEquals(1009,reciteMemory("2,0,1,9,5,19",2020))
    }

    @Test
    fun `Large Recitation Examples`(){
        assertAll(
                { assertEquals(175594,reciteMemory("0,3,6",30000000)) },
                { assertEquals(2578,reciteMemory("1,3,2",30000000)) },
                { assertEquals(3544142,reciteMemory("2,1,3",30000000)) },
                { assertEquals(261214,reciteMemory("1,2,3",30000000)) },
                { assertEquals(6895259,reciteMemory("2,3,1",30000000)) },
                { assertEquals(18,reciteMemory("3,2,1",30000000)) },
                { assertEquals(362,reciteMemory("3,1,2",30000000)) }
        )
    }


    @Test
    fun `Puzzle Large Recitation Test`(){
        assertEquals(62714,reciteMemory("2,0,1,9,5,19",30000000))
    }
}