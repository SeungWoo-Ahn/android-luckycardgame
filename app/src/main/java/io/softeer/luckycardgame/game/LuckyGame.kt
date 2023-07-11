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
import io.softeer.luckycardgame.util.CardManager.putRemainCards
import io.softeer.luckycardgame.util.PlayerManager.providePlayerForGame
import io.softeer.luckycardgame.util.ViewUtil

class LuckyGame(
    private val bind : ActivityMainBinding
) : MaterialButtonToggleGroup.OnButtonCheckedListener {

    private val deck = mutableListOf<Card>()
    private val playerList = mutableListOf<Player>()
    private val recyclerViewList : List<RecyclerView> =
        listOf(bind.rvA, bind.rvB, bind.rvC, bind.rvD, bind.rvE)
    private val adapterList = mutableListOf<CardAdapter>()

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

    /**
     * 어뎁터와 연결
     */
    private fun connectAdapter(playerNumber: Int) {
        for ((idx,adapter) in adapterList.withIndex()) {
            if (idx == adapterList.size-1) {
                connectBottomAdapter(playerNumber,adapter)
                continue
            }
            ViewUtil.setRecycler(
                recyclerViewList[idx],
                layoutManager = LinearLayoutManager(bind.root.context, RecyclerView.HORIZONTAL, false),
                rightSpace = ViewUtil.setItemSpace(playerNumber),
                topSpace = 0,
                adapter
            )
        }
    }

    private fun connectBottomAdapter(playerNumber: Int, adapter: CardAdapter) {
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
    }

    /*************************************************
     *** 게임
     *************************************************/

    /**
     * 게임 시작하기
     * 1. 게임 초기화
     * 2. 카드 만들기
     * 3. 참가자에게 카드 분배
     * 4. 남은 카드 하단에 두기
     */
    private fun play(playerNumber : Int) {
        initGame()
        deck.provideDeckForGame(playerNumber = playerNumber)
        playerList.providePlayerForGame(playerNumber, deck, ::selectCard, adapterList)
        deck.putRemainCards(playerNumber, ::selectCard, adapterList)
        connectAdapter(playerNumber)
    }

    /**
     * 카드 선택
     */
    private fun selectCard(card: Card, position: Int) {

        if (checkGameEnd()) endGame()
    }

    /**
     * 게임 종료 여부 확인
     */
    private fun checkGameEnd() : Boolean {
        var needEnd = false
        return needEnd
    }

    /**
     * 게임 종료
     */
    private fun endGame() {

    }

    /**
     * 게임 초기화
     */
    private fun initGame() {
        deck.clear()
        playerList.clear()
        adapterList.clear()
    }
}