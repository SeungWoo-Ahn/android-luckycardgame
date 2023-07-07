package io.softeer.luckycardgame.util

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
     * 카드 섞기
     */
    private fun shuffleDeck() = deck.shuffle()

    /**
     * 덱에서 특정 숫자 제거하기
     */
    private fun exceptCard(cardNumber : Int) = deck.removeIf { it.getCardNumber() == cardNumber }

    /**
     * 카드 가져가기
     */
    fun getDeck(playerNumber : Int) : MutableList<Card> {
        if (playerNumber==3) exceptCard(cardNumber = 12)
        shuffleDeck()
        return deck
    }

    /**
     * 모든 카드 정보 보기
     */
    fun showAllCardInfo() : String {
        val infoList = mutableListOf<String>()
        deck.forEach {
            infoList.add(it.cardInfo())
        }
        return infoList.joinToString(", ")
    }
}