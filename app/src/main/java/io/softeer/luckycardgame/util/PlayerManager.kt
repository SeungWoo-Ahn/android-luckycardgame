package io.softeer.luckycardgame.util

import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.player.Player

object PlayerManager {
    /*fun providePlayerForGame(
        playerNumber: Int,
        gameDeck: MutableList<Card>,
    ) : MutableList<Player> {
        val playerList = mutableListOf<Player>()
        val cardCount = 11 - playerNumber
        for (index in 0 until  playerNumber) {
            val eachCards = gameDeck.slice(index*cardCount until  (index+1)*cardCount).toMutableList()
            val player = Player(eachCards,index)
            player.sortCardList()
            playerList.add(player)
        }
        return playerList
    }*/
}