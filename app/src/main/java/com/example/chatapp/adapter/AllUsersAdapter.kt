package com.example.chatapp.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.model.ContactDetails
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chatapp.databinding.ListUsersBinding
import com.example.chatapp.utils.DataItemClickListner
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class AllUsersAdapter(
    firebaseDataList: FirebaseRecyclerOptions<ContactDetails>,
    private val context: Context,
    private val onDataItemClickListner: DataItemClickListner,
) : FirebaseRecyclerAdapter<ContactDetails,
        AllUsersAdapter.ViewHolder>(firebaseDataList) {

    inner class ViewHolder(val binding: ListUsersBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListUsersBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, contactDetails: ContactDetails) {
        holder.binding.tvUserName.text=contactDetails.name
        Log.d("username", "onBindViewHolder: "+contactDetails.name)

    }

}
