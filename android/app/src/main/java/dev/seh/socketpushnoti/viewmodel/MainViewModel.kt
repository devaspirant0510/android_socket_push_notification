package dev.seh.socketpushnoti.viewmodel;

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.seh.socketpushnoti.App
import dev.seh.socketpushnoti.model.api.User
import dev.seh.socketpushnoti.repository.Repository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * @author : seungHo
 * @since : 2022-01-04
 * class : MainViewModel.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
class MainViewModel : ViewModel() {
    private val repository = Repository()

    private val _userInfo = MutableLiveData<User>()
    val userInfo: LiveData<User>
        get() = _userInfo

    val inputLoginId = MutableLiveData<String>()
    val inputLoginPwd = MutableLiveData<String>()

    val moveToChat = MutableLiveData<Boolean>()

    fun moveToChatAct(){
        moveToChat.value = true
    }

    fun requestLogin() {
        viewModelScope.launch {
            val data = repository.requestLogin(
                inputLoginId.value.toString(),
                inputLoginPwd.value.toString()
            )
            Timber.e(data.toString())
            if(data!=null){
                Timber.e("로그인 성공 ${data.toString()}")
                _userInfo.postValue(data)
                moveToChatAct()

            }else{
                return@launch
            }
        }
    }
    fun requestRegister(){
        viewModelScope.launch {
            val data = repository.requestRegister()
            Timber.e(data.toString())

        }
    }

    private suspend fun verifyUser() {
        try {
            val user =
                repository.requestVerifyUser("eyJhbGciOiJIUzI1NiIsInA5cCI6IkpXVCJ9.eyJuYW1lIjoiYWFhYWEiLCJpZCI6ImZiMjk0OWZjLTE5MTgtNGU0Zi1iM2Y3LTZmZGUwNTRjNTQwYiIsInVzZXJJZCI6ImxzaDAyMDUxMCIsImlhdCI6MTY0MTMwMTA4MiwiZXhwIjoxNjQyNTEwNjgyLCJpc3MiOiJpc3N1ZXIifQ.nqfK1D-UHtQmNQY4LwJwJ1FBOH-mnb5WnQogH-N2H7Y")
            user?.let {
                _userInfo.value = user
                Toast.makeText(App.INSTANCE, user.toString(), Toast.LENGTH_SHORT).show()
            }
            if (user == null) {
                Toast.makeText(App.INSTANCE, "JwtTokenError", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(App.INSTANCE, e.message.toString(), Toast.LENGTH_LONG).show()
            Timber.d(e.message.toString())
        }
    }

    init {
        inputLoginId.value = ""
        viewModelScope.launch {
            verifyUser()
        }

    }
}