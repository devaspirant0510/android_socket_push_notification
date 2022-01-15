const express = require("express");
const {Room,User} = require("../models/")
const {v4} = require("uuid");
const jwt = require("jsonwebtoken");
const {successMsg,failMsg} = require("../util/Result");
const {Router} = require("express");

const router = Router();

router.post("/verify",async (req,res,next)=>{
    try{
        const {token} = req.body;
        const result = jwt.verify(token,process.env.JWT_TOKEN)
        res.json(successMsg(201,result));
    }catch(err){
        next(err);
    }
});
router.put("/socket-id",async (req,res,next)=>{
    try{
        console.log("put socket id");
        const {userId,socketId} = req.body
        console.log(userId,socketId)
        const result = await User.update({socketId:socketId},{where:{id:userId}})
        if(result.toString()==='0'){
            res.json(failMsg(401,"수정실패"))
        }else{
            res.json(successMsg(203,result));
        }
    }catch (err){
        next(err.toString())
    }
});

router.put("/socket-id/disconnect",async (req,res,next)=>{
    try{
        console.log("delete socket id")
        const {socketId} = req.body;
        console.log(socketId);
        const result = await User.update({socketId:null},{where:{socketId:socketId}});
        if(result.toString()==='0'){
            res.json(failMsg(401,"삭제"))
        }else{
            res.json(successMsg(203,result));
        }
    }catch (err){
        next(err.toString());
    }
});

module.exports = router;