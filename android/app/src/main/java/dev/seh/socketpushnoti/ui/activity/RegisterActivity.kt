package dev.seh.socketpushnoti.ui.activity;

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dev.seh.socketpushnoti.R
import dev.seh.socketpushnoti.base.BaseActivity
import dev.seh.socketpushnoti.base.BaseViewModelFactory
import dev.seh.socketpushnoti.databinding.ActivityRegisterBinding
import dev.seh.socketpushnoti.repository.Repository
import dev.seh.socketpushnoti.viewmodel.MainViewModel
import dev.seh.socketpushnoti.viewmodel.RegisterViewModel

/**
 * @author : seungHo
 * @since : 2022-01-13
 * class : RegisterActivity.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
class RegisterActivity:BaseActivity<ActivityRegisterBinding>(R.layout.activity_register) {
    lateinit var viewModel:RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this,BaseViewModelFactory(Repository())).get(RegisterViewModel::class.java)
        mBinding.viewmodel = viewModel
        viewModel.inputRegisterPwd.observe(this, Observer {
            if(it.isNotEmpty() && viewModel.inputRegisterPwdCheck.value!=null){
                if(it!=viewModel.inputRegisterPwdCheck.value){
                    mBinding.tidPwdCheck.error="비밀번호를 확인해주세요"
                }else{
                    mBinding.tidPwdCheck.error = null
                }
            }

        })
        viewModel.inputRegisterPwdCheck.observe(this, Observer {
            if(it.isNotEmpty() && viewModel.inputRegisterPwd.value!=null){
                if(it!=viewModel.inputRegisterPwd.value){
                    mBinding.tidPwdCheck.error="비밀번호를 확인해주세요"
                }
                else{
                    mBinding.tidPwdCheck.error=null
                }
            }

        })
        viewModel.isRegisterSuccess.observe(this,{
            if(it==true){
                finish()
            }
        })

    }
}