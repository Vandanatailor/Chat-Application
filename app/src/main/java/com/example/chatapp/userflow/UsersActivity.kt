package com.example.chatapp.userflow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.adapter.AllUsersAdapter
import com.example.chatapp.databinding.ActivityUsersBinding
import com.example.chatapp.model.ContactDetails
import com.example.chatapp.utils.DataItemClickListner
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UsersActivity : AppCompatActivity() ,DataItemClickListner {
    private lateinit var binding: ActivityUsersBinding
    private lateinit var allUsersAdapter: AllUsersAdapter
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseReference = FirebaseDatabase.getInstance().
        getReference().child("SignUp")
        allUserListShow()
    }

    private fun allUserListShow(){
   //     val options: FirebaseRecyclerOptions<ContactDetails>
//        = FirebaseRecyclerOptions.Builder<ContactDetails>()
//            .setQuery(databaseReference, ContactDetails::class.java)
//            .build()
        val options = FirebaseRecyclerOptions.Builder<ContactDetails>()
            .setQuery(databaseReference, ContactDetails::class.java)
            .build()
        Log.d("gfgfgfgfgfg", "allUserListShow: "+options.toString())
        allUsersAdapter = AllUsersAdapter(options, this, this)
        binding.rvUsers.adapter = allUsersAdapter
        binding.rvUsers.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL, false
        )
    }

    override fun onItemClick(position: Int, type: String) {
        TODO("Not yet implemented")
    }
}