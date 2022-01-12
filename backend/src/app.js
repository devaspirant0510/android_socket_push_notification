const express = require('express');
const nunjucks = require('nunjucks');
const http = require("http");
const {failMsg} = require("./util/Result")
require("dotenv").config();

const router = require("./router");
const {sequelize} = require("./models")
const socketServer = require("./socket/socketIO");
const SocketIO = require("socket.io");

const app = express();
nunjucks.configure('src/views', {
    watch:true,
    express: app
});
app.set('view engine', 'html');

app.use(express.json());
app.use(express.urlencoded({extended:true}));
app.set("port",process.env.PORT||8080);
// api router
app.use("/api",router);

sequelize.sync({force:false}).then(value => {
    console.log("연결 성공");
}).catch(reason => {
    console.log(reason);
});

// error handle middleware
app.use((err,req,res,next)=>{
    res.json(failMsg(200,err))
});

const server = http.createServer(app);

server.listen(app.get("port"),()=>{
    console.log("server is open");
});
socketServer(server);