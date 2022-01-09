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
    inline fun < reified T:Any> convertLTMtoObject(data:Any): T {
        val gson = Gson()
        val json = gson.toJson(data)
        return gson.fromJson(json,T::class.java) as T
    }
}