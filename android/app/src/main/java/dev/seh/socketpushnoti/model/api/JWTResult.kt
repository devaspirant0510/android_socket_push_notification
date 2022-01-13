package dev.seh.socketpushnoti.model.api;

/**
 * @author : seungHo
 * @since : 2022-01-13
 * class : JWTResult.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
data class JWTResult(
    val name:String?=null,
    val id:String?=null,
    val userId:String?=null,
    val iat:String?=null,
    val exp:String?=null,
    val iss:String?=null
) {
}