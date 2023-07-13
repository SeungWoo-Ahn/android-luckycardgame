package io.softeer.luckycardgame.game

import io.softeer.luckycardgame.util.GameManager
import org.junit.Assert.*
import org.junit.Test

class Game_CheckEndTest{

    private val resultList = mutableListOf(1,3,9)

    @Test
    fun checkEndTest_findSeven_returnTrue() {
        val actual = GameManager.checkGameNeedEnd(7, resultList, false)
        assertTrue(actual)
    }

    @Test
    fun checkEndTest_sumIsSeven_returnTrue() {
        val actual = GameManager.checkGameNeedEnd(6, resultList, false)
        assertTrue(actual)
    }

    @Test
    fun checkEndTest_subtractIsSeven_returnTrue() {
        val actual = GameManager.checkGameNeedEnd(2, resultList, false)
        assertTrue(actual)
    }

    @Test
    fun checkEndTest_findNothing_returnFalse() {
        val actual = GameManager.checkGameNeedEnd(12, resultList, false)
        assertFalse(actual)
    }

}