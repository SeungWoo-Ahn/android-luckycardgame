package io.softeer.luckycardgame.util

import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.player.Player
import java.util.Queue

object PlayerManager {
    fun providePlayerForGame(
        playerNumber: Int,
        gameDeck: MutableList<Card>,
    ) : MutableList<Player> {
        val playerList = mutableListOf<Player>()
        for (index in 0 until  playerNumber) {
            val player = makePlayer(gameDeck, index, playerNumber)
            player.sortCardList()
            player.removeSameNumbers()
            player.showPlayerCardsInfo()
            playerList.add(player)
        }
        return playerList
    }

    fun makePlayer(gameDeck: MutableList<Card>, index : Int, playerNumber : Int) : Player {
        val eachCards = gameDeck.slice(index*(11-playerNumber) until  (index+1)*(11-playerNumber)).toMutableList()
        return Player(eachCards,index)
    }

    fun checkPlayerListNeedEnd(playerList: MutableList<Player>, matchPool: MutableList<Int>) : Boolean {
        var needEnd = false
        for (player in playerList) {
            val matchListByPlayer = player.matchSet
            if (checkPlayerCardsBeforeGame(matchListByPlayer, matchPool)) {
                needEnd = true
            }
        }
        return needEnd
    }

    private fun checkPlayerCardsBeforeGame(removeList: MutableSet<Int>, matchPool: MutableList<Int>) : Boolean {
        var needEnd = false
        for (matchNumber in removeList) {
            if (GameManager.checkGameNeedEnd(matchNumber, matchPool, false)) {
                needEnd = true
            }
        }
        return needEnd
    }

}