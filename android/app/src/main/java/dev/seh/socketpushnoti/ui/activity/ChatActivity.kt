package dev.seh.socketpushnoti.ui.activity;

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import dev.seh.socketpushnoti.R
import dev.seh.socketpushnoti.base.BaseActivity
import dev.seh.socketpushnoti.databinding.ActivityChatBinding
import dev.seh.socketpushnoti.model.types.ChatData
import dev.seh.socketpushnoti.ui.recyclerview.adpater.ChatAdapter
import dev.seh.socketpushnoti.viewmodel.ChatViewModel
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
    private lateinit var mSocket:Socket
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewmodel = ViewModelProvider(this).get(ChatViewModel::class.java)
        mBinding.viewmodel = viewmodel
        val adapter = ChatAdapter()
        mBinding.chatList.adapter = adapter

        mSocket = IO.socket("http://3.38.214.119:8080/chat")
        mSocket.connect()
        mSocket.on(Socket.EVENT_CONNECT, onConnect);
        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
        mSocket.on("chat", Emitter.Listener {
            runOnUiThread {
                adapter.addItem(ChatData("name",it[0].toString()))
                adapter.notifyItemInserted(adapter.itemCount)
            }
        })
        Timber.e(mSocket.connected().toString())


    }
    private val onConnect = Emitter.Listener {
        Timber.e( "connected...")
        // This doesn't run in the UI thread, so use:
        // .runOnUiThread if you want to do something in the UI
    }

    private val onConnectError =
        Emitter.Listener {
            Timber.e( "Error connecting...")
            Timber.e("error : ${it[0]} ")
        }
}