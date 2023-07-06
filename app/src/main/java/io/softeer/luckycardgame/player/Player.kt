package io.softeer.luckycardgame.player

import io.softeer.luckycardgame.adapter.CardAdapter
import io.softeer.luckycardgame.card.Card

class Player constructor(
    val cardList: MutableList<Card>,
    private val isMine : Boolean
) {

    fun cardCount() : Int = cardList.size

    fun adapterByPlayer() : CardAdapter = CardAdapter(cardList, isMine)


}