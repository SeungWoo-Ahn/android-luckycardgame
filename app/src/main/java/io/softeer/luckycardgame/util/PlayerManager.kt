package io.softeer.luckycardgame.util

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.player.Player

object PlayerManager {

    fun MutableList<Player>.providePlayerForGame(
        playerNumber: Int,
        deck: MutableList<Card>,
        recyclerViewList: List<RecyclerView>,
        context: Context
    ) {
        for(index in 0 until  playerNumber) {
            val player = Player(deck, playerIndex = index, 11- playerNumber)
            player.sortCardList()
            CardManager.showAllCardInfo(player.cardList, index)
            this.add(player)
            ViewUtil.setRecycler(
                recyclerViewList[index],
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false),
                rightSpace = ViewUtil.setItemSpace(playerNumber),
                topSpace = 0,
                adapter =  player.adapterByPlayer()
            )
        }
    }
}