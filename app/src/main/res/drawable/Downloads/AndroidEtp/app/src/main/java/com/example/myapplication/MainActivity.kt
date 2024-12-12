package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.content.Intent


import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.edittext)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener{
            val intent=Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT,editText.text.toString())
            startActivity(Intent.createChooser(intent,"Share Via"))

        }
    }
}