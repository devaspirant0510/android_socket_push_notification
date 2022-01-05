package dev.seh.socketpushnoti.util

sealed class Result {
    data class SuccessResult(
        val status:Int,
        val message:String,
        val data:Any
    )
    data class FailResult(
        val status:Int,
        val message:String,
        val errorCode:Int,
        val data:Any
    )
}