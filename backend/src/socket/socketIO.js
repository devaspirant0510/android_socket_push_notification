const SocketIO = require("socket.io");

module.exports = (server) => {
    const io = SocketIO(server, {path: "/socket.io"});
    const room = io.of("chat");
    room.on("connection", function (socket) {
        console.log("socket ",socket.id)
        room.to(socket.id).emit("room-join",socket.id); // 연결된 유저에게만 socket Id 보냄
        const req = socket.request;
        const ip = req.headers['x-forwarded-for'] || req.connection.remoteAddress;
        console.log(`접속함 ${ip} 에서 `)
        console.log(socket.id);
        socket.on("disconnect", function () {
            /*
                        socket.leave("room2",(a)=>{
                            console.log(a)
                        })
            */
            console.log(`연결 끊김 ${ip} 에서 `)
        })
        socket.on("chat",(socketId,userName,content)=>{
            console.log("chat",socketId,userName,content);
            room.emit("chat",socketId,userName,content);
        });
    });
}
