const express = require("express");
const {jwtVerify} = require("../util/jwtConfig")
const {failMsg} = require("../util/Result");
const router = express.Router();

router.get("/",async(req,res,next)=>{
    res.redirect("/");
});
router.get("/:id",async(req,res,next)=>{
    try{
        const {id} = req.params;
        console.log(req.query.token)
        const result=await jwtVerify(req.query.token,process.env.JWT_TOKEN);
        console.log(result)
        if(req.query.token && result!==null){
            res.render("socket",{name:result.name});
        }
        else{
            res.send(failMsg(403,"유효한 토큰이 아닙니다."))
        }
    }
    catch (err){
        next(err.toString());
    }
});
router.post("/",async(req,res,next)=>{
    try{

    }catch (err){
        next(err);
    }
});

module.exports = router;