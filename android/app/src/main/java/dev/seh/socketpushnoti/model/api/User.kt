package dev.seh.socketpushnoti.model.api;

/**
 * @author : seungHo
 * @since : 2022-01-04
 * class : User.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
data class User(
     var id:String?=null,
     var name:String?=null,
     var userId:String?=null,
     var password:String?=null,
     var socketId:String?=null,
     var token:String?=null,
) {
}