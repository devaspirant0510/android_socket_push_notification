package dev.seh.socketpushnoti.viewmodel;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.seh.socketpushnoti.model.types.ChatData
import dev.seh.socketpushnoti.repository.Repository

/**
 * @author : seungHo
 * @since : 2022-01-09
 * class : ChatViewModel.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
class ChatViewModel(private val repository: Repository): ViewModel() {
    private val _chatData = MutableLiveData<MutableList<ChatData>>()
    val chatData:LiveData<MutableList<ChatData>>
        get() = _chatData

}