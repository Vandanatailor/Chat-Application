package com.example.chatapp.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.model.ContactDetails
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.chatapp.R
import com.example.chatapp.databinding.ListUsersBinding
import com.example.chatapp.utils.onDataItemClickListner
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class AllUsersAdapter(

    firebaseDataList: FirebaseRecyclerOptions<ContactDetails>,
    private val context: Context,
    private val onDataItemClickListner: onDataItemClickListner

) : FirebaseRecyclerAdapter<ContactDetails, AllUsersAdapter.ViewHolder>(firebaseDataList) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val listUsersBinding :ListUsersBinding
        listUsersBinding=ListUsersBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(listUsersBinding)

    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int, p2: ContactDetails) {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(private val binding: ListUsersBinding)
        : RecyclerView.ViewHolder(binding.root) {

    }

}
