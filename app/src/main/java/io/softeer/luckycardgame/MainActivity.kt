package io.softeer.luckycardgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import io.softeer.luckycardgame.databinding.ActivityMainBinding
import io.softeer.luckycardgame.game.LuckyGame
import io.softeer.luckycardgame.result.ResultActivity

class MainActivity : AppCompatActivity() {

    private lateinit var bind : ActivityMainBinding
    private lateinit var launcher : ActivityResultLauncher<Intent>
    private var game : LuckyGame? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        game = LuckyGame(bind, ::moveResult)
        setLauncher()
    }

    private fun setLauncher() {
        launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.getIntExtra("number",3)?.let {
                    game = LuckyGame(bind, ::moveResult)
                    game?.startGame(it)
                }
            }
        }
    }

    private fun moveResult() {
        val intent = Intent(this, ResultActivity::class.java)
        launcher.launch(intent)
    }
}