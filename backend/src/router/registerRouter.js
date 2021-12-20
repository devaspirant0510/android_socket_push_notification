const express = require("express");
const {User,Room,UserRoom,Chat} = require("../models");
const {strConvertHash} = require("../util/utilLogic");
const {v4} = require("uuid");

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
        res.json(addUser.toJSON());
    }catch (err){
        next(err);
    }
});

module.exports = router;