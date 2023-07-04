package io.softeer.luckycardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.softeer.luckycardgame.card.Card
import io.softeer.luckycardgame.card.CardType

class MainActivity : AppCompatActivity() {

    /**
     * 카드를 담을 리스트
     */
    private val cardList = mutableListOf<Card>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makeCard()

    }

    /**
     * 카드 만들기
     * 1. 카드 생성
     * 2. 카드 섞기
     * 3. 카드 정보 출력
     */
    private fun makeCard() {
        for(type in CardType.values()) {
            for (number in Card.MIN_NUMBER..Card.MAX_NUMBER){
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

}