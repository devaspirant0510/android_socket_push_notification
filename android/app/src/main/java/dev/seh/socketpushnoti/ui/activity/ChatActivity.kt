package dev.seh.socketpushnoti.ui.activity;

import android.os.Bundle
import dev.seh.socketpushnoti.R
import dev.seh.socketpushnoti.base.BaseActivity
import dev.seh.socketpushnoti.databinding.ActivityChatBinding
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import io.socket.engineio.client.transports.WebSocket
import timber.log.Timber
import java.net.URI


/**
 * @author : seungHo
 * @since : 2022-01-05
 * class : ChatActivity.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
class ChatActivity:BaseActivity<ActivityChatBinding>(R.layout.activity_chat) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try{
            val options = IO.Options.builder()
                .setTransports(arrayOf(WebSocket.NAME))
                .build()
            val socket: Socket = IO.socket("ws://3.38.214.119:8080",options)
            socket.on("chat", Emitter.Listener {
                Timber.e(it[0].toString())
            })
            socket.connect()
        }catch(e:Exception){
            Timber.e(e.message.toString())
        }

    }
}