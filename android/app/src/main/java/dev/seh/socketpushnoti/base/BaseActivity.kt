package dev.seh.socketpushnoti.base;

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @author : seungHo
 * @since : 2022-01-04
 * class : BaseActivity.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
open class BaseActivity<T:ViewDataBinding>(@LayoutRes val res:Int) :AppCompatActivity(){
    lateinit var mBinding:T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,res)
        mBinding.lifecycleOwner = this
    }

}