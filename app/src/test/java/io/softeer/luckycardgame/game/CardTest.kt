package io.softeer.luckycardgame.game

import io.softeer.luckycardgame.util.CardManager
import org.junit.Assert.*
import org.junit.Test

class CardTest {

    @Test
    fun provideDeck_forThreePlayer_thirtyThreeCardsCreate() {
        val expected = 33
        val actual = CardManager.provideDeckForGame(3).size
        assertEquals(expected, actual)
    }

    @Test
    fun provideDeck_forFourPlayer_thirtySixCardsCreate() {
        val expected = 36
        val actual = CardManager.provideDeckForGame(4).size
        assertEquals(expected, actual)
    }

    @Test
    fun provideDeck_forFivePlayer_thirtySixCardsCreate() {
        val expected = 36
        val actual = CardManager.provideDeckForGame(5).size
        assertEquals(expected, actual)
    }

}