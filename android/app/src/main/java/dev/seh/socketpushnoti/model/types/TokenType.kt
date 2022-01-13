package dev.seh.socketpushnoti.model.types;

import com.google.gson.annotations.SerializedName

/**
 * @author : seungHo
 * @since : 2022-01-13
 * class : TokenType.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
data class TokenType(
    @SerializedName("token")
    val token:String?=null
    ) {
}