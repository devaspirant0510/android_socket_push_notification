package dev.seh.socketpushnoti.repository;

import android.widget.Toast
import dev.seh.socketpushnoti.App
import dev.seh.socketpushnoti.model.api.JWTResult
import dev.seh.socketpushnoti.model.api.User
import dev.seh.socketpushnoti.model.types.RequestLoginType
import dev.seh.socketpushnoti.model.types.RequestRegisterType
import dev.seh.socketpushnoti.model.types.TokenType
import dev.seh.socketpushnoti.network.RetrofitService
import dev.seh.socketpushnoti.util.Result
import dev.seh.socketpushnoti.util.Util
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

            val data = RetrofitService.getInstance().requestVerifyUser(TokenType(token)).data
            Timber.e(data.toString())
            val result = Util.convertLTMtoObject<JWTResult>(data)
            Timber.e(result.toString())
            return User(
                id = result.id,
                name = result.name,
                userId = result.userId,
            )
        } catch (e: Exception) {
            null
        }
    }

    suspend fun requestRegister(id:String?,userName:String?,pwd:String?): User? {
        return try {
            val data = RetrofitService.getInstance()
                .requestRegister(RequestRegisterType(userName, id, pwd))
            val result = Util.convertLTMtoObject<User>(data)
            return result as User
        } catch (e: Exception) {
            null
        }
    }

    suspend fun requestLogin(id: String, pwd: String): User? {
        try {
            val data =
                RetrofitService.getInstance().requestLogin(RequestLoginType(id, pwd))
            return if(data.message==Result.ERROR){
                Toast.makeText(App.INSTANCE,data.data as String,Toast.LENGTH_SHORT).show()
                null
            }else{
                val result: User = Util.convertLTMtoObject(data.data)
                Toast.makeText(App.INSTANCE,"로그인 성공",Toast.LENGTH_SHORT).show()
                result
            }
        } catch (e: Exception) {
            Timber.e(e.message.toString())
            return null
        }
    }
}