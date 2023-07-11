package io.softeer.luckycardgame.util

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.softeer.luckycardgame.adapter.CardAdapter
import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.player.Player

object PlayerManager {

    fun MutableList<Player>.providePlayerForGame(
        playerNumber: Int,
        deck: MutableList<Card>,
        onCardClick : (Card,Int) -> Unit,
        adapterList: MutableList<CardAdapter>
    ) {
        for(index in 0 until  playerNumber) {
            val player = Player(deck, playerId = index, 11-playerNumber)
            player.let {
                it.sortCardList()
                this.add(it)
                adapterList.add(
                    CardAdapter(it.cardList, index==0, onCardClick)
                )
            }
        }
    }
}