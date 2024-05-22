package com.example.pokedex

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedex.DatabaseHelper
import com.example.pokedex.databinding.ActivityFavoritesBinding

class FavoritesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritesBinding // XML Aqui eu acho! Dei CTRL+CLique nos binding dos outros Activity e eram xmls que tu tinha feito
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.favoriteButton.setOnClickListener {
            val pokemonId = binding.pokemonIdEditText.text.toString()
            if (pokemonId.isNotEmpty()) {
                toggleFavorite(pokemonId)
            } else {
                Toast.makeText(this, "Please enter a Pokémon ID", Toast.LENGTH_SHORT).show()
            }
        }

//        binding.backButton.setOnClickListener {
//            finish()
//        }
    }

    private fun toggleFavorite(pokemonId: String) {
        val isFavorite = databaseHelper.readFavorite(pokemonId)
        if (isFavorite) {
            val deletedRowId = databaseHelper.deleteFavorite(pokemonId)
            if (deletedRowId.toLong() != -1L) {
                Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to remove from favorites", Toast.LENGTH_SHORT).show()
            }
        } else {
            val insertedRowId = databaseHelper.insertFavorite(pokemonId)
            if (insertedRowId != -1L) {
                Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to add to favorites", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
