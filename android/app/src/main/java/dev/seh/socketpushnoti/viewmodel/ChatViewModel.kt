package dev.seh.socketpushnoti.viewmodel;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.seh.socketpushnoti.model.types.ChatData

/**
 * @author : seungHo
 * @since : 2022-01-09
 * class : ChatViewModel.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
class ChatViewModel: ViewModel() {
    private val _chatData = MutableLiveData<List<ChatData>>()
    val chatData:LiveData<List<ChatData>>
        get() = _chatData
}