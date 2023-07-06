package io.softeer.luckycardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.softeer.luckycardgame.adapter.CardAdapter
import io.softeer.luckycardgame.adapter.RecyclerItemDecoration
import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.card.CardType
import io.softeer.luckycardgame.databinding.ActivityMainBinding
import io.softeer.luckycardgame.player.Player

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

        recyclerViewList = listOf(bind.rvA , bind.rvB, bind.rvC, bind.rvD, bind.rvE)
        manageButton()
    }

    /**
     * 버튼을 제어
     */
    private fun manageButton() {

        bind.toggleButton.addOnButtonCheckedListener { button, checkId, isChecked ->

            when(button.checkedButtonId) {
                R.id.button1 -> {
                    bind.clBoard.setHeight(240f)
                    bind.clGrayD.visibility = View.GONE
                    bind.clGrayE.visibility = View.GONE
                    playCardGame(3)
                }
                R.id.button2 -> {
                    bind.clBoard.setHeight(240f)
                    bind.clGrayD.visibility = View.VISIBLE
                    bind.clGrayE.visibility = View.GONE
                    playCardGame(4)
                }
                R.id.button3 -> {
                    bind.clBoard.setHeight(120f)
                    bind.clGrayD.visibility = View.VISIBLE
                    bind.clGrayE.visibility = View.VISIBLE
                    playCardGame(5)
                }
            }

        }

    }

    /**
     * 게임 시작하기
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
        for (num in 1..playerNumber) {
            val player = if (num == 1) Player(cardList.subList(0,cardCount),true) else Player(cardList.subList((num-1)*cardCount,num*cardCount),false)
            playerList.add(player)
        }
        makeBoard(cardList.subList(playerNumber*cardCount,cardList.size), playerNumber)
        matchAdapter()
    }

    /**
     * 어댑터 연결하기
     */
    private fun matchAdapter() {

        val playerCount = playerList.size
        var itemSpace = 0
        if (playerCount == 3) itemSpace = -42
        if (playerCount == 4) itemSpace = -24

        for (index in 0 until playerCount) {
            recyclerViewList[index].let {
                if (it.itemDecorationCount != 0) it.removeItemDecoration(it.getItemDecorationAt(0))
                it.addItemDecoration(RecyclerItemDecoration(itemSpace,0))
                it.adapter = playerList[index].adapterByPlayer()
            }
            recyclerViewList[index].adapter = playerList[index].adapterByPlayer()
        }
    }

    /**
     * 하단 보드 만들기
     */
    private fun makeBoard(cardList: MutableList<Card>, playerNumber: Int) {

        when(playerNumber) {
            3 -> {
                bind.rvBoard.let {
                    it.layoutManager = GridLayoutManager(this,2, RecyclerView.HORIZONTAL,false)
                    if(it.itemDecorationCount != 0) it.removeItemDecoration(it.getItemDecorationAt(0))
                    it.addItemDecoration(RecyclerItemDecoration(40,20))
                }
            }
            4 -> {
                bind.rvBoard.let {
                    it.layoutManager = GridLayoutManager(this,2, RecyclerView.HORIZONTAL,false)
                    if(it.itemDecorationCount != 0) it.removeItemDecoration(it.getItemDecorationAt(0))
                    it.addItemDecoration(RecyclerItemDecoration(100,20))
                }
            }
            5 -> {
                bind.rvBoard.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false)
            }
        }
        bind.rvBoard.adapter = CardAdapter(cardList, false)
    }

    /**
     * 뷰 크기를 설정
     */
    private fun View.setHeight(value: Float) {
        val lp = layoutParams
        lp?.let {
            lp.height =  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,resources.displayMetrics).toInt()
            layoutParams = lp
        }
    }

}