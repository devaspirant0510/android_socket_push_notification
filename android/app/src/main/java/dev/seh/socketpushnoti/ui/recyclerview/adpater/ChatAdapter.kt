package dev.seh.socketpushnoti.ui.recyclerview.adpater;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.seh.socketpushnoti.R
import dev.seh.socketpushnoti.databinding.ItemChatMyBinding
import dev.seh.socketpushnoti.model.types.ChatData
import dev.seh.socketpushnoti.ui.recyclerview.viewholder.MyChatViewHolder
import dev.seh.socketpushnoti.ui.recyclerview.viewholder.OtherChatViewHolder

/**
 * @author : seungHo
 * @since : 2022-01-09
 * class : ChatAdatper.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
class ChatAdapter : RecyclerView.Adapter<OtherChatViewHolder>() {
    private val mList: MutableList<ChatData> = mutableListOf()
    override fun onBindViewHolder(holder: OtherChatViewHolder, position: Int) {
        holder.setData(mList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherChatViewHolder {
        return OtherChatViewHolder(parent)
    }

    override fun getItemCount(): Int = mList.size

    fun addItem(data: ChatData) {
        mList.add(data)
    }
}