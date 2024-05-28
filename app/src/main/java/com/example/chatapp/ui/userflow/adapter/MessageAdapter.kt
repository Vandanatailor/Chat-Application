package com.example.chatapp.ui.userflow.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.model.ChatMessage
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(private val messageList: List<ChatMessage>) :
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    private val currentUserId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chatlist_left, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messageList[position]
        holder.bind(message)
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvChatLeft: TextView = itemView.findViewById(R.id.tv_chat_left)
        private val tvChatRight: TextView = itemView.findViewById(R.id.tv_chat_right)

        fun bind(message: ChatMessage) {
            if (message.senderId == currentUserId) {
                tvChatRight.text = message.message
                tvChatRight.visibility = View.VISIBLE
                tvChatLeft.visibility = View.GONE
            } else {
                tvChatLeft.text = message.message
                tvChatLeft.visibility = View.VISIBLE
                tvChatRight.visibility = View.GONE
            }
        }
    }
}
