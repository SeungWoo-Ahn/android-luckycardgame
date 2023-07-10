package io.softeer.luckycardgame.util

import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.card.CardType
import org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CardManagerTest {

    private val cardList = mutableListOf(
        Card(1,CardType.Dog),
        Card(2,CardType.Dog),
        Card(3,CardType.Dog),
        Card(4,CardType.Dog),
        Card(5,CardType.Dog),
        Card(6,CardType.Dog),
        Card(7,CardType.Dog),
        Card(8,CardType.Dog),
        Card(9,CardType.Dog),
        Card(10,CardType.Dog),
        Card(11,CardType.Dog),
        Card(12,CardType.Dog),
        Card(1,CardType.Cat),
        Card(2,CardType.Cat),
        Card(3,CardType.Cat),
        Card(4,CardType.Cat),
        Card(5,CardType.Cat),
        Card(6,CardType.Cat),
        Card(7,CardType.Cat),
        Card(8,CardType.Cat),
        Card(9,CardType.Cat),
        Card(10,CardType.Cat),
        Card(11,CardType.Cat),
        Card(12,CardType.Cat),
        Card(1,CardType.Cow),
        Card(2,CardType.Cow),
        Card(3,CardType.Cow),
        Card(4,CardType.Cow),
        Card(5,CardType.Cow),
        Card(6,CardType.Cow),
        Card(7,CardType.Cow),
        Card(8,CardType.Cow),
        Card(9,CardType.Cow),
        Card(10,CardType.Cow),
        Card(11,CardType.Cow),
        Card(12,CardType.Cow),
    )

    private val deck = CardManager.getDeck()

    @Test
    fun provideDeckForGame() {
        compareTwoList(cardList,deck)
    }

    @Test
    fun putRemainCards() {
        var expectedList = makeRemainListByPlayer(cardList,3)
        var actualList = makeRemainListByPlayer(deck, 3)
        compareTwoList(expectedList, actualList)

        expectedList = makeRemainListByPlayer(cardList,4)
        actualList = makeRemainListByPlayer(deck, 4)
        compareTwoList(expectedList, actualList)

        expectedList = makeRemainListByPlayer(cardList,5)
        actualList = makeRemainListByPlayer(deck, 5)
        compareTwoList(expectedList, actualList)
    }

    private fun compareTwoList(expectedList: MutableList<Card>, actualList : MutableList<Card>) {
        for(index in expectedList.indices) {
            assertSame(expectedList[index].getCardNumber(),actualList[index].getCardNumber())
            assertSame(expectedList[index].getCardType().name, actualList[index].getCardType().name)
        }
    }

    private fun makeRemainListByPlayer(cardList: MutableList<Card> ,playerNumber : Int) : MutableList<Card> {
        return cardList.subList(playerNumber*(11-playerNumber), cardList.size)
    }

    @Test
    fun checkCardsNumberSame() {
        val same = mutableListOf(cardList[0],cardList[0],cardList[0])
        val differentOne = mutableListOf(cardList[0],cardList[12],cardList[25])
        val differentAll = mutableListOf(cardList[0],cardList[16],cardList[25])
        assertTrue(CardManager.checkCardsNumberSame(same))
        assertFalse(CardManager.checkCardsNumberSame(differentOne))
        assertFalse(CardManager.checkCardsNumberSame(differentAll))
    }
}