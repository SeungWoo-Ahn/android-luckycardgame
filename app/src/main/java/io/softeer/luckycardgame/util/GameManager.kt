package io.softeer.luckycardgame.util

import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.player.Player
import kotlin.math.abs

object GameManager {

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