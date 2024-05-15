package com.example.pokedex

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.api.PokemonRepository
import com.example.pokedex.domain.Pokemon
import com.example.pokedex.domain.PokemonType
import com.example.pokedex.viewmodel.PokemonViewModel
import com.example.pokedex.viewmodel.PokemonViewModelFactory
import com.example.pokedex.TypesActivity

class PokemonListActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var buttonReturn: Button

    val viewModel by lazy {
        ViewModelProvider(this, PokemonViewModelFactory())
            .get(PokemonViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvPokemons)
        viewModel.pokemons.observe(this, Observer {
            loadRecyclerView(it)
        })

        buttonReturn = findViewById(R.id.button_type_back)
        buttonReturn.setOnClickListener({
            val intent = Intent(this, TypesActivity::class.java)
            startActivity(intent)
        })
    }

    private fun loadRecyclerView(pokemons: List<Pokemon?>) {
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = PokemonAdapter(pokemons)
    }
}