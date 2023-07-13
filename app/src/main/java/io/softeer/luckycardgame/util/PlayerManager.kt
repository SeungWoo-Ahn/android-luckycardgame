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
            sortPlayerCards(player)
            player.showPlayerCardsInfo()
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

    fun checkPlayerQueueNeedEnd(playerQueue: Queue<Player>, matchPool: MutableList<Int>) : Boolean {
        var needEnd = false
        while (playerQueue.peek() != null) {
            val playerRemoveList = playerQueue.poll()!!.removeSameNumbers()
            if (checkPlayerCardsBeforeGame(playerRemoveList, matchPool)) {
                needEnd = true
            }
        }
        return needEnd
    }

    private fun checkPlayerCardsBeforeGame(removeList: List<Int>, matchPool: MutableList<Int>) : Boolean {
        for (matchNumber in removeList) {
            return GameManager.checkGameNeedEnd(matchNumber, matchPool, false)
        }
        return false
    }

}