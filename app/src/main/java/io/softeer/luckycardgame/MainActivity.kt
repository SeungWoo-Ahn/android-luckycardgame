package io.softeer.luckycardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.softeer.luckycardgame.databinding.ActivityMainBinding
import io.softeer.luckycardgame.game.LuckyGame

class MainActivity : AppCompatActivity() {

    private lateinit var bind : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        LuckyGame(bind)
    }
}