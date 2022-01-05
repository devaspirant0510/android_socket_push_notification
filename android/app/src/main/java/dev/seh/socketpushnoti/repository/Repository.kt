package dev.seh.socketpushnoti.repository;

import com.google.gson.Gson
import dev.seh.socketpushnoti.model.api.User
import dev.seh.socketpushnoti.model.types.RequestLoginType
import dev.seh.socketpushnoti.model.types.RequestRegisterType
import dev.seh.socketpushnoti.network.RetrofitService
import timber.log.Timber

/**
 * @author : seungHo
 * @since : 2022-01-04
 * class : Repository.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
class Repository {
    suspend fun requestVerifyUser(token: String): User? {
        return try {
            val data = RetrofitService.getInstance().requestVerifyUser(token).data as User
            val gson = Gson()
            val user = gson.toJson(data)
            return gson.fromJson(user, User::class.java) as User
        } catch (e: Exception) {
            null
        }
    }

    suspend fun requestRegister(): User? {
        return try {
            val data = RetrofitService.getInstance()
                .requestRegister(RequestRegisterType("seungho", "lsh0510", "1234"))
            val gson = Gson()
            val user = gson.toJson(data)
            return gson.fromJson(user, User::class.java) as User
        } catch (e: Exception) {
            null
        }
    }

    suspend fun requestLogin(id: String, pwd: String): User? {
        try {
            val data =
                RetrofitService.getInstance()
                    .requestLogin(
                        RequestLoginType("lsh0510", "1234")
                    )
                    .data
            val gson = Gson()
            val user = gson.toJson(data)
            return gson.fromJson(user, User::class.java) as User
        } catch (e: Exception) {
            Timber.e(e.message)
            return null
        }
    }
}