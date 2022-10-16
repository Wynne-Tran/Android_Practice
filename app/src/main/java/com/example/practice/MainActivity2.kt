package com.example.practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val message = intent.getStringExtra("EXTRA_MESSAGE")
        val textView = findViewById<TextView>(R.id.result).apply {
            text = message
        }

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{goBackMainActivity()}
    }

    private fun goBackMainActivity() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}