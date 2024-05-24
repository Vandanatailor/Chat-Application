package com.example.chatapp.ui.userflow

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.R
import com.example.chatapp.adapter.AllUsersAdapter
import com.example.chatapp.databinding.ActivityChatBinding
import com.example.chatapp.model.ChatMessage
import com.example.chatapp.model.User
import com.google.firebase.Firebase
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = FirebaseDatabase.getInstance()
        chatReference = database.getReference("chats")
        var UserId = intent.getStringExtra("UserId")
        Log.d("gghhfgjh", "onCreate: "+UserId)

        startRealtimeUpdates()
        onClickEvent()

    }
    private fun onClickEvent(){
        binding.imgSend.setOnClickListener {
            val message = binding.edtMsg.text.toString().trim()
            if (message.isNotEmpty()) {
                val senderId = "123" // Replace with actual sender ID
                sendMessage(message, senderId)
                binding.edtMsg.text.clear()
            }
        }
    }
    private fun sendMessage(message: String, senderId: String) {
        // Create a map representing the chat message
        val chatMessage = mapOf(
            "message" to message,
            "senderId" to senderId,
            "timestamp" to System.currentTimeMillis() // Include a timestamp for sorting
        )

        val messageId = chatReference.push().key
        chatReference.child(messageId ?: "").setValue(chatMessage)
            .addOnSuccessListener {
                Log.d("Yesss", "sendMessage: ")
            }
            .addOnFailureListener { e ->
                // Handle failure: Log the error message
                println("Error sending message: $e")
            }
    }

    private fun startRealtimeUpdates() {
        chatReference.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                // A new message has been added
                val chatMessage = snapshot.getValue(ChatMessage::class.java)
                // Handle the new message
                if (chatMessage != null) {
                    // Update your UI or perform any other actions with the new message
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                // Handle changes to existing messages if needed
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                // Handle message removal if needed
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                // Handle message movement if needed
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database errors if needed
            }
        })
    }


}