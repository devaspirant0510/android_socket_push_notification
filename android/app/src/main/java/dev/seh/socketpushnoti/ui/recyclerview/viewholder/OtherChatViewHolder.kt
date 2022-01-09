package dev.seh.socketpushnoti.ui.recyclerview.viewholder;

import android.view.ViewGroup
import android.widget.Toast
import dev.seh.socketpushnoti.R
import dev.seh.socketpushnoti.base.BaseViewHolder
import dev.seh.socketpushnoti.databinding.ItemChatOtherBinding
import dev.seh.socketpushnoti.model.types.ChatData

/**
 * @author : seungHo
 * @since : 2022-01-09
 * class : OtherChatViewHolder.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
open class OtherChatViewHolder(parent: ViewGroup)
    :BaseViewHolder<ItemChatOtherBinding>(parent, R.layout.item_chat_other){
    fun setData(data:ChatData){
        mBinding.itemOtherName.text = data.user;
        mBinding.itemOtherContent.text = data.content
        mBinding.executePendingBindings()
//        mBinding.chatData = data
    }
}