package com.example.pokedex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import org.w3c.dom.Text


class MainActivity : AppCompatActivity()
{

    private val handler = Handler(Looper.getMainLooper())
    lateinit var pokeballBG_menu: ImageView
    lateinit var animRotate360: Animation
    lateinit var containerButtonsMainMenu: ImageView
    lateinit var animSlideUp: Animation
    lateinit var animFadeIn: Animation
    lateinit var buttonTypes: Button
    lateinit var buttonLocations: Button
    lateinit var buttonMoves: Button
    lateinit var buttonFavorites: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        initalize()
    }

    private fun initalize() {
        animRotate360 = AnimationUtils.loadAnimation(this,R.anim.rotate_360)
        animRotate360.setInterpolator(this, android.R.anim.linear_interpolator)
        pokeballBG_menu = findViewById(R.id.pokeball_bg_menu)
        pokeballBG_menu.startAnimation(animRotate360)

        animSlideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        animFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        containerButtonsMainMenu = findViewById(R.id.container_buttons_main_menu)
        containerButtonsMainMenu.startAnimation(animSlideUp)


        buttonTypes = findViewById(R.id.button_types)
        buttonLocations = findViewById(R.id.button_locations)
        buttonMoves = findViewById(R.id.button_moves)
        buttonFavorites = findViewById(R.id.button_favorites)
        setupButtonAnimation(buttonTypes, 500)
        setupButtonAnimation(buttonLocations, 600)
        setupButtonAnimation(buttonMoves, 700)
        setupButtonAnimation(buttonFavorites, 800)
        setupButtonClick(buttonTypes,TypesActivity::class.java)
        setupButtonClick(buttonLocations,TypesActivity::class.java)
        setupButtonClick(buttonMoves,TypesActivity::class.java)
        setupButtonClick(buttonFavorites,FavoritesActivity::class.java)


    }
    private fun setupButtonAnimation(button: Button, delay: Long){
        val animSlideUpButton = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        handler.postDelayed({
            button.startAnimation(animSlideUpButton)
            button.visibility = View.VISIBLE
        }, delay)
    }
    private fun setupButtonClick(button: Button, activityClass: Class<*>){
        button.setOnClickListener{
            val intent = Intent(button.context, activityClass)
            button.context.startActivity(intent)
        }
    }
}