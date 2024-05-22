package com.example.pokedex

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.domain.Pokemon

class PokemonAdapter(
    private val items: List<Pokemon?>
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindView(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Pokemon?) = with(itemView){
            val ivPokemon = findViewById<ImageView>(R.id.ivPokemon)
            val tvNumber = findViewById<TextView>(R.id.tvNumber)
            val tvName = findViewById<TextView>(R.id.tvName)

            item?.let {
                Glide.with(itemView.context).load(it.imageUrl).into(ivPokemon)

                tvNumber.text = "#${item.formattedNumber}"
                tvName.text = item.formattedName
                setOnClickListener {
                    Toast.makeText(
                        context,
                        "Item clicado: ${item.formattedName}",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent(itemView.context, PokemonDetailsActivity::class.java).apply {
                        putExtra("POKEMON_NAME", item.formattedName)
                        putExtra("POKEMON_NUMBER", "#${item.formattedNumber}")
                        putExtra("POKEMON_SPRITE", item.imageUrl)
                        putExtra("POKEMON_HEIGHT", item.formattedHeight)
                        putExtra("POKEMON_WEIGHT", item.formattedWeight)
                        putStringArrayListExtra("POKEMON_TYPES", ArrayList(item.types.map { it.name }))
                        putStringArrayListExtra("POKEMON_STATS", ArrayList(item.stats.map { stat -> "${stat.base_stat}" }))
                    }

                    itemView.context.startActivity(intent)
                }
            }

        }
    }

}