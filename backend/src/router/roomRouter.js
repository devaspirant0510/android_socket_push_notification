const express = require("express");
const {Room,User} = require("../models/")
const {v4} = require("uuid");
const jwt = require("jsonwebtoken");
const {successMsg} = require("../util/Result");

const router = express.Router();

router.post("/create",async(req,res,next)=>{
    try{
        const {
            token,
            name
        } = req.body;
        const decode = jwt.verify(token,process.env.JWT_TOKEN);
        console.log(decode.userId);
        const result = await Room.create({
            id:v4(),
            name:name,
            masterId:decode.id
        });
        const fullData =await Room.findOne({
            where:{
                masterId:decode.id
            },
            include:User
        })
        res.json(successMsg(201,fullData.toJSON()));
    }catch (err){
        next(err);
    }
});
router.post("/join",async(req,res,next)=>{
    try{

    }catch(err){

    }
});

module.exports = router;