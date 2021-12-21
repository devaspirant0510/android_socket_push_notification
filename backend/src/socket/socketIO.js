const SocketIO = require("socket.io");

module.exports = (server) => {
    const io = SocketIO(server, {path: "/socket.io"});
    const room = io.of("chat");
    room.on("connection", function (socket) {
        const req = socket.request;
        const ip = req.headers['x-forwarded-for'] || req.connection.remoteAddress;
        console.log(`접속함 ${ip} 에서 `)
        socket.join("room2");
        console.log(socket.id);
        console.log(room.to("room2"))
        socket.on("disconnect", function () {
/*
            socket.leave("room2",(a)=>{
                console.log(a)
            })
*/
            console.log(room.to("room2"))
            console.log(`연결 끊김 ${ip} 에서 `)
        })
        socket.emit("userCount",io.engine.clientsCount);
        socket.on("message", function (data) {
            console.log(data);
            io.emit("message",data);
        });
        socket.on("chat",data=>{
            console.log("chat",data);
            room.to("room2").emit("chat",data);

        })

    });
}
