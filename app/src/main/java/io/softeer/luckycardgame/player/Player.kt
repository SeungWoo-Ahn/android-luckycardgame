package io.softeer.luckycardgame.player

import android.util.Log
import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.util.CardManager
import io.softeer.luckycardgame.util.GameManager

class Player(
    val cardList: MutableList<Card>,
    val playerId : Int,
) {
    val me = playerId == 0
    val matchList = mutableListOf<Int>()
    private val selectCards = mutableListOf<Card>()
    fun showPlayerCardsInfo() = CardManager.showAllCardInfo(cardList, playerId)

    fun canSelectCard() : Boolean {
        if (selectCards.size == 3) {
            selectCards.clear()
            return false
        }
        return true
    }

    fun selectCard(card: Card) : Boolean {
        sayMyTurn()
        selectCards.add(card)
        if (selectCards.size == 3) {
            val isMatch = GameManager.checkSelectedCardsSame(selectCards)
            if (isMatch) {
                matchList.add(selectCards[0].getCardNumber())
            }
            return isMatch
        }
        return false
    }

    fun sortCardList() {
        cardList.sort()
    }

    fun removeSameNumbers() : List<Int> {
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
                matchList.add(number)
            }
        }
        cardList.removeAll(result)
        return matchList
    }

    private fun sayMyTurn() {
        Log.i(javaClass.name, "${playerId}번 참가자 턴입니다.")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Player) return false
        return playerId == other.playerId
    }
}