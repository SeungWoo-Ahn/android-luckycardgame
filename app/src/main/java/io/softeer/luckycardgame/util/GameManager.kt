package io.softeer.luckycardgame.util

import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.player.Player
import java.util.Queue
import kotlin.math.abs

object GameManager {

    fun selectCardByPlayer(playerQueue: Queue<Player>) : Player {
        var currentPlayer = playerQueue.peek()!!
        if (!currentPlayer.canSelectCard()) {
            currentPlayer = changeCurrentPlayer(playerQueue)
        }
        currentPlayer.selectCard()
        return currentPlayer
    }

    private fun changeCurrentPlayer(playerQueue: Queue<Player>) : Player {
        playerQueue.offer(playerQueue.poll())
        return playerQueue.peek()!!
    }

    fun removeSelectedCardsByPlayer(
        player: Player,
        cards: List<Card>
    ) : List<Card> {
        player.removeCards(cards)
        return player.cardList
    }

    fun checkSelectedCardsSame(cards : List<Card>) : Boolean {
        return cards.all { it.getCardNumber() == cards[0].getCardNumber() }
    }

    fun checkGameNeedEnd(
        cardNumber: Int,
        resultList: MutableList<Int>
    ) : Boolean {
        if (cardNumber == 7) return true
        return resultList.any { it + cardNumber == 7 || abs(it - cardNumber) == 7 }
    }
}