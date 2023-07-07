package io.softeer.luckycardgame.util

import android.util.Log
import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.card.CardType

object CardManager {

    private val deck = mutableListOf<Card>()

    /**
     * 초기화 : 카드 없으면 만들기
     */
    init {
        if (deck.isEmpty()) makeDeck()
    }

    /**
     * 게임을 위한 덱 제공
     */
    fun MutableList<Card>.provideDeckForGame(playerNumber : Int) {
        addAll(deck.shuffled())
        if (playerNumber == 3) exceptCard(this, cardNumber = 12)
        Log.i("GAME DECK", showAllCardInfo(this))
    }

    /**
     * 카드 만들기
     */
    private fun makeDeck() {
        for (type in CardType.values()) {
            for(number in Card.MIN_NUMBER..Card.MAX_NUMBER) {
                deck.add(Card(number, type))
            }
        }
    }

    /**
     * 덱에서 특정 숫자 제거하기
     */
    private fun exceptCard(deck : MutableList<Card>,cardNumber : Int): Boolean = deck.removeIf { it.getCardNumber() == cardNumber }

    /**
     * 모든 카드 정보 보기
     */
    private fun showAllCardInfo(deck: MutableList<Card>) : String {
        val infoList = mutableListOf<String>()
        deck.forEach {
            infoList.add(it.cardInfo())
        }
        return infoList.joinToString(", ")
    }

}