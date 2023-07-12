package io.softeer.luckycardgame.player

import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.util.CardManager

class Player(
    val cardList: MutableList<Card>,
    private val playerId : Int,
) {
    val me = playerId == 0
    private var selectCount = 0
    fun showPlayerCardsInfo() = CardManager.showAllCardInfo(cardList, playerId)

    fun canSelectCard() : Boolean {
        if (selectCount == 3) {
            selectCount = 0
            return false
        }
        return true
    }

    fun selectCard() {
        selectCount++
    }

    fun sortCardList() {
        cardList.sort()
    }

    fun removeSameNumbers() : List<Card> {
        val counts = mutableMapOf<Int, Int>()
        val result = mutableListOf<Card>()
        for (card in cardList) {
            val number = card.getCardNumber()
            counts[number] = counts.getOrDefault(number, 0) + 1
        }
        for (card in cardList) {
            val number = card.getCardNumber()
            if (counts[number] == 3) {
                result.add(card)
            }
        }
        removeCards(result)
        return result
    }

    fun removeCards(selectedCards : List<Card>) {
        cardList.removeAll(selectedCards)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Player) return false
        return playerId == other.playerId
    }
}