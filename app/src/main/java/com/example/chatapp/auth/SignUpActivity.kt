package com.example.chatapp.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chatapp.BaseActivity
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivitySignUpBinding
import com.example.chatapp.model.ContactDetails
import com.example.chatapp.utils.CommanFunction
import com.example.chatapp.utils.CommonMethods
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    private lateinit var contactDetails: ContactDetails
    private lateinit var fireBaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this);
        fireBaseAuth = FirebaseAuth.getInstance();
        onClickEvents()

    }

    private fun onClickEvents() {

        binding.tvSubmit.setOnClickListener(
            View.OnClickListener {
                if (isValidaton()) {
                    singUpWithRealTime(
                        binding.usernameEt.text.toString(),
                        binding.emailEt.text.toString(),
                        binding.passwordEt.text.toString()
                    )
                }

            }
        )
    }

    private fun singUpWithRealTime(name: String, email: String, password: String) {

        contactDetails = ContactDetails(name, email, password);
        FirebaseDatabase.getInstance().getReference()
            .child("SignUp")
            .push().setValue(contactDetails)
            .addOnSuccessListener {
                intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
                Log.d("Successs", "singUpWithRealTime: ")

            }.addOnFailureListener { exception ->
                Log.d("failureFailure", "singUpWithRealTime: " + exception)
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
    }

    private fun isValidaton(): Boolean {
        if (binding.usernameEt.text.toString().isEmpty() && binding.usernameEt.text == null) {
            CommanFunction.showToast(this,"Please Enter Your Name")
            return false
        } else if (binding.emailEt.text.toString().isEmpty()
        ) {
            CommanFunction.showToast(this,"Please Enter Your Email")
            return false
        } else if (!CommonMethods.emailValidation(binding.emailEt.text.toString())
        ) {
            CommanFunction.showToast(this,"Please Enter Valid email")
            return false
        } else if (binding.passwordEt.text.toString().isEmpty()) {
            CommanFunction.showToast(this,"Please Enter Password")
            return false
        }
        else{
            return true
        }
    }

}