package io.softeer.luckycardgame.game

import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.card.CardType
import io.softeer.luckycardgame.player.Player
import io.softeer.luckycardgame.util.GameManager
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class Game_FindWinnerTest {


    private val matchNoneList = listOf<Int>()
    private val matchOneList = listOf(1)
    private val matchTwoList = listOf(2,8)
    private val matchTwoWinList = listOf(3,10)

    private val playerZero= Player(mutableListOf(Card(1,CardType.Dog)),0)
    private val playerOne= Player(mutableListOf(Card(1,CardType.Dog)),1)
    private val playerTwo= Player(mutableListOf(Card(1,CardType.Dog)),2)
    private val playerThree= Player(mutableListOf(Card(1,CardType.Dog)),3)

    @Before
    fun setUp() {
        playerZero.matchList.addAll(matchNoneList)
        playerOne.matchList.addAll(matchOneList)
        playerTwo.matchList.addAll(matchTwoList)
        playerThree.matchList.addAll(matchTwoWinList)
    }

    @Test
    fun findWinner_withTwoPlayer_oneWinner() {
        val expected = listOf(3)
        val actual = GameManager.findWinner(mutableListOf(1,3,10), mutableListOf(playerOne,playerThree))
        assertEquals(expected, actual)
    }

    @Test
    fun findWinner_withThreePlayer_oneWinner() {
        val expected = listOf(3)
        val actual = GameManager.findWinner(mutableListOf(1,3,10), mutableListOf(playerZero,playerOne,playerThree))
        assertEquals(expected, actual)
    }

    @Test
    fun findWinner_withThreePlayer_twoWinner() {
        val expected = listOf(1,2)
        val actual = GameManager.findWinner(mutableListOf(1,2,8), mutableListOf(playerZero,playerOne,playerTwo))
        assertEquals(expected, actual)
    }

    @Test
    fun findWinner_withThreePlayer_threeWinner() {
        val expected = listOf(1,2,3)
        val actual = GameManager.findWinner(mutableListOf(1,2,3,8,10), mutableListOf(playerOne,playerTwo,playerThree))
        assertEquals(expected, actual)
    }
}