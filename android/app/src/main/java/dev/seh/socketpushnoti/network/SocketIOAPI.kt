package dev.seh.socketpushnoti.network;

import io.socket.emitter.Emitter

/**
 * @author : seungHo
 * @since : 2022-01-04
 * class : SocketIOAPI.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
sealed class SocketIOAPI {
    companion object {
        const val ROOM_JOIN = "room-join"
        const val CHAT = "chat"
    }
}