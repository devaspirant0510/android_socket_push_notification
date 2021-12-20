const express = require("express");

const router = express.Router();

router.get("/",async(req,res,next)=>{
    res.redirect("/");
});
router.get("/:id",async(req,res,next)=>{
    try{
        const {id} = req.params;
        res.render("socket");
    }
    catch (err){
        console.log(err.message);
    }
});
router.post("/",async(req,res,next)=>{
    try{

    }catch (err){
        next(err);
    }
});

module.exports = router;