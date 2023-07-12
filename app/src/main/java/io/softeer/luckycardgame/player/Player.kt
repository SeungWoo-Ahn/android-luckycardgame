package io.softeer.luckycardgame.player

import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.util.CardManager

class Player(
    val cardList: MutableList<Card>,
    private val playerId : Int,
) {
    val me = playerId == 0
    private fun showPlayerCardsInfo() = CardManager.showAllCardInfo(cardList, playerId)

    fun sortCardList() {
        cardList.sort()
//        showPlayerCardsInfo()
    }

}