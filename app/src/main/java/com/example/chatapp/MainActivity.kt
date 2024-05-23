package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityLoginBinding
import com.example.chatapp.databinding.ActivityMainBinding
import com.example.chatapp.ui.auth.ProfileActivity
import com.example.chatapp.ui.userflow.UsersActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClickEvents()

    }
    private fun onClickEvents(){
        binding.fbButton.setOnClickListener(View.OnClickListener {
            var intent =Intent(this@MainActivity, UsersActivity::class.java)
             startActivity(intent)
        })

        binding.ivCreateProfile.setOnClickListener{
            var intent =Intent(this@MainActivity, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}