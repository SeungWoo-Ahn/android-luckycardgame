package io.softeer.luckycardgame.player

import io.softeer.luckycardgame.adapter.CardAdapter
import io.softeer.luckycardgame.card.Card

class Player constructor(
    deck: MutableList<Card>,
    playerIndex : Int,
    cardCount : Int
) {
    private var isMine = playerIndex == 0
    private val cardList = deck.subList(playerIndex*cardCount, (playerIndex+1)*cardCount)

    fun adapterByPlayer() : CardAdapter = CardAdapter(cardList, isMine)

}