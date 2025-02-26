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
import io.softeer.luckycardgame.util.GameManager
import io.softeer.luckycardgame.util.PlayerManager
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
            R.id.button1 -> startGame(3)
            R.id.button2 -> startGame(4)
            R.id.button3 -> startGame(5)
        }
    }

    fun startGame(playerNumber: Int) {
        ViewUtil.changeBoardView(
            bind.groupBoardD,
            bind.groupBoardE,
            bind.tvBoardBottom,
            bind.rvBottom,
            playerNumber,
            ::play
        )
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

    private fun play(playerNumber : Int) {
        initGame()
        gameDeck.addAll(CardManager.provideDeckForGame(playerNumber))
        playerList.addAll(PlayerManager.providePlayerForGame(playerNumber, gameDeck))
        if (PlayerManager.checkPlayerListNeedEnd(playerList, matchPool)) endGame()
        playerQueue = LinkedList(playerList)
        connectAdapter(playerNumber)
    }

    private fun selectCard(card: Card, adapterId : Int) {
        val playerCardMatch = GameManager.selectCardByPlayer(
            playerQueue,
            card,
            adapterId,
            selectPool,
            matchPool,
            ::endGame
        )
        if (playerCardMatch) {
            GameManager.updateGameUI(selectPool, adapterList)
        }
    }

    private fun endGame() {
        GameManager.setGameResult(playerQueue, matchPool)
        moveResult()
    }
}