package io.softeer.luckycardgame.game

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButtonToggleGroup
import io.softeer.luckycardgame.R
import io.softeer.luckycardgame.adapter.CardAdapter
import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.card.CardType
import io.softeer.luckycardgame.databinding.ActivityMainBinding
import io.softeer.luckycardgame.player.Player
import io.softeer.luckycardgame.util.CardManager
import io.softeer.luckycardgame.util.ViewUtil

class LuckyGame(private val bind : ActivityMainBinding) : MaterialButtonToggleGroup.OnButtonCheckedListener {

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

    /*************************************************
     *** 게임
     *************************************************/

    private var deck = mutableListOf<Card>()
    private var playerList = mutableListOf<Player>()
    private val recyclerViewList : List<RecyclerView> =
        listOf(bind.rvA, bind.rvB, bind.rvC, bind.rvD, bind.rvE)

    /**
     * 게임 시작하기
     */
    private fun play(playerNumber : Int) {
        getDeck(playerNumber)
        makePlayer(playerNumber, 11-playerNumber)
    }

    /**
     * 카드 가져오기
     */
    private fun getDeck(playerNumber: Int)  {
        deck = CardManager.getDeck(playerNumber)
        Log.i(javaClass.name, CardManager.showAllCardInfo())
    }


    /**
     * 플레이어 참가
     */
    private fun makePlayer(playerNumber: Int, cardCount : Int) {
        playerList = mutableListOf()
        for (number in 0 until playerNumber)
            playerList.add(Player(deck, number, cardCount))
        matchAdapter()
        putRemainCards(deck.subList(playerNumber*cardCount,deck.size), playerNumber)
    }

    /**
     * 어댑터 연결
     */
    private fun matchAdapter() {
        for (index in 0 until playerList.size) {
            ViewUtil.setRecycler(
                recyclerViewList[index],
                layoutManager = LinearLayoutManager(bind.root.context, RecyclerView.HORIZONTAL,false),
                rightSpace = ViewUtil.setItemSpace(playerList.size),
                topSpace = 0,
                adapter =  playerList[index].adapterByPlayer()
            )
        }
    }

    /**
     * 하단 보드에 남은 카드 두기
     */
    private fun putRemainCards(cardList: MutableList<Card>, playerNumber: Int) {
        when(playerNumber) {
            3 -> ViewUtil.setRecycler(
                bind.rvBottom,
                layoutManager = GridLayoutManager(bind.root.context,2,RecyclerView.HORIZONTAL,false),
                rightSpace = 40,
                topSpace = 20,
                adapter =  CardAdapter(cardList, false)
            )

            4 -> ViewUtil.setRecycler(
                bind.rvBottom,
                layoutManager = GridLayoutManager(bind.root.context,2,RecyclerView.HORIZONTAL,false),
                rightSpace = 100,
                topSpace = 20,
                adapter =  CardAdapter(cardList, false)
            )

            5 -> ViewUtil.setRecycler(
                bind.rvBottom,
                layoutManager = LinearLayoutManager(bind.root.context, RecyclerView.HORIZONTAL,false),
                rightSpace = 0,
                topSpace = 0,
                adapter =  CardAdapter(cardList, false)
            )
        }
    }



}