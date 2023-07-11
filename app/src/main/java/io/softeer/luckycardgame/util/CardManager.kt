package io.softeer.luckycardgame.util

import android.util.Log
import io.softeer.luckycardgame.adapter.CardAdapter
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

    fun getDeck() = deck

    /**
     * 게임을 위한 덱 제공
     */
    fun MutableList<Card>.provideDeckForGame(playerNumber : Int) {
        addAll(deck.shuffled())
        if (playerNumber == 3) exceptCard(this, cardNumber = 12)
    }

    /**
     * 남은 카드 하단 보드에 두기
     */
    fun MutableList<Card>.putRemainCards(
        playerNumber: Int,
        onCardClick : (Card,Int)->Unit,
        adapterList : MutableList<CardAdapter>
    ) {
        val remainCardList = this.subList(playerNumber*(11-playerNumber), this.size)
        remainCardList.let {
            it.sort()
            showAllCardInfo(it, null)
            adapterList.add(
                CardAdapter(it,false, onCardClick)
            )
        }
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
     * 덱에서 특정 숫자 제거
     */
    private fun exceptCard(deck : MutableList<Card>, cardNumber : Int): Boolean = deck.removeIf { it.getCardNumber() == cardNumber }

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

    /**
     * 카드 숫자가 같은지 체크
     */
    fun checkCardsNumberSame(cardList: MutableList<Card>): Boolean {
        val counts = cardList.groupingBy { it.getCardNumber() }.eachCount()
        return counts.any { it.value >= 3 }
    }

}