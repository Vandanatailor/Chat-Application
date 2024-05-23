package com.example.chatapp.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.model.ContactDetails
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chatapp.databinding.ListUsersBinding
import com.example.chatapp.extra.onSelectItemClick
import com.example.chatapp.model.User
import com.example.chatapp.utils.DataItemClickListner
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class AllUsersAdapter(private val dataList: List<User>,
      private val selectItemClick: onSelectItemClick

)
    : RecyclerView.Adapter<AllUsersAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ListUsersBinding)
           : RecyclerView.ViewHolder(binding.root){
               fun bind(data : User){
                   binding.tvUserName.text=data.userName
               }
           }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListUsersBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])

        holder.itemView.setOnClickListener {
            if (holder.absoluteAdapterPosition != RecyclerView.NO_POSITION) {
                selectItemClick.itemClick(
                    holder.absoluteAdapterPosition,
                    "Users"
                )
            }
        }


    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
