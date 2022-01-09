package dev.seh.socketpushnoti.base;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * @author : seungHo
 * @since : 2022-01-09
 * class : BaseViewHolder.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
open class BaseViewHolder<T : ViewDataBinding>(parent: ViewGroup, @LayoutRes layoutRes: Int) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)) {
    protected val mBinding: T by lazy {
        DataBindingUtil.bind(itemView)!!
    }
}