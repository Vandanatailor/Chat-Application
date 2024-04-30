package com.example.chatapp.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.MainActivity
import com.example.chatapp.databinding.ActivityLoginBinding
import com.example.chatapp.model.ContactDetails
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

class LoginActivity : AppCompatActivity() {

    private lateinit var emailPasswordList: MutableList<Pair<String, String>>
    private lateinit var binding: ActivityLoginBinding
    private lateinit var fireBaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var usemail: String
    private lateinit var usPassword: String
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fireBaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference
        signUp()
        emailPasswordList = mutableListOf()
        readDataFromRealTime()
        onClickEvents()

    }

    private fun onClickEvents() {
        binding.tvSubmit.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()
            Log.d("printHere", "onCreate: " + usPassword + " " + usemail);
            if (isValidaton()) {
                if (emailPasswordList.any { it.first == email && it.second == password }) {
                    Toast.makeText(this, "Login SuccessFully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(
                        this, MainActivity::class.java
                    )
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun readDataFromRealTime() {
        val databaseReference = FirebaseDatabase.getInstance()
            .getReference("SignUp")
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    usemail = snapshot.child("email")
                        .getValue(String::class.java).toString()
                    usPassword = snapshot.child("password")
                        .getValue(String::class.java).toString()
                    emailPasswordList.add(Pair(usemail, usPassword))
                    Log.d("dataUpdation", "onDataChange: " + usemail + " " + usPassword)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d(
                    "DataReadError",
                    "Failed to read data from database: ${databaseError.message}"
                )
            }
        }
        databaseReference.addListenerForSingleValueEvent(valueEventListener)
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
        } else {
            return true
        }
    }


}