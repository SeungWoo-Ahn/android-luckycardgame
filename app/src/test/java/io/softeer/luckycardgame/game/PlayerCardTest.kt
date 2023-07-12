package io.softeer.luckycardgame.game

import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.card.CardType
import io.softeer.luckycardgame.player.Player
import io.softeer.luckycardgame.util.PlayerManager
import org.junit.Assert.*
import org.junit.Test

class PlayerCardTest {

    private val cardsAllDifferent = listOf(
        Card(5,CardType.Dog),
        Card(3,CardType.Cow),
        Card(2,CardType.Dog),
        Card(4,CardType.Cat),
        Card(1,CardType.Dog)
    )

    private val cardsDifferentNumberSameType = listOf(
        Card(5,CardType.Dog),
        Card(3,CardType.Dog),
        Card(2,CardType.Dog),
        Card(4,CardType.Dog),
        Card(1,CardType.Dog)
    )

    private val cardsDifferentTypeSameNumber = listOf(
        Card(1,CardType.Cat),
        Card(1,CardType.Cow),
        Card(1,CardType.Dog)
    )

    private val cardsAllSame = listOf(
        Card(1,CardType.Cat),
        Card(1,CardType.Cat),
        Card(1,CardType.Cat)
    )

    private val playerCardAllDifferent = Player(cardsAllDifferent.toMutableList(), 0)

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

    private val playerCardAllSame = Player(cardsDifferentTypeSameNumber.toMutableList(),0)

    @Test
    fun sortCards_cardsAllDifferent_sortNumberType() {
        val expected = listOf(
            Card(1,CardType.Dog),
            Card(2,CardType.Dog),
            Card(3,CardType.Cow),
            Card(4,CardType.Cat),
            Card(5,CardType.Dog)
        )
        val actual = PlayerManager.sortPlayerCards(playerCardAllDifferent)
        assertEquals(expected, actual)
    }

    @Test
    fun sortCards_cardsDifferentNumberSameType_sortNumberType() {
        val expected = listOf(
            Card(1,CardType.Dog),
            Card(2,CardType.Dog),
            Card(3,CardType.Dog),
            Card(4,CardType.Dog),
            Card(5,CardType.Dog)
        )
        val actual = PlayerManager.sortPlayerCards(Player(cardsDifferentNumberSameType.toMutableList(), 0))
        assertEquals(expected, actual)
    }

    @Test
    fun sortCards_cardsDifferentTypeSameNumber_sortType() {
        val expected = listOf(
            Card(1,CardType.Dog),
            Card(1,CardType.Cat),
            Card(1,CardType.Cow)
        )
        val actual = PlayerManager.sortPlayerCards(playerCardAllSame)
        assertEquals(expected, actual)
    }

    @Test
    fun sortCards_cardsAllSame_sortNone() {
        val expected = listOf(
            Card(1,CardType.Cat),
            Card(1,CardType.Cat),
            Card(1,CardType.Cat)
        )
        val actual = PlayerManager.sortPlayerCards(Player(cardsAllSame.toMutableList(), 0))
        assertEquals(expected, actual)
    }

    @Test
    fun removeCards_cardsAllDifferent_removeNone() {
        val expected = listOf<Card>()
        val actual = PlayerManager.removePlayerSameNumbers(playerCardAllDifferent)
        assertEquals(expected, actual)
    }

    @Test
    fun removeCards_cardsDifferentPart_removePart() {
        val expected = listOf(
            Card(1,CardType.Cow),
            Card(1,CardType.Cat),
            Card(1,CardType.Dog)
        )
        val actual = PlayerManager.removePlayerSameNumbers(playerCardPartDifferent)
        assertEquals(expected, actual)
    }

    @Test
    fun removeCards_cardsAllSame_removeAll() {
        val expected = cardsDifferentTypeSameNumber
        val actual = PlayerManager.removePlayerSameNumbers(playerCardAllSame)
        assertEquals(expected, actual)
    }

}