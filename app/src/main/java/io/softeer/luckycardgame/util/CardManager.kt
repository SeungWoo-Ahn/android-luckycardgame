package io.softeer.luckycardgame.util

import android.util.Log
import io.softeer.luckycardgame.adapter.CardAdapter
import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.card.CardType

object CardManager {

    val deck = mutableListOf<Card>()

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
     * 게임을 위한 덱 제공
     */
    fun provideDeckForGame(playerNumber : Int) : MutableList<Card> {
        val gameDeck = deck.shuffled().toMutableList()
        if (playerNumber == 3) exceptCard(gameDeck, cardNumber = 12)
        return  gameDeck
    }

    /**
     * 덱에서 특정 숫자 제거
     */
    private fun exceptCard(deck : MutableList<Card>, cardNumber : Int) {
        deck.removeIf { it.getCardNumber() == cardNumber }
    }

    /**
     * 모든 카드 정보 보기
     */
    fun showAllCardInfo(cardList: MutableList<Card>, playerIndex : Int?) {
        val infoList = mutableListOf<String>()
        cardList.forEach {
            infoList.add(it.toString())
        }
        val placeText = if (playerIndex==null) "바닥" else ('A'+playerIndex).toString()
        Log.i(javaClass.name, "$placeText: [${infoList.joinToString(", ")}]")
    }


}