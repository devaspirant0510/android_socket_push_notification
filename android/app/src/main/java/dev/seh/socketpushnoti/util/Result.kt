package dev.seh.socketpushnoti.util

sealed class Result {
    companion object {
        const val OK = "OK"
        const val ERROR = "ERROR"
    }

    data class ApiResult<T>(

        val status:Int,
        val message:String,
        val data:T
    )

}