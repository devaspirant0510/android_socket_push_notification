package dev.seh.socketpushnoti.service;

import android.content.Context
import android.content.SharedPreferences

/**
 * @author : seungHo
 * @since : 2022-01-04
 * class : SharedService.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
class SharedService(context: Context) {
    companion object{
        const val TOKEN = "TOKEN"
    }
    private val sharedPreference:SharedPreferences = context.getSharedPreferences("pushNoti",Context.MODE_PRIVATE)
    fun saveStringData(key:String,value:String):Unit {
        with(sharedPreference.edit()) {
            putString(key, value)
            commit()
        }
    }

    fun loadStringData(key:String):String{
        return sharedPreference.getString(key,"").toString()
    }

}