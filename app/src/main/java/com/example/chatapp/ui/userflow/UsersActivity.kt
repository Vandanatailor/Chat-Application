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
import com.example.chatapp.model.ChatRoom
import com.example.chatapp.model.ContactDetails
import com.example.chatapp.model.User
import com.example.chatapp.utils.DataItemClickListner
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UsersActivity : AppCompatActivity() ,DataItemClickListner,onSelectItemClick{
    private lateinit var binding: ActivityUsersBinding
    private lateinit var allUsersAdapter: AllUsersAdapter
    private lateinit var currentUser: User
    private lateinit var database : FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
         database = FirebaseDatabase.getInstance()

        currentUser=FirebaseAuth.getInstance().currentUser?.let {
            User(it.uid,it.displayName ?:" ")
        }?:return

        listOfUsersCall()
    }


    override fun onItemClick(position: Int, type: String) {
        TODO("Not yet implemented")
    }
    private fun listOfUsersCall(){

        val dataList = mutableListOf<User>()
         allUsersAdapter = AllUsersAdapter(dataList,this)
        binding.rvUsers.adapter = allUsersAdapter
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
                allUsersAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("Firebase", "Failed to read value.", error.toException())
            }
        })
    }

    override fun itemClick(position: Int, uid: String) {
        val chatRoomId = createChatRoomId(currentUser.uid, uid)
             startActivity(
                 Intent(this@UsersActivity,
                 ChatActivity::class.java)
                     .putExtra("chatRoomId", chatRoomId)
                     .putExtra("UserId",uid))
    }

    private fun createChatRoomId(user1: String, user2: String): String {
        val sortedIds = listOf(user1, user2).sorted()
        return sortedIds.joinToString("_")
    }
}