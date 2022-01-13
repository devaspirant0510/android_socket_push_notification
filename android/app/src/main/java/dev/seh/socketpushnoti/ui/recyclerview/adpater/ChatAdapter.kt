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
class ChatAdapter(private val socketId:String) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mList: MutableList<ChatData> = mutableListOf()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is OtherChatViewHolder){
            holder.setData(mList[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return OtherChatViewHolder(parent)
    }

    override fun getItemCount(): Int = mList.size


    fun addItem(data: ChatData) {
        mList.add(data)
    }

    companion object {
        private const val OTHER:Int = 1
        private const val MY:Int =2
    }

}