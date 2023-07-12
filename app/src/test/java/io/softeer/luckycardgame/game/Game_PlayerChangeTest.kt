package io.softeer.luckycardgame.game

import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.card.CardType
import io.softeer.luckycardgame.player.Player
import io.softeer.luckycardgame.util.GameManager
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.LinkedList

class Game_PlayerChangeTest{

    private val playerOne = Player(
        mutableListOf(
            Card(1, CardType.Dog)
        ),
        1
    )
    private val playerTwo = Player(
        mutableListOf(
            Card(1, CardType.Dog)
        ),
        2
    )
    private val playerThree = Player(
        mutableListOf(
            Card(1, CardType.Dog)
        ),
        3
    )
    private val playerList = listOf(playerOne, playerTwo, playerThree)
    private val playerQueue = LinkedList(playerList)

    @Before
    fun setUp() {

    }

    @Test
    fun playerChange_selectThree_returnPlayerOne() {
        val expected = playerOne
        var actual = playerOne
        var count = 0
        while (count < 3) {
            actual = GameManager.selectCardByPlayer(playerQueue)
            count++
        }
        assertEquals(expected, actual)
    }

    @Test
    fun playerChange_selectSix_returnPlayerTwo() {
        val expected = playerTwo
        var actual = playerOne
        var count = 0
        while (count < 6) {
            actual = GameManager.selectCardByPlayer(playerQueue)
            count++
        }
        assertEquals(expected, actual)
    }

    @Test
    fun playerChange_selectNine_returnPlayerThree() {
        val expected = playerThree
        var actual = playerOne
        var count = 0
        while (count < 9) {
            actual = GameManager.selectCardByPlayer(playerQueue)
            count++
        }
        assertEquals(expected, actual)
    }

    @Test
    fun playerChange_selectTen_returnPlayerOne() {
        val expected = playerOne
        var actual = playerOne
        var count = 0
        while (count < 12) {
            actual = GameManager.selectCardByPlayer(playerQueue)
            count++
        }
        assertEquals(expected, actual)
    }
}