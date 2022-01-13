package dev.seh.socketpushnoti.viewmodel;

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.seh.socketpushnoti.App
import dev.seh.socketpushnoti.repository.Repository
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.Exception

/**
 * @author : seungHo
 * @since : 2022-01-13
 * class : RegisterViewModel.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
class RegisterViewModel(private val repository: Repository): ViewModel() {
    val inputRegisterId = MutableLiveData<String>()
    val inputRegisterPwd = MutableLiveData<String>()
    val inputRegisterPwdCheck = MutableLiveData<String>()
    val inputRegisterName = MutableLiveData<String>()

    val isRegisterSuccess = MutableLiveData<Boolean>()

    fun requestRegister(){
        viewModelScope.launch {
            try{
                val result = repository.requestRegister(inputRegisterId.value,inputRegisterName.value,inputRegisterPwd.value)
                if(result!=null){
                    Toast.makeText(App.INSTANCE,"회원가입 성공",Toast.LENGTH_SHORT).show()
                    isRegisterSuccess.value = true
                }else{
                    Toast.makeText(App.INSTANCE,"회원가입 실패",Toast.LENGTH_SHORT).show()
                }

            }catch(e:Exception){
                Timber.e(e.message.toString())

            }
        }

    }
    init{
        isRegisterSuccess.value = false
    }
}