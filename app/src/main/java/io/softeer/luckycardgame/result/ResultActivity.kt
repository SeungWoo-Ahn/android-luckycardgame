package io.softeer.luckycardgame.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.softeer.luckycardgame.R
import io.softeer.luckycardgame.adapter.CardAdapter
import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.card.CardType
import io.softeer.luckycardgame.databinding.ActivityResultBinding
import io.softeer.luckycardgame.player.Player
import io.softeer.luckycardgame.util.GameManager
import io.softeer.luckycardgame.util.ViewUtil

class ResultActivity : AppCompatActivity(), OnClickListener {

    private lateinit var bind : ActivityResultBinding
    private val playerList = GameManager.playerList
    private val winnerList = GameManager.findWinner()
    private val winnerName = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityResultBinding.inflate(layoutInflater)
        setContentView(bind.root)
        setUI()
    }

    private fun setUI() {
        setBoards()
        setWinnerText()
        setButton()
    }


    private fun setBoards() {
        ViewUtil.changeBoardView(
            bind.groupFour,
            bind.groupFive,
            null,
            null,
            playerList.size,
            null
        )
        connectAdapter()
        colorWinnerBoards()
    }

    private fun connectAdapter() {
        val recyclerViewList = listOf(bind.rvOne, bind.rvTwo, bind.rvThree, bind.rvFour, bind.rvFive)
        for ((index, player) in playerList.withIndex()) {
            val winCards = makeWinCard(player)
            val adapter = CardAdapter(winCards,true, null, index)
            ViewUtil.setRecycler(
                recyclerViewList[index],
                layoutManager = LinearLayoutManager(bind.root.context, RecyclerView.HORIZONTAL, false),
                rightSpace = ViewUtil.setItemSpace(playerList.size),
                topSpace = 0,
                adapter
            )
        }
    }

    private fun makeWinCard(player: Player) : MutableList<Card> {
        val winCards = mutableListOf<Card>()
        for (number in player.matchList) {
            for (type in CardType.values()) {
                winCards.add(Card(number, type))
            }
        }
        return winCards
    }

    private fun colorWinnerBoards() {
        val boardList = listOf(bind.tvBoardOne, bind.tvBoardTwo, bind.tvBoardThree, bind.tvBoardFour, bind.tvBoardFive)
        for (winId in winnerList) {
            boardList[winId].isEnabled = false
            winnerName.add(('A'+winId).toString())
        }
        winnerName.sort()
    }

    private fun setWinnerText() {
        val winnerNameStr = winnerName.joinToString("와 ")
        bind.tvWinner.text = "이번 게임은 ${winnerNameStr}가 승리했습니다"
    }

    private fun setButton() {
        bind.btnRestart.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        GameManager.resetGameResult()
        intent.putExtra("number", playerList.size)
        setResult(RESULT_OK, intent)
        finish()
    }
}