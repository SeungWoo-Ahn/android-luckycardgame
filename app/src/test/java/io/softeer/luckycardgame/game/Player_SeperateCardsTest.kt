package io.softeer.luckycardgame.game

import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.util.CardManager
import io.softeer.luckycardgame.util.PlayerManager
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class Player_SeperateCardsTest {

    private val deck = mutableListOf<Card>()
    private val deckForThreePlayer = mutableListOf<Card>()

    @Before
    fun setUp() {
        deck.addAll(CardManager.provideDeckForGame(4))
        deckForThreePlayer.addAll(CardManager.provideDeckForGame(3))
    }

    @Test
    fun makePlayer_totalThreePlayers_eightCardsEach() {
        val expected = 8
        val actual = PlayerManager.makePlayer(deckForThreePlayer, 0, 3).cardList.size
        assertEquals(expected, actual)
    }

    @Test
    fun makePlayer_totalFourPlayers_sevenCardsEach() {
        val expected = 7
        val actual = PlayerManager.makePlayer(deck, 0, 4).cardList.size
        assertEquals(expected, actual)
    }

    @Test
    fun makePlayer_totalFivePlayers_sixCardsEach() {
        val expected = 6
        val actual = PlayerManager.makePlayer(deck, 0, 5).cardList.size
        assertEquals(expected, actual)
    }

}