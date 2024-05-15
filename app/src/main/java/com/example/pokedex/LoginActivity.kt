package com.example.pokedex

import android.content.Intent
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pokedex.databinding.ActivityLoginBinding
import com.example.pokedex.databinding.ActivitySignupBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var databaseHelper: DatabaseHelper
    lateinit var animRotate360: Animation
    lateinit var pokeballBG: ImageView

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.loginButton.setOnClickListener {
        val loginUsername = binding.loginUsername.text.toString()
        val loginPassword = binding.loginPassword.text.toString()
        loginDatabase(loginUsername, loginPassword)
        }
        binding.signRedirect.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
        animRotate360 = AnimationUtils.loadAnimation(this,R.anim.rotate_360)
        animRotate360.setInterpolator(this, android.R.anim.linear_interpolator)
        pokeballBG = findViewById(R.id.pokeballBackground)
        pokeballBG.setRenderEffect(RenderEffect.createBlurEffect(10F,10F, Shader.TileMode.MIRROR))
        pokeballBG.startAnimation(animRotate360)
    }

    private fun loginDatabase(username: String, password: String){
        val userExists = databaseHelper.readUser(username, password)
        if (userExists){
            Toast.makeText(this, "Login Sucessful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()

        }
    }
}