package com.example.chatapp.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.MainActivity
import com.example.chatapp.databinding.ActivityLoginBinding
import com.example.chatapp.model.ContactDetails
import com.example.chatapp.model.User
import com.example.chatapp.utils.AppSession
import com.example.chatapp.utils.CommanFunction
import com.example.chatapp.utils.CommonMethods
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
class LoginActivity : AppCompatActivity() {

    private lateinit var emailPasswordList: MutableList<Pair<String, String>>
    private lateinit var binding: ActivityLoginBinding
    private lateinit var fireBaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fireBaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference
        signUp()
        emailPasswordList = mutableListOf()
        onClickEvents()

    }
    private fun onClickEvents() {
        binding.tvSubmit.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()
            if (isValidaton()) {
                fireBaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }

    private fun signUp() {
        binding.clickForSignIn.setOnClickListener(View.OnClickListener {
            intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        })
    }
    private fun isValidaton(): Boolean {
         if (binding.emailEt.text.toString().isEmpty()) {
            CommanFunction.showToast(this, "Please Enter Your Email")
            return false
        } else if (!CommonMethods.emailValidation(binding.emailEt.text.toString())
        ) {
            CommanFunction.showToast(this, "Please Enter Valid email")
            return false
        } else if (binding.passwordEt.text.toString().isEmpty()) {
            CommanFunction.showToast(this, "Please Enter Password")
            return false
        }
        else {
            return true
        }
    }

}