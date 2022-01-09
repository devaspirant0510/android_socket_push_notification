package dev.seh.socketpushnoti.ui.recyclerview.viewholder;

import android.view.ViewGroup
import dev.seh.socketpushnoti.R
import dev.seh.socketpushnoti.base.BaseViewHolder
import dev.seh.socketpushnoti.databinding.ItemChatMyBinding
import dev.seh.socketpushnoti.model.types.ChatData

/**
 * @author : seungHo
 * @since : 2022-01-09
 * class : ChatViewHolder.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
open class MyChatViewHolder(parent: ViewGroup)
    :BaseViewHolder<ItemChatMyBinding>(parent, R.layout.item_chat_other) {
        fun setData(data:ChatData){
            mBinding.chatData = data
        }
}