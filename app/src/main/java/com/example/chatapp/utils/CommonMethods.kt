package com.example.chatapp.utils

import java.util.regex.Pattern

class CommonMethods {

    companion object{

        fun emailValidation(email: String?): Boolean {
            val EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
            val pattern = Pattern.compile(EMAIL_PATTERN)
            val matcher = pattern.matcher(email)
            return matcher.matches()
        }
    }
}
public interface onDataItemClickListner{
   public fun onItemClick(position :Int,type : String)
}