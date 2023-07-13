package io.softeer.luckycardgame.game

import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.card.CardType
import io.softeer.luckycardgame.player.Player
import io.softeer.luckycardgame.util.PlayerManager
import org.junit.Assert.*
import org.junit.Test

class Player_RemoveCardTest{

    private val playerCardAllDifferent = Player(
        mutableListOf(
            Card(5,CardType.Dog),
            Card(3,CardType.Cow),
            Card(2,CardType.Dog),
            Card(4,CardType.Cat),
            Card(1,CardType.Dog)
        ),
        0
    )

    private val playerCardPartDifferent = Player(
        mutableListOf(
            Card(5,CardType.Dog),
            Card(1,CardType.Cow),
            Card(2,CardType.Dog),
            Card(1,CardType.Cat),
            Card(1,CardType.Dog)
        ),
        0
    )

    private val playerCardAllSame = Player(
        mutableListOf(
            Card(1,CardType.Cat),
            Card(1,CardType.Cow),
            Card(1,CardType.Dog)
        ),
        0
    )

/*    @Test
    fun removeCards_cardsAllDifferent_removeNone() {
        val expected = listOf<Card>()
        val actual = PlayerManager.removePlayerSameNumbers(playerCardAllDifferent)
        assertEquals(expected, actual)
    }

    @Test
    fun removeCards_cardsDifferentPart_removePart() {
        val expected = listOf(
            Card(1, CardType.Cow),
            Card(1, CardType.Cat),
            Card(1, CardType.Dog)
        )
        val actual = PlayerManager.removePlayerSameNumbers(playerCardPartDifferent)
        assertEquals(expected, actual)
    }

    @Test
    fun removeCards_cardsAllSame_removeAll() {
        val expected = listOf(
            Card(1,CardType.Cat),
            Card(1,CardType.Cow),
            Card(1,CardType.Dog)
        )
        val actual = PlayerManager.removePlayerSameNumbers(playerCardAllSame)
        assertEquals(expected, actual)
    }*/
}