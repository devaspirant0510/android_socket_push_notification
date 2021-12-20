const express = require("express");
const {User} = require("../models");
const {strConvertHash} = require("../util/utilLogic");

const router = express.Router();

router.post("/",async(req,res,next)=>{
    try{
        const {userId,password} = req.body;
        const hashPwd = strConvertHash(password);
        console.log(`password:${password} hash:${hashPwd}`)
        const users = await User.findOne({where:{userId,password:hashPwd}})
        if(users===null){
            res.json({message:"error"})
        }else{
            const user2json = users.toJSON();
            res.json(user2json);
        }
    }catch (err){
        next(err);
    }
});

module.exports = router;