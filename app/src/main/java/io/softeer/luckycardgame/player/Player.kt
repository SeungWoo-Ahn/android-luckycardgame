package io.softeer.luckycardgame.player

import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.util.CardManager

class Player(
    deck: MutableList<Card>,
    playerId : Int,
    cardCount : Int
) {

    private val id = playerId
    val cardList = deck.subList(id*cardCount, (id+1)*cardCount)
    val selectCards = mutableListOf<Card>()

    /**
     * 참가자 카드 정렬
     */
    fun sortCardList() {
        cardList.let {
            it.sort()
            CardManager.showAllCardInfo(it,id)
        }
    }

}