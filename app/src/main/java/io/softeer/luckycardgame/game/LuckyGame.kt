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
import io.softeer.luckycardgame.util.ViewUtil

class LuckyGame(
    private val bind : ActivityMainBinding
) : MaterialButtonToggleGroup.OnButtonCheckedListener {

    private val gameDeck = mutableListOf<Card>()
    private val playerList = mutableListOf<Player>()
    private val recyclerViewList : List<RecyclerView> =
        listOf(bind.rvA, bind.rvB, bind.rvC, bind.rvD, bind.rvE)
    private val bottomCards = mutableListOf<Card>()

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
            ViewUtil.setRecycler(
                recyclerViewList[index],
                layoutManager = LinearLayoutManager(bind.root.context, RecyclerView.HORIZONTAL, false),
                rightSpace = ViewUtil.setItemSpace(playerNumber),
                topSpace = 0,
                CardAdapter(player.cardList,player.me,::selectCard)
            )
        }
        connectBottomAdapter(playerNumber)
    }

    private fun connectBottomAdapter(playerNumber: Int) {
        val cardCount = 11 - playerNumber
        val bottomCardList = gameDeck.slice(playerNumber*cardCount until  gameDeck.size).toMutableList()
        bottomCardList.sort()
        val adapter = CardAdapter(bottomCardList, false, ::selectCard)
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

    private fun initGame() {
        gameDeck.clear()
        playerList.clear()
        bottomCards.clear()
    }

    private fun play(playerNumber : Int) {
        initGame()
        gameDeck.addAll(provideDeckForGame(playerNumber))
        playerList.addAll(providePlayerForGame(playerNumber, gameDeck))
        connectAdapter(playerNumber)
    }

    private fun provideDeckForGame(playerNumber : Int) : MutableList<Card> {
        val gameDeck = CardManager.deck.shuffled().toMutableList()
        if (playerNumber == 3) exceptCard(gameDeck, cardNumber = 12)
        return  gameDeck
    }

    private fun exceptCard(deck : MutableList<Card>, cardNumber : Int) {
        deck.removeIf { it.getCardNumber() == cardNumber }
    }

    private fun providePlayerForGame(
        playerNumber: Int,
        gameDeck: MutableList<Card>,
    ) : MutableList<Player> {
        val playerList = mutableListOf<Player>()
        val cardCount = 11 - playerNumber
        for (index in 0 until  playerNumber) {
            val player = makePlayer(gameDeck, index, cardCount)
            sortPlayerCards(player)
            playerList.add(player)
        }
        return playerList
    }

    private fun makePlayer(gameDeck: MutableList<Card>, index : Int, cardCount : Int) : Player {
        val eachCards = gameDeck.slice(index*cardCount until  (index+1)*cardCount).toMutableList()
        return Player(eachCards,index)
    }

    private fun sortPlayerCards(player: Player) : List<Card> {
        player.sortCardList()
        return player.cardList
    }

    private fun selectCard() {
        if (checkNeedEnd()) endGame()
    }

    private fun checkNeedEnd() : Boolean {
        return false
    }

    private fun endGame() {

    }
}