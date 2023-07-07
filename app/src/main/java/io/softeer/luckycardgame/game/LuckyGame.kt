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
import io.softeer.luckycardgame.util.CardManager.provideDeckForGame
import io.softeer.luckycardgame.util.PlayerManager.providePlayerForGame
import io.softeer.luckycardgame.util.ViewUtil

class LuckyGame(
    private val bind : ActivityMainBinding
) : MaterialButtonToggleGroup.OnButtonCheckedListener {

    private var deck = mutableListOf<Card>()
    private var playerList = mutableListOf<Player>()
    private val recyclerViewList : List<RecyclerView> =
        listOf(bind.rvA, bind.rvB, bind.rvC, bind.rvD, bind.rvE)

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
        playerList.providePlayerForGame(
            playerNumber = playerNumber,
            deck = deck,
            recyclerViewList,
            bind.root.context
        )
        CardManager.putRemainCards(
            deck,
            playerNumber = playerNumber,
            bind.rvBottom,
            bind.root.context
        )
    }

    /**
     * 게임 초기화
     */
    private fun initGame() {
        deck = mutableListOf()
        playerList = mutableListOf()
    }
}