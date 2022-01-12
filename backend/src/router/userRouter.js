const express = require("express");
const {Room,User} = require("../models/")
const {v4} = require("uuid");
const jwt = require("jsonwebtoken");
const {successMsg} = require("../util/Result");
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

module.exports = router;