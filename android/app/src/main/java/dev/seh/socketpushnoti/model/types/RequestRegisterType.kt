package dev.seh.socketpushnoti.model.types;

import com.google.gson.annotations.SerializedName

/**
 * @author : seungHo
 * @since : 2022-01-05
 * class : RequestRegisterType.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
data class RequestRegisterType(
    @SerializedName("name")
    val name:String?=null,
    @SerializedName("userId")
    val userId:String?=null,
    @SerializedName("password")
    val password:String?=null,
) {
}