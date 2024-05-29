package com.example.chatapp.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chatapp.MainActivity
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityProfileBinding
import com.example.chatapp.model.User
import com.example.chatapp.utils.CommanFunction
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var mAuth : FirebaseAuth
    private lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference("users")
        onClickHandler()
    }
    private fun onClickHandler(){
        binding.tvSubmit.setOnClickListener{
           val name = binding.tvUserName.text.toString()
           val about= binding.tvAbout.text.toString()
            addProfile(name,about)
        }
    }

        private fun addProfile( userName: String,about : String) {
            val userId = mAuth.currentUser?.uid
            if (userId != null) {
                val user = User(userId, userName,about)
                mDatabase.child(userId).setValue(user)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                           CommanFunction.showToast(this@ProfileActivity,"Successfully changed")
                        } else {
                            Toast.makeText(this, "Failed to save user data",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            }
    }

}