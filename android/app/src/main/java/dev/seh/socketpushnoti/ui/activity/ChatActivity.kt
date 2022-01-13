package dev.seh.socketpushnoti.ui.activity;

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import dev.seh.socketpushnoti.R
import dev.seh.socketpushnoti.base.BaseActivity
import dev.seh.socketpushnoti.base.BaseViewModelFactory
import dev.seh.socketpushnoti.databinding.ActivityChatBinding
import dev.seh.socketpushnoti.model.types.ChatData
import dev.seh.socketpushnoti.network.SocketIOAPI
import dev.seh.socketpushnoti.repository.Repository
import dev.seh.socketpushnoti.ui.recyclerview.adpater.ChatAdapter
import dev.seh.socketpushnoti.viewmodel.ChatViewModel
import dev.seh.socketpushnoti.viewmodel.MainViewModel
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import io.socket.engineio.client.transports.WebSocket
import timber.log.Timber
import java.net.URI
import java.util.*


/**
 * @author : seungHo
 * @since : 2022-01-05
 * class : ChatActivity.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
class ChatActivity : BaseActivity<ActivityChatBinding>(R.layout.activity_chat) {
    private lateinit var mSocket: Socket
    private lateinit var viewModel: ChatViewModel
    private lateinit var mainViewModel: MainViewModel
    private lateinit var name:String
    private lateinit var id:String
    private lateinit var socketId:String
    private lateinit var adapter:ChatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this,BaseViewModelFactory(Repository())).get(ChatViewModel::class.java)
        mainViewModel = ViewModelProvider(this,BaseViewModelFactory(Repository())).get(MainViewModel::class.java)
        mBinding.viewmodel = viewModel
        name = intent.getStringExtra("name")!!
        id = intent.getStringExtra("id")!!

        Timber.e("${mainViewModel.userInfo.value.toString()}  a")

        mSocket = IO.socket("http://3.38.214.119:8080/chat")
        mSocket.connect()
        mSocket.on(Socket.EVENT_CONNECT, onConnect);
        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);

        mSocket.on(SocketIOAPI.ROOM_JOIN,Emitter.Listener{
            Timber.e("socket id :${it[0]}")
            runOnUiThread {
                socketId = it[0].toString()
                adapter= ChatAdapter(socketId)
                mBinding.chatList.adapter = adapter
            }
        })
        // chat (id,name,content)
        mSocket.on("chat", Emitter.Listener {
            runOnUiThread {
                Timber.e("${it[0]} ${it[1]} ${it[2]}")
                if(it[0].toString()==socketId){
                    adapter.addItem(ChatData(it[0].toString(), it[1].toString(),it[2].toString()))
                    adapter.notifyItemInserted(adapter.itemCount)

                }else{
                    adapter.addItem(ChatData(it[0].toString(), it[1].toString(),it[2].toString()))
                    adapter.notifyItemInserted(adapter.itemCount)
                }
                mBinding.chatList.scrollToPosition(adapter.itemCount)
            }
        })
        mBinding.btnSend.setOnClickListener {
            mSocket.emit(SocketIOAPI.CHAT,socketId,name,mBinding.tietChat.text)
            mBinding.tietChat.text = null
        }


    }

    private val onConnect = Emitter.Listener {
        Timber.e("connected...")
        // This doesn't run in the UI thread, so use:
        // .runOnUiThread if you want to do something in the UI
    }

    private val onConnectError =
        Emitter.Listener {
            Timber.e("Error connecting...")
            Timber.e("error : ${it[0]} ")
        }
}