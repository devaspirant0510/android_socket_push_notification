const express = require("express");
const loginRouter = require("./router/loginRouter");
const registerRouter = require("./router/registerRouter");
const roomRouter = require("./router/roomRouter");
const chatRouter = require("./router/chatRouter");

const router = express.Router();
router.get("/",async(req,res,next)=>{
    try{
        res.render("index");
    }catch (err){
        console.log(err)
    }

})
router.use("/login",loginRouter);
router.use("/register",registerRouter);
router.use("/room",roomRouter);
router.use("/chat",chatRouter);

module.exports = router;

