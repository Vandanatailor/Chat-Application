package com.example.chatapp.utils

import android.content.SharedPreferences
import javax.inject.Inject

class AppSession @Inject constructor( var sharedPreferences: SharedPreferences) {

    fun getStringData( keyName:String, defaultValue: String?):String{
           return sharedPreferences?.let {
               sharedPreferences.getString(keyName, defaultValue)
           }.toString()

    }

    fun putStringData( keyName:String, defaultValue: String){

        sharedPreferences?. let {
            var editor= sharedPreferences.edit()
            editor.putString(keyName,defaultValue)
            editor.commit()
        }

    }

    fun putIntData( keyName:String, defaultValue: Int){

        sharedPreferences?. let {
            var editor=sharedPreferences.edit()
            editor.putInt(keyName,defaultValue)
            editor.commit()
        }

    }
    fun putBooleanData(keyName: String, defaultValue: Boolean) {
        sharedPreferences?. let {
            var editor=sharedPreferences.edit()
            editor.putBoolean(keyName,defaultValue)
            editor.commit()
        }

    }

    fun getBooleanData(keyName: String, defaultValue: Boolean): Boolean? {
        return sharedPreferences?.let {
            sharedPreferences.getBoolean(keyName, defaultValue)
        }
    }

    fun getIntData(keyName: String, defaultValue: Int): Int? {
        return sharedPreferences?.let {
            sharedPreferences.getInt(keyName, defaultValue)
        }
    }

//     fun getObjectData(keyName: String, classOfT: Class<*>?): Any? {
//         val json= sharedPreferences?.getString(keyName, "")
//         val gson = Gson()
//         val selectedUser = gson.fromJson(json, classOfT)
//        return selectedUser
//
//    }
//
//    fun putObjectData(keyName: String, modelObj: Object){
//
//        val gson = Gson()
//        val jsonObject = gson.toJson(modelObj)
//        var editor=sharedPreferences.edit()
//        editor.putString(keyName, jsonObject)
//        editor.commit()
//         }

    fun clearData() {
        sharedPreferences.edit().clear().apply()
    }

}