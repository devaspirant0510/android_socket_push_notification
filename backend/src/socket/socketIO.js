const SocketIO = require("socket.io");
const axios = require("axios");

module.exports = (server) => {
    const io = SocketIO(server, {path: "/socket.io"});
    const room = io.of("chat");
    room.on("connection", async function (socket) {
        console.log("socket ", socket.id)
        const req = socket.request;
        const ip = req.headers['x-forwarded-for'] || req.connection.remoteAddress;
        console.log(`접속함 ${ip} 에서 `)
        room.to(socket.id).emit("room-join", socket.id); // 연결된 유저에게만 socket Id 보냄
        socket.on("room-join-client", async (userId) => {
            const result = await axios.put(`${process.env.BASE_DOMAIN}/api/user/socket-id`, {
                userId,
                socketId: socket.id
            });
            console.log(result)
        });

        socket.on("disconnect", async function () {
            await axios.put(`${process.env.DOMAIN}/api/user/socket-id/disconnect`, {
                socketId: socket.id
            });
            console.log(`연결 끊김 ${ip} 에서 `)
        })
        socket.on("chat", async (socketId, userName, content, userId) => {
            console.log("chat", socketId, userName, content, userId);
            room.emit("chat", socketId, userName, content, userId);
        });
    });
}
