package com.example.chatapp.ui.userflow.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.model.ContactDetails
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chatapp.databinding.ChatlistLeftBinding
import com.example.chatapp.databinding.ListUsersBinding
import com.example.chatapp.extra.onSelectItemClick
import com.example.chatapp.model.ChatMessage
import com.example.chatapp.model.User
import com.example.chatapp.utils.DataItemClickListner
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class ChatAdapter(private val chatList : List<ChatMessage>,
                  private val selectItemClick: onSelectItemClick

)
    : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ChatlistLeftBinding)
           : RecyclerView.ViewHolder(binding.root){
               fun bind(data : ChatMessage){
                   binding.tvChatRight.text=data.message
               }
           }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ChatlistLeftBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(dataList[position])
//
//        holder.itemView.setOnClickListener {
//            if (holder.absoluteAdapterPosition != RecyclerView.NO_POSITION) {
//                selectItemClick.itemClick(
//                    holder.absoluteAdapterPosition,
//                    dataList[position].uid
//                )
//            }
//        }


    }

    override fun getItemCount(): Int {
        return 4
    }
}
