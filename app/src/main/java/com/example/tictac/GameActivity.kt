package com.example.tictac

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tictac.model.Board
import com.example.tictac.view.BoardView


class GameActivity : AppCompatActivity() {

    lateinit var boardView: BoardView
    var board: Board? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)
        boardView = findViewById(R.id.board)

        val player = intent.getIntExtra(INTENT_PLAYER, 0)

        board = Board(player = player)
        board!!.newGame()
        boardView.setGameEngine(board)
        boardView.setMainActivity(this)
        getCurrentPlayer(board!!.getCurrentPlayer())
    }

    fun gameEnded(c: Char) {
        val msg = if (c == 'T') "A draw" else "$c wins"

        AlertDialog.Builder(this).setMessage(msg)
            .setPositiveButton("OK") { dialog, which ->
                finish()
            }.setOnDismissListener {
                finish()
            }.setCancelable(false).show()
    }

    @SuppressLint("SetTextI18n")
    fun getCurrentPlayer(c: Char) {
        findViewById<TextView>(R.id.textView).text = "${c.toUpperCase()} 's Turn"
    }


    companion object {
        private val INTENT_PLAYER = "player"
        fun newIntent(context: Context, player: Int): Intent {
            val intent = Intent(context, GameActivity::class.java)
            intent.putExtra(INTENT_PLAYER, player)
            return intent
        }
    }

}
