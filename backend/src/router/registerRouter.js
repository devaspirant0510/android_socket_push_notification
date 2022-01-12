const express = require("express");
const {User,Room,UserRoom,Chat} = require("../models");
const {strConvertHash} = require("../util/utilLogic");
const {v4} = require("uuid");
const {successMsg} = require("../util/Result");

const router = express.Router();

router.post("/",async(req,res,next)=>{
    try{
        const {name,userId,password} = req.body;
        const hashPwd = strConvertHash(password);
        console.log(`password:${password} hash:${hashPwd}`)
        const addUser = await User.create({
            id:v4(),
            name,
            userId,
            password:hashPwd
        });
        console.log("회원 가입 성공",addUser.toJSON())
        res.json(successMsg(201,addUser.toJSON()));
    }catch (err){
        console.log(err.toString())
        next(err.toString());
    }
});

module.exports = router;