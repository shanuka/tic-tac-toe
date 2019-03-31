package com.example.tictac

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    fun onClickStartX(v: View) {
        val  intent: Intent = GameActivity.newIntent(this, 1);
        startActivity(intent);
    }

    fun onClickStartO(v: View) {
        val  intent: Intent = GameActivity.newIntent(this, 0);
        startActivity(intent);
    }



}
