package dev.seh.socketpushnoti.viewmodel;

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import dev.seh.socketpushnoti.App
import dev.seh.socketpushnoti.model.api.User
import dev.seh.socketpushnoti.repository.Repository
import dev.seh.socketpushnoti.service.SharedService
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.math.log

/**
 * @author : seungHo
 * @since : 2022-01-04
 * class : MainViewModel.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
class MainViewModel(private val repository: Repository) : AndroidViewModel(App.INSTANCE) {

    private val _userInfo = MutableLiveData<User>()
    val userInfo: LiveData<User>
        get() = _userInfo

    val inputLoginId = MutableLiveData<String>()
    val inputLoginPwd = MutableLiveData<String>()

    val moveToChat = MutableLiveData<Boolean>()
    val moveToRegister = MutableLiveData<Boolean>()

    fun moveToChatAct() {
        moveToChat.value = true
    }

    fun moveToRegisterAct(){
        moveToRegister.value = true
    }

    fun requestLogin() {
        viewModelScope.launch {
            val data = repository.requestLogin(
                inputLoginId.value.toString(),
                inputLoginPwd.value.toString()
            )
            if (data != null) {
                data.token?.let {
                    Timber.e("로그인 성공 ${data.toString()}")
                    _userInfo.postValue(data)
                    Timber.e(it)
                    App.sharedPreference.saveStringData(SharedService.TOKEN, it)
                    moveToChatAct()
                }

            } else {
                return@launch
            }
        }
    }


    private fun verifyUser() {
        viewModelScope.launch {
            try {
                val token = App.sharedPreference.loadStringData(SharedService.TOKEN)
                if (token.isNotEmpty()) {
                    val user =
                        repository.requestVerifyUser(token)
                    Timber.e(user.toString())
                    user?.let {
                        _userInfo.value = user
                        moveToChatAct()

                    }
                    if (user == null) {
                        Toast.makeText(App.INSTANCE, "JwtTokenError", Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(App.INSTANCE, e.message.toString(), Toast.LENGTH_LONG).show()
                Timber.d(e.message.toString())
            }
        }
    }

    init {
        inputLoginId.value = ""
        verifyUser()

    }
}