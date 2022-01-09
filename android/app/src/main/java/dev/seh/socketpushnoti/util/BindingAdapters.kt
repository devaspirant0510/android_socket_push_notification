package dev.seh.socketpushnoti.util;

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.seh.socketpushnoti.model.api.User
import dev.seh.socketpushnoti.model.types.ChatData
import dev.seh.socketpushnoti.ui.recyclerview.adpater.ChatAdapter
import timber.log.Timber

/**
 * @author : seungHo
 * @since : 2022-01-05
 * class : BindingAdapters.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
object BindingAdapters {
    @JvmStatic
    @BindingAdapter("loadUser")
    fun loadUser(textView: TextView,user: User){
        Timber.e(user.toString())
    }
    @JvmStatic
    @BindingAdapter("loadChat")
    fun loadChat(recyclerView: RecyclerView,data:MutableList<ChatData>?){
        val adapter = recyclerView.adapter as ChatAdapter

    }
}