package io.softeer.luckycardgame.game

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButtonToggleGroup
import io.softeer.luckycardgame.R
import io.softeer.luckycardgame.adapter.CardAdapter
import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.databinding.ActivityMainBinding
import io.softeer.luckycardgame.player.Player
import io.softeer.luckycardgame.util.CardManager
import io.softeer.luckycardgame.util.CardManager.provideDeckForGame
import io.softeer.luckycardgame.util.GameManager.selectCardByPlayer
import io.softeer.luckycardgame.util.GameManager.setGameResult
import io.softeer.luckycardgame.util.GameManager.updateGameUI
import io.softeer.luckycardgame.util.PlayerManager.checkPlayerQueueNeedEnd
import io.softeer.luckycardgame.util.PlayerManager.providePlayerForGame
import io.softeer.luckycardgame.util.ViewUtil
import java.util.LinkedList

class LuckyGame(
    private val bind : ActivityMainBinding,
    private val moveResult : () -> Unit
) : MaterialButtonToggleGroup.OnButtonCheckedListener {

    private val gameDeck = mutableListOf<Card>()
    private val playerList = mutableListOf<Player>()
    private val recyclerViewList : List<RecyclerView> =
        listOf(bind.rvA, bind.rvB, bind.rvC, bind.rvD, bind.rvE)
    private val bottomCards = mutableListOf<Card>()
    private val adapterList = mutableListOf<CardAdapter>()
    private val selectPool = mutableMapOf<Int,MutableList<Card>>()
    private val matchPool = mutableListOf<Int>()
    private var playerQueue = LinkedList<Player>()

    /*************************************************
     *** 화면 제어
     *************************************************/

    init {
        bind.toggleButton.addOnButtonCheckedListener(this)
    }

    override fun onButtonChecked(
        group: MaterialButtonToggleGroup?,
        checkedId: Int,
        isChecked: Boolean
    ) {
        when(group?.checkedButtonId) {
            R.id.button1 -> ViewUtil.changeBoardView(
                bind.groupBoardD,
                bind.groupBoardE,
                bind.tvBoardBottom,
                bind.rvBottom,
                3,
                ::play
            )

            R.id.button2 -> ViewUtil.changeBoardView(
                bind.groupBoardD,
                bind.groupBoardE,
                bind.tvBoardBottom,
                bind.rvBottom,
                4,
                ::play
            )

            R.id.button3 -> ViewUtil.changeBoardView(
                bind.groupBoardD,
                bind.groupBoardE,
                bind.tvBoardBottom,
                bind.rvBottom,
                5,
                ::play
            )
        }
    }

    private fun connectAdapter(playerNumber: Int) {
        for ((index, player) in playerList.withIndex()) {
            val adapter = CardAdapter(player.cardList,player.me,::selectCard, index)
            ViewUtil.setRecycler(
                recyclerViewList[index],
                layoutManager = LinearLayoutManager(bind.root.context, RecyclerView.HORIZONTAL, false),
                rightSpace = ViewUtil.setItemSpace(playerNumber),
                topSpace = 0,
                adapter
            )
            adapterList.add(adapter)
        }
        connectBottomAdapter(playerNumber)
    }

    private fun connectBottomAdapter(playerNumber: Int) {
        val cardCount = 11 - playerNumber
        val bottomCardList = gameDeck.slice(playerNumber*cardCount until  gameDeck.size).toMutableList()
        bottomCardList.sort()
        CardManager.showAllCardInfo(bottomCardList, null)
        val adapter = CardAdapter(bottomCardList, false, ::selectCard, playerNumber)
        when(playerNumber) {
            3 -> ViewUtil.setRecycler(
                bind.rvBottom,
                layoutManager = GridLayoutManager(bind.root.context,2,RecyclerView.HORIZONTAL,false),
                rightSpace = 40,
                topSpace = 20,
                adapter
            )

            4 -> ViewUtil.setRecycler(
                bind.rvBottom,
                layoutManager = GridLayoutManager(bind.root.context,2,RecyclerView.HORIZONTAL,false),
                rightSpace = 100,
                topSpace = 20,
                adapter
            )

            5 -> ViewUtil.setRecycler(
                bind.rvBottom,
                layoutManager = LinearLayoutManager(bind.root.context, RecyclerView.HORIZONTAL,false),
                rightSpace = 0,
                topSpace = 0,
                adapter
            )
        }
        adapterList.add(adapter)
    }

    /*************************************************
     *** 게임
     *************************************************/

    private fun initGame() {
        gameDeck.clear()
        playerList.clear()
        bottomCards.clear()
        adapterList.clear()
        selectPool.clear()
        matchPool.clear()
        playerQueue.clear()
    }

    fun play(playerNumber : Int) {
        initGame()
        gameDeck.addAll(provideDeckForGame(playerNumber))
        playerList.addAll(providePlayerForGame(playerNumber, gameDeck))
        connectAdapter(playerNumber)
        playerQueue = LinkedList(playerList)
        if (checkPlayerQueueNeedEnd(playerQueue, matchPool)) endGame()
    }

    private fun selectCard(card: Card, adapterId : Int) {
        val playerCardMatch = selectCardByPlayer(
            playerQueue,
            card,
            adapterId,
            selectPool,
            matchPool,
            ::endGame
        )
        if (playerCardMatch) {
            updateGameUI(selectPool, adapterList)
        }
    }

    private fun endGame() {
        setGameResult(playerQueue, matchPool)
        moveResult()
    }
}