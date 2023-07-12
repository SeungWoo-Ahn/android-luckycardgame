package io.softeer.luckycardgame.game

import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.card.CardType
import io.softeer.luckycardgame.util.GameManager
import org.junit.Assert.*
import org.junit.Test

class Game_CardsSameTest {

    private val allDifferentCards = listOf(
        Card(1, CardType.Dog),
        Card(2, CardType.Cow),
        Card(3, CardType.Cat),
    )

    private val partSameCards = listOf(
        Card(1, CardType.Dog),
        Card(1, CardType.Cat),
        Card(2, CardType.Cow),
    )

    private val allSameCards = listOf(
        Card(1, CardType.Dog),
        Card(1, CardType.Cat),
        Card(1, CardType.Cow),
    )

    @Test
    fun checkCardsSame_allDifferent_returnFalse() {
        val actual = GameManager.checkSelectedCardsSame(allDifferentCards)
        assertFalse(actual)
    }

    @Test
    fun checkCardsSame_partSame_returnFalse() {
        val actual = GameManager.checkSelectedCardsSame(partSameCards)
        assertFalse(actual)
    }

    @Test
    fun checkCardsSame_allSame_returnTrue() {
        val actual = GameManager.checkSelectedCardsSame(allSameCards)
        assertTrue(actual)
    }

}