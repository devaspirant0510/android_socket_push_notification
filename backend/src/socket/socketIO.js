const SocketIO = require("socket.io");

module.exports = (server) => {
    const io = SocketIO(server, {path: "/socket.io"});

    io.on("connection", function (socket) {
        const req = socket.request;
        const ip = req.headers['x-forwarded-for'] || req.connection.remoteAddress;
        console.log(`접속함 ${ip} 에서 `)
        socket.on("disconnect", function () {
            console.log(`연결 끊김 ${ip} 에서 `)
        })
        socket.emit("userCount",io.engine.clientsCount);
        socket.on("message", function (data) {
            console.log(data);
            io.emit("message",data);
        });

    });
}
