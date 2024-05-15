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

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var databaseHelper: DatabaseHelper
    lateinit var animRotate360: Animation
    lateinit var pokeballBG: ImageView

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.signupButton.setOnClickListener {
            val signupUsername = binding.signupUsername.text.toString()
            val signupPassword = binding.signupPassword.text.toString()
            val signupPasswordConfirmation = binding.signupPasswordConfirmation.text.toString()
            if(signupPassword == signupPasswordConfirmation){
                signupDatabase(signupUsername, signupPassword)
            } else {
                Toast.makeText(this, "Signup Failed", Toast.LENGTH_SHORT).show()
            }

        }

        binding.loginRedirect.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java )
            startActivity(intent)
            finish()
        }
        animRotate360 = AnimationUtils.loadAnimation(this,R.anim.rotate_360)
        animRotate360.setInterpolator(this, android.R.anim.linear_interpolator)
        pokeballBG = findViewById(R.id.pokeballBackground)
        pokeballBG.setRenderEffect(RenderEffect.createBlurEffect(10F,10F, Shader.TileMode.MIRROR))
        pokeballBG.startAnimation(animRotate360)
    }

    private fun signupDatabase(username: String, password: String){
        val insertedRowId = databaseHelper.insertUser(username, password)

        if(insertedRowId != -1L){
            Toast.makeText(this, "Signup Sucessful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,LoginActivity::class.java )
            startActivity(intent)
            finish()
        }   else{
            Toast.makeText(this, "Signup Failed", Toast.LENGTH_SHORT).show()
        }
    }
}