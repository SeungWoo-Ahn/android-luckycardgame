package io.softeer.luckycardgame.util

import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.player.Player

object PlayerManager {
    fun providePlayerForGame(
        playerNumber: Int,
        gameDeck: MutableList<Card>,
        matchPool : MutableList<Int>,
        endGame : () -> Unit
    ) : MutableList<Player> {
        val playerList = mutableListOf<Player>()
        for (index in 0 until  playerNumber) {
            val player = makePlayer(gameDeck, index, playerNumber)
            sortPlayerCards(player)
            val removeList = player.removeSameNumbers()
            player.showPlayerCardsInfo()
            if (checkPlayerCardsBeforeGame(removeList, matchPool)) endGame()
            playerList.add(player)
        }
        return playerList
    }

    fun makePlayer(gameDeck: MutableList<Card>, index : Int, playerNumber : Int) : Player {
        val eachCards = gameDeck.slice(index*(11-playerNumber) until  (index+1)*(11-playerNumber)).toMutableList()
        return Player(eachCards,index)
    }

    fun sortPlayerCards(player: Player) : List<Card> {
        player.sortCardList()
        return player.cardList
    }

    private fun checkPlayerCardsBeforeGame(removeList: List<Int>, matchList: MutableList<Int>) : Boolean {
        for (matchNumber in removeList) {
            return GameManager.checkGameNeedEnd(matchNumber, matchList)
        }
        return false
    }

}