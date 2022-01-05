package dev.seh.socketpushnoti.network;

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import dev.seh.socketpushnoti.model.types.RequestLoginType
import dev.seh.socketpushnoti.model.types.RequestRegisterType
import dev.seh.socketpushnoti.util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @author : seungHo
 * @since : 2022-01-04
 * class : ChatAPI.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
interface ChatAPI {
    @POST("/login")
    suspend fun requestLogin(
        @Body loginBody:RequestLoginType
    ):Result.SuccessResult

    @POST("/user/verify")
    suspend fun requestVerifyUser(
        @Query("token") token:String?=null,
    ):Result.SuccessResult

    @POST("/register")
    suspend fun requestRegister(
        @Body registerBody:RequestRegisterType
    ):Result.SuccessResult
}