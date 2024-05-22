package com.example.pokedex

import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

class PokemonDetailsActivity : AppCompatActivity() {

    lateinit var viewBackground: View

    lateinit var detailName: TextView
    lateinit var detailImage: ImageView
    lateinit var detailTypesContainer: LinearLayout
    lateinit var detailStatsHP: TextView
    lateinit var detailStatsATK: TextView
    lateinit var detailStatsDEF: TextView
    lateinit var detailStatsSATK: TextView
    lateinit var detailStatsSDEF: TextView
    lateinit var detailStatsSPD: TextView
    lateinit var progressBarHP: ProgressBar
    lateinit var progressBarATK: ProgressBar
    lateinit var progressBarDEF: ProgressBar
    lateinit var progressBarSATK: ProgressBar
    lateinit var progressBarSDEF: ProgressBar
    lateinit var progressBarSPD: ProgressBar

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)

        initialize()

        detailName.text = "${intent.getStringExtra("POKEMON_NAME")} ${intent.getStringExtra("POKEMON_NUMBER")}"

        //detailNumber.text = intent.getStringExtra("POKEMON_NUMBER")
        Glide.with(this).load(intent.getStringExtra("POKEMON_SPRITE")).into(detailImage)
        val types = intent.getStringArrayListExtra("POKEMON_TYPES") ?: arrayListOf()
        val inflater = LayoutInflater.from(this)
        val typeColorMap = mapOf(
            "bug" to R.drawable.gradient_type_bug_linear,
            "dark" to R.drawable.gradient_type_dark_linear,
            "dragon" to R.drawable.gradient_type_dragon_linear,
            "electric" to R.drawable.gradient_type_eletric_linear,
            "fairy" to R.drawable.gradient_type_fairy_linear,
            "fighting" to R.drawable.gradient_type_fighting_linear,
            "fire" to R.drawable.gradient_type_fire_linear,
            "flying" to R.drawable.gradient_type_flying_linear,
            "ghost" to R.drawable.gradient_type_ghost_linear,
            "grass" to R.drawable.gradient_type_grass_linear,
            "ground" to R.drawable.gradient_type_ground_linear,
            "ice" to R.drawable.gradient_type_ice_linear,
            "normal" to R.drawable.gradient_type_normal_linear,
            "poison" to R.drawable.gradient_type_poison_linear,
            "psychic" to R.drawable.gradient_type_psychic_linear,
            "rock" to R.drawable.gradient_type_rock_linear,
            "steel" to R.drawable.gradient_type_steel_linear,
            "water" to R.drawable.gradient_type_water_linear
        )
        for (type in types) {
            val typeTextView =  inflater.inflate(R.layout.type_text_view, detailTypesContainer, false) as TextView
            typeTextView.text = type.capitalize()
            typeTextView.setBackgroundResource(typeColorMap[type] ?: R.drawable.gradient_type_bug_linear)
            detailTypesContainer.addView(typeTextView)
        }
        val stats = intent.getStringArrayListExtra("POKEMON_STATS")
        if (stats != null) {
            detailStatsHP.text = stats.getOrNull(0) ?: "N/A"
            detailStatsATK.text = stats.getOrNull(1) ?: "N/A"
            detailStatsDEF.text = stats.getOrNull(2) ?: "N/A"
            detailStatsSATK.text = stats.getOrNull(3) ?: "N/A"
            detailStatsSDEF.text = stats.getOrNull(4) ?: "N/A"
            detailStatsSPD.text = stats.getOrNull(5) ?: "N/A"

            val hp = stats.getOrNull(0)?.toIntOrNull() ?: 0
            val atk = stats.getOrNull(1)?.toIntOrNull() ?: 0
            val def = stats.getOrNull(2)?.toIntOrNull() ?: 0
            val satk = stats.getOrNull(3)?.toIntOrNull() ?: 0
            val sdef = stats.getOrNull(4)?.toIntOrNull() ?: 0
            val spd = stats.getOrNull(5)?.toIntOrNull() ?: 0
            progressBarHP.progress = if (hp > 100) 100 else hp
            progressBarATK.progress = if (atk > 100) 100 else atk
            progressBarDEF.progress = if (def > 100) 100 else def
            progressBarSATK.progress = if (satk > 100) 100 else satk
            progressBarSDEF.progress = if (sdef > 100) 100 else sdef
            progressBarSPD.progress = if (spd > 100) 100 else spd
        }

        viewBackground.background = when (types[0]){
            "bug" -> ContextCompat.getDrawable(this, R.drawable.gradient_type_bug)
            "dark" -> ContextCompat.getDrawable(this, R.drawable.gradient_type_dark)
            "dragon" -> ContextCompat.getDrawable(this, R.drawable.gradient_type_dragon)
            "electric" -> ContextCompat.getDrawable(this, R.drawable.gradient_type_eletric)
            "fairy" -> ContextCompat.getDrawable(this, R.drawable.gradient_type_fairy)
            "fighting" -> ContextCompat.getDrawable(this, R.drawable.gradient_type_fighting)
            "fire" -> ContextCompat.getDrawable(this, R.drawable.gradient_type_fire)
            "flying" -> ContextCompat.getDrawable(this, R.drawable.gradient_type_flying)
            "ghost" -> ContextCompat.getDrawable(this, R.drawable.gradient_type_ghost)
            "grass" -> ContextCompat.getDrawable(this, R.drawable.gradient_type_grass)
            "ground" -> ContextCompat.getDrawable(this, R.drawable.gradient_type_ground)
            "ice" -> ContextCompat.getDrawable(this, R.drawable.gradient_type_ice)
            "normal" -> ContextCompat.getDrawable(this, R.drawable.gradient_type_normal)
            "poison" -> ContextCompat.getDrawable(this, R.drawable.gradient_type_poison)
            "psychic" -> ContextCompat.getDrawable(this, R.drawable.gradient_type_psychic)
            "rock" -> ContextCompat.getDrawable(this, R.drawable.gradient_type_rock)
            "steel" -> ContextCompat.getDrawable(this, R.drawable.gradient_type_steel)
            "water" -> ContextCompat.getDrawable(this, R.drawable.gradient_type_water)
            else -> ContextCompat.getDrawable(this, R.color.poke_red)
        }

    }

    private fun initialize() {
        viewBackground = findViewById(R.id.detail_view)
        detailName = findViewById(R.id.detailPokeName)
        detailImage = findViewById(R.id.detail_poke_image)
        detailTypesContainer = findViewById(R.id.detail_types_container)
        detailStatsHP = findViewById(R.id.detailPokeStatsHP)
        detailStatsATK = findViewById(R.id.detailPokeStatsATK)
        detailStatsDEF = findViewById(R.id.detailPokeStatsDEF)
        detailStatsSATK = findViewById(R.id.detailPokeStatsSATK)
        detailStatsSDEF = findViewById(R.id.detailPokeStatsSDEF)
        detailStatsSPD = findViewById(R.id.detailPokeStatsSPD)
        progressBarHP = findViewById(R.id.progressBarHP)
        progressBarATK = findViewById(R.id.progressBarATK)
        progressBarDEF = findViewById(R.id.progressBarDEF)
        progressBarSATK = findViewById(R.id.progressBarSATK)
        progressBarSDEF = findViewById(R.id.progressBarSDEF)
        progressBarSPD = findViewById(R.id.progressBarSPD)
    }
}