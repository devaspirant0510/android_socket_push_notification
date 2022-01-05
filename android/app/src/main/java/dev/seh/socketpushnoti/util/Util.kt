package dev.seh.socketpushnoti.util;

import com.google.gson.Gson

/**
 * @author : seungHo
 * @since : 2022-01-05
 * class : Util.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
object Util {
    fun <T> convertLTMtoObject(data:Any,type:Any): T {
        val gson = Gson()
        val json = gson.toJson(gson)
        return gson.fromJson(json,type::class.java) as T

    }
}