package io.softeer.luckycardgame.util

import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.player.Player

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
}