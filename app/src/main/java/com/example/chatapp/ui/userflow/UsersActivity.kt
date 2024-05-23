package com.example.chatapp.ui.userflow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.MainActivity
import com.example.chatapp.adapter.AllUsersAdapter
import com.example.chatapp.databinding.ActivityUsersBinding
import com.example.chatapp.extra.onSelectItemClick
import com.example.chatapp.model.ContactDetails
import com.example.chatapp.model.User
import com.example.chatapp.utils.DataItemClickListner
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UsersActivity : AppCompatActivity() ,DataItemClickListner,onSelectItemClick{
    private lateinit var binding: ActivityUsersBinding
    private lateinit var allUsersAdapter: AllUsersAdapter
   /// private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        databaseReference = FirebaseDatabase.getInstance().
//        getReference().child("SignUp")
        allUserListShow()
        listOfUsersCall()
    }

    private fun allUserListShow(){
   //     val options: FirebaseRecyclerOptions<ContactDetails>
//        = FirebaseRecyclerOptions.Builder<ContactDetails>()
//            .setQuery(databaseReference, ContactDetails::class.java)
//            .build()
//        val options = FirebaseRecyclerOptions.Builder<ContactDetails>()
//            .setQuery(databaseReference, ContactDetails::class.java)
//            .build()
//        Log.d("gfgfgfgfgfg", "allUserListShow: "+options)
//        allUsersAdapter = AllUsersAdapter(options, this, this)
//        binding.rvUsers.adapter = allUsersAdapter
//        binding.rvUsers.layoutManager = LinearLayoutManager(
//            this,
//            RecyclerView.VERTICAL, false
//        )
    }

    override fun onItemClick(position: Int, type: String) {
        TODO("Not yet implemented")
    }

    private fun listOfUsersCall(){
      val database = FirebaseDatabase.getInstance()
        val dataList = mutableListOf<User>()

      //  val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = AllUsersAdapter(dataList,this)
        binding.rvUsers.adapter = adapter
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        val databaseReference = database.getReference("users")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (dataSnapshot in snapshot.children) {
                    val data = dataSnapshot.getValue(User::class.java)
                    if (data != null) {
                        dataList.add(data)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("Firebase", "Failed to read value.", error.toException())
            }
        })

    }

    override fun itemClick(position: Int, type: String) {
         if(type=="Users"){
             startActivity(
                 Intent(this@UsersActivity,
                 ChatActivity::class.java)
             )
         }
    }
}