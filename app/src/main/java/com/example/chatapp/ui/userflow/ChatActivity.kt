package com.example.chatapp.ui.userflow

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.adapter.AllUsersAdapter
import com.example.chatapp.databinding.ActivityChatBinding
import com.example.chatapp.model.ChatMessage
import com.example.chatapp.model.User
import com.example.chatapp.ui.userflow.adapter.MessageAdapter
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChatActivity : AppCompatActivity() {
    private lateinit var binding : ActivityChatBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var chatReference: DatabaseReference
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList: MutableList<ChatMessage>
    private lateinit var chatRoomId: String
    private lateinit var currentUserId: String
    private lateinit var receiverUserId: String
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)


        database = FirebaseDatabase.getInstance()
        currentUserId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
        chatRoomId = intent.getStringExtra("chatRoomId") ?: ""
        receiverUserId = intent.getStringExtra("UserId") ?: ""

        chatReference = database.getReference("chats").child(chatRoomId)


        messageList = mutableListOf()
        messageAdapter = MessageAdapter(messageList)
        binding.rvChatData.adapter = messageAdapter
        binding.rvChatData.layoutManager = LinearLayoutManager(this)
        onClickEvent()
        listenForMessages()

    }
    private fun onClickEvent() {
        binding.imgSend.setOnClickListener {
            val message = binding.edtMsg.text.toString().trim()
            if (message.isNotEmpty()) {
                sendMessage(message)
                binding.edtMsg.text.clear()
            }
        }
    }

    private fun sendMessage(message: String) {
        val chatMessage = ChatMessage(
            message = message,
            senderId = currentUserId,
            receiverId = receiverUserId,
            timestamp = System.currentTimeMillis()
        )

        chatReference.push().setValue(chatMessage)
            .addOnSuccessListener {
                Log.d("ChatActivity", "Message sent successfully")
            }
            .addOnFailureListener { e ->
                Log.e("ChatActivity", "Error sending message", e)
            }
    }
    private fun listenForMessages() {
//        linearLayoutManager= LinearLayoutManager(this,RecyclerView.VERTICAL,true)
//        binding.rvChatData.layoutManager=linearLayoutManager
        chatReference.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val message = snapshot.getValue(ChatMessage::class.java)
                if (message != null) {
                    messageList.add(message)
                    messageAdapter.notifyItemInserted(messageList.size - 1)
                  //  binding.rvChatData.adapter=messageAdapter
                    binding.rvChatData.scrollToPosition(messageList.size - 1)
                }
            }
            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) {}
        })
    }
}