package com.example.demo

import com.example.demo.ui.utils.updateCells
import com.example.demo.ui.models.Cell
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {
    @Test
    fun `Test updateCell`() {

        // Case 1: жизнь зарождается, если до этого трижды подряд создалась живая клетка
        val expectedCase1 = listOf( Cell.LIVING, Cell.LIVING, Cell.LIVING, Cell.LIVE)
        val actualCase1 = updateCells(listOf(Cell.LIVING, Cell.LIVING), Cell.LIVING) // actual
        assertEquals(expectedCase1, actualCase1)

        // Case 2: если трижды подряд родилась мёртвая клетка, жизнь рядом умирает.
        val expectedCase2 = listOf(Cell.DEAD, Cell.DEAD, Cell.DEAD, Cell.DEAD) // expected
        val actualCase2 = updateCells(listOf(Cell.LIVE, Cell.DEAD, Cell.DEAD), Cell.DEAD)
        assertEquals(expectedCase2, actualCase2)

    }
}