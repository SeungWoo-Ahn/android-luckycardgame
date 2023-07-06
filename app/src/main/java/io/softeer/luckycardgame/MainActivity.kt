package io.softeer.luckycardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.softeer.luckycardgame.adapter.CardAdapter
import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.card.CardType
import io.softeer.luckycardgame.databinding.ActivityMainBinding
import io.softeer.luckycardgame.player.Player
import io.softeer.luckycardgame.util.ViewUtil

class MainActivity : AppCompatActivity() {

    /**
     * 카드를 담을 리스트
     */
    private var cardList = mutableListOf<Card>()
    private lateinit var bind : ActivityMainBinding
    private lateinit var recyclerViewList : List<RecyclerView>
    private lateinit var playerList : MutableList<Player>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        setUI()
    }

    private fun setUI() {
        recyclerViewList = listOf(bind.rvA , bind.rvB, bind.rvC, bind.rvD, bind.rvE)
        manageButton()
    }

    /**
     * 버튼을 제어
     */
    private fun manageButton() {

        bind.toggleButton.addOnButtonCheckedListener { button, checkId, isChecked ->

            when(button.checkedButtonId) {
                R.id.button1 -> ViewUtil.changeBoardView(
                    bind.clGrayD,
                    bind.clGrayE,
                    bind.clBoard,
                    3,
                    ::playCardGame
                )

                R.id.button2 -> ViewUtil.changeBoardView(
                    bind.clGrayD,
                    bind.clGrayE,
                    bind.clBoard,
                    4,
                    ::playCardGame
                )

                R.id.button3 -> ViewUtil.changeBoardView(
                    bind.clGrayD,
                    bind.clGrayE,
                    bind.clBoard,
                    5,
                    ::playCardGame
                )
            }

        }

    }

    /**
     * 게임 시작하기
     * 1. 카드 만들기 ~ 섞기
     * 2. 플레이어 초기화
     * 3. 플레이어 카드 배분
     * 4. 하단 보드 카드 배분
     */
    private fun playCardGame(playerNumber: Int) {
        makeCard(playerNumber)
        initiateGame(playerNumber)
    }

    /**
     * 카드 만들기
     * 1. 카드 생성
     * 2. 카드 섞기
     * 3. 카드 정보 출력
     */
    private fun makeCard(playerNumber : Int) {
        cardList = mutableListOf()
        val cardMaxNumber = if (playerNumber==3) 11 else 12
        for(type in CardType.values()) {
            for (number in Card.MIN_NUMBER..cardMaxNumber){
                cardList.add(Card(number, type))
            }
        }
        cardList.shuffle()
        Log.i(javaClass.name, concatAllCardInfo(cardList))
    }

    /**
     * 카드 정보 합치기
     */
    private fun concatAllCardInfo(cardList : MutableList<Card>) : String {
        val infoList = mutableListOf<String>()
        cardList.forEach {
            infoList.add(it.cardInfo())
        }
        return infoList.joinToString(", ")
    }

    /**
     * 게임 초기화
     */
    private fun initiateGame(playerNumber: Int) {
        playerList = mutableListOf()
        makePlayer(playerNumber, 11-playerNumber)
    }

    /**
     * 플레이어 만들기
     */
    private fun makePlayer(playerNumber: Int, cardCount : Int) {
        for (num in 1..playerNumber)
            playerList.add(Player(cardList.subList((num-1)*cardCount,num*cardCount),false))
        playerList[0].changeToMyCard()
        matchAdapter()
        makeBoard(cardList.subList(playerNumber*cardCount,cardList.size), playerNumber)

    }

    /**
     * 어댑터 연결하기
     */
    private fun matchAdapter() {

        for (index in 0 until playerList.size) {
            ViewUtil.setRecycler(
                recyclerViewList[index],
                layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false),
                rightSpace = ViewUtil.setItemSpace(playerList.size),
                topSpace = 0,
                adapter =  playerList[index].adapterByPlayer()
            )
        }
    }

    /**
     * 하단 보드 만들기
     */
    private fun makeBoard(cardList: MutableList<Card>, playerNumber: Int) {

        when(playerNumber) {
            3 -> ViewUtil.setRecycler(
                    bind.rvBoard,
                    layoutManager = GridLayoutManager(this,2,RecyclerView.HORIZONTAL,false),
                    rightSpace = 40,
                    topSpace = 20,
                    adapter =  CardAdapter(cardList, false)
                )
            4 -> ViewUtil.setRecycler(
                    bind.rvBoard,
                    layoutManager = GridLayoutManager(this,2,RecyclerView.HORIZONTAL,false),
                    rightSpace = 100,
                    topSpace = 20,
                    adapter =  CardAdapter(cardList, false)
                )
            5 -> ViewUtil.setRecycler(
                    bind.rvBoard,
                    layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false),
                    rightSpace = 0,
                    topSpace = 0,
                    adapter =  CardAdapter(cardList, false)
                )
            }
    }

}