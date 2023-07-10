package io.softeer.luckycardgame.player

import android.util.Log
import io.softeer.luckycardgame.adapter.CardAdapter
import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.util.CardManager

class Player(
    deck: MutableList<Card>,
    playerIndex : Int,
    cardCount : Int
) {
    private val isMine = playerIndex == 0
    val cardList = deck.subList(playerIndex*cardCount, (playerIndex+1)*cardCount)
    private val cardAdapter = CardAdapter(cardList, isMine)

    /**
     * 참가자 카드 정렬
     */
    fun sortCardList() = cardList.sort()

    fun adapterByPlayer() : CardAdapter {
        CardManager.checkCardsNumberSame(cardList)
        return cardAdapter
    }

}