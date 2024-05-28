package com.example.chatapp.model
data class ContactDetails (
    var name : String ="",
    var email : String ="",
    var password : String=""
)
data class User(
    var uid : String="",
    var userName : String ="",
    var email : String=""
)
data class ChatMessage(
    val message: String = "",
    val senderId: String = "",
    val receiverId: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
data class ChatRoom(
    val chatRoomId: String = "",
    val users: List<String> = listOf()
)
