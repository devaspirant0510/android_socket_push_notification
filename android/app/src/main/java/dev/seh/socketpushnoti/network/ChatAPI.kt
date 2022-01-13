package dev.seh.socketpushnoti.network;

import dev.seh.socketpushnoti.model.api.JWTResult
import dev.seh.socketpushnoti.model.api.User
import dev.seh.socketpushnoti.model.types.RequestLoginType
import dev.seh.socketpushnoti.model.types.RequestRegisterType
import dev.seh.socketpushnoti.model.types.TokenType
import dev.seh.socketpushnoti.util.Result
import retrofit2.http.Body
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
    @POST("/api/login")
    suspend fun requestLogin(
        @Body loginBody:RequestLoginType
    ):Result.ApiResult<User>

    @POST("/api/user/verify")
    suspend fun requestVerifyUser(
        @Body token:TokenType,
    ):Result.ApiResult<JWTResult>

    @POST("/api/register")
    suspend fun requestRegister(
        @Body registerBody:RequestRegisterType
    ):Result.ApiResult<User>
}