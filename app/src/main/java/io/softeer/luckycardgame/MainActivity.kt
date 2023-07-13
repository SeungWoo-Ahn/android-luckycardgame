package io.softeer.luckycardgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import io.softeer.luckycardgame.databinding.ActivityMainBinding
import io.softeer.luckycardgame.game.LuckyGame
import io.softeer.luckycardgame.result.ResultActivity

class MainActivity : AppCompatActivity() {

    private lateinit var bind : ActivityMainBinding
    private lateinit var launcher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        val game = LuckyGame(bind, ::moveResult)
        setLauncher(game)
    }

    private fun setLauncher(game: LuckyGame) {
        launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.getIntExtra("number",3)?.let {
                    game.play(it)
                }
            }
        }
    }

    private fun moveResult() {
        val intent = Intent(this, ResultActivity::class.java)
        launcher.launch(intent)
    }
}