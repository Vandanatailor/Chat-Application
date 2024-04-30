package com.example.chatapp.utils

import android.widget.Toast
import android.content.Context


object CommanFunction {
    public fun showToast(context : Context, msg : String)
    {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}