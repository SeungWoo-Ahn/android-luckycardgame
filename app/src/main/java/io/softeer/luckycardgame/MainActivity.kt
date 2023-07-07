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
import io.softeer.luckycardgame.game.LuckyGame
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

        LuckyGame(bind)
    }
}