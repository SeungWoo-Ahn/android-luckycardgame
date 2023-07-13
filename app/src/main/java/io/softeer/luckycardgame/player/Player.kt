package io.softeer.luckycardgame.player

import io.softeer.luckycardgame.adapter.CardAdapter
import io.softeer.luckycardgame.card.Card

class Player(
    private val cardList: List<Card>,
    private var isMine : Boolean
) {
    fun changeToMyCard() {
        isMine = true
    }
    fun adapterByPlayer() : CardAdapter = CardAdapter(cardList.toMutableList(), isMine)

}