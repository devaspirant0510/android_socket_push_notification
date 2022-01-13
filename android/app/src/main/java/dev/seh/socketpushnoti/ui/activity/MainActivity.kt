package dev.seh.socketpushnoti.ui.activity;

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dev.seh.socketpushnoti.App
import dev.seh.socketpushnoti.R
import dev.seh.socketpushnoti.base.BaseActivity
import dev.seh.socketpushnoti.base.BaseViewModelFactory
import dev.seh.socketpushnoti.databinding.ActivityMainBinding
import dev.seh.socketpushnoti.repository.Repository
import dev.seh.socketpushnoti.viewmodel.MainViewModel
import timber.log.Timber

/**
 * @author : seungHo
 * @since : 2022-01-05
 * class : MainActivity.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
class MainActivity:BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this,BaseViewModelFactory(Repository())).get(MainViewModel::class.java)
        mBinding.viewmodel = viewModel
        viewModel.moveToChat.observe(this, Observer {
            if(it){
                val intent = Intent(this@MainActivity,ChatActivity::class.java)
                intent.putExtra("name",viewModel.userInfo.value?.name)
                intent.putExtra("id",viewModel.userInfo.value?.id)
                startActivity(intent)
                Toast.makeText(App.INSTANCE,"${viewModel.userInfo.value?.name} 님 환영합니다.",Toast.LENGTH_SHORT).show()
                viewModel.moveToChat.value = false
                finish()
            }
        })
        viewModel.moveToRegister.observe(this,Observer{
            if(it){
                val intent = Intent(this@MainActivity,RegisterActivity::class.java)
                startActivity(intent)
                viewModel.moveToRegister.value = false
            }
        })
    }
}