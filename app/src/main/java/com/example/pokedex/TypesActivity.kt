package com.example.pokedex

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TypesActivity : AppCompatActivity() {

    lateinit var buttonBack: Button
    lateinit var buttonAll: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_types)
        initialize()
    }

    private fun initialize() {
        buttonBack = findViewById(R.id.button_types_back)
        buttonBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        buttonAll = findViewById(R.id.poke_all)
        setupButtonClick(buttonAll)
    }

    fun setupButtonClick(button: Button){
        button.setOnClickListener({
            val intent = Intent(button.context, PokemonListActivity::class.java)
            startActivity(intent)
        })
    }
}