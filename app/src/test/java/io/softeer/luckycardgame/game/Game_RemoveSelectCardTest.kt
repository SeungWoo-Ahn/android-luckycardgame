package io.softeer.luckycardgame.game

import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.card.CardType
import io.softeer.luckycardgame.player.Player
import io.softeer.luckycardgame.util.GameManager
import org.junit.Assert.*
import org.junit.Test

class Game_RemoveSelectCardTest {

    private val playerOne = Player(
        mutableListOf(
            Card(1, CardType.Dog),
            Card(1, CardType.Cat),
            Card(8, CardType.Dog),
            Card(9, CardType.Dog),
            Card(10, CardType.Dog),
            Card(11, CardType.Dog),
            Card(12, CardType.Dog),
        ),
        0
    )

    private val playerTwo = Player(
        mutableListOf(
            Card(1, CardType.Cow),
            Card(2, CardType.Cat),
            Card(3, CardType.Dog),
            Card(4, CardType.Dog),
            Card(5, CardType.Dog),
            Card(6, CardType.Dog),
            Card(7, CardType.Dog),
        ),
        0
    )

/*    @Test
    fun removeSelectedCards_twoPlayers_removeNumberOne() {
        val expectedOne = mutableListOf(
            Card(8, CardType.Dog),
            Card(9, CardType.Dog),
            Card(10, CardType.Dog),
            Card(11, CardType.Dog),
            Card(12, CardType.Dog),
        )
        val actualOne = GameManager.removeSelectedCardsByPlayer(
            playerOne,
            listOf(
                Card(1, CardType.Dog),
                Card(1, CardType.Cat)
            )
        )
        val expectedTwo = mutableListOf(
            Card(2, CardType.Cat),
            Card(3, CardType.Dog),
            Card(4, CardType.Dog),
            Card(5, CardType.Dog),
            Card(6, CardType.Dog),
            Card(7, CardType.Dog),
        )
        val actualTwo = GameManager.removeSelectedCardsByPlayer(
            playerTwo,
            listOf(
                Card(1, CardType.Cow)
            )
        )
        assertEquals(expectedOne, actualOne)
        assertEquals(expectedTwo, actualTwo)
    }*/
}