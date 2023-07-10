package io.softeer.luckycardgame.util

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.softeer.luckycardgame.adapter.CardAdapter
import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.card.CardType

object CardManager {

    private val deck = mutableListOf<Card>()
    private var selectCardList = mutableListOf<Card>()

    /**
     * 초기화 : 카드 없으면 만들기
     */
    init {
        if (deck.isEmpty()) makeDeck()
    }

    fun getDeck() = deck

    /**
     * 게임을 위한 덱 제공
     */
    fun MutableList<Card>.provideDeckForGame(playerNumber : Int) {
        selectCardList = mutableListOf()
        addAll(deck.shuffled())
        if (playerNumber == 3) exceptCard(this, cardNumber = 12)
    }

    /**
     * 남은 카드 하단 보드에 두기
     */
    fun MutableList<Card>.putRemainCards(
        playerNumber: Int,
        bottomRecyclerView: RecyclerView,
        context: Context
    ) {
        val remainCardList = this.subList(playerNumber*(11-playerNumber), this.size)
        remainCardList.sorted()
        showAllCardInfo(remainCardList,null)
        when(playerNumber) {
            3 -> ViewUtil.setRecycler(
                bottomRecyclerView,
                layoutManager = GridLayoutManager(context,2,RecyclerView.HORIZONTAL,false),
                rightSpace = 40,
                topSpace = 20,
                adapter =  CardAdapter(remainCardList, false)
            )

            4 -> ViewUtil.setRecycler(
                bottomRecyclerView,
                layoutManager = GridLayoutManager(context,2,RecyclerView.HORIZONTAL,false),
                rightSpace = 100,
                topSpace = 20,
                adapter =  CardAdapter(remainCardList, false)
            )

            5 -> ViewUtil.setRecycler(
                bottomRecyclerView,
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false),
                rightSpace = 0,
                topSpace = 0,
                adapter =  CardAdapter(remainCardList, false)
            )
        }

    }

    /**
     * 카드 만들기
     */
    private fun makeDeck() {
        for (type in CardType.values()) {
            for(number in Card.MIN_NUMBER..Card.MAX_NUMBER) {
                deck.add(Card(number, type))
            }
        }
    }

    /**
     * 덱에서 특정 숫자 제거하기
     */
    private fun exceptCard(deck : MutableList<Card>,cardNumber : Int): Boolean = deck.removeIf { it.getCardNumber() == cardNumber }

    /**
     * 모든 카드 정보 보기
     */
    fun showAllCardInfo(cardList: MutableList<Card>, playerIndex : Int?) {
        val infoList = mutableListOf<String>()
        cardList.forEach {
            infoList.add(it.toString())
        }
        val placeText = if (playerIndex==null) "바닥" else ('A'+playerIndex).toString()
        Log.i(javaClass.name, "$placeText: [${infoList.joinToString(", ")}]")
    }

    /**
     * 카드 선택
     */
    fun selectCard(card : Card) {
        selectCardList.add(card)
        if (selectCardList.size == 3) {
            checkCardsNumberSame(selectCardList)
            selectCardList = mutableListOf()
        }
    }

    /**
     * 카드 숫자가 같은지 체크
     */
    fun checkCardsNumberSame(cardList: MutableList<Card>) : Boolean {
        val counts = cardList.groupingBy { it.getCardNumber() }.eachCount()
        val isSame = counts.any { it.value >= 3 }
        val message = if (isSame) "같습니다" else "다릅니다"
        Log.i(javaClass.name, "세 카드의 숫자가 $message")
        return isSame
    }

}