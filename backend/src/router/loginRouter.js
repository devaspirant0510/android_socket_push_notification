const express = require("express");
const {User} = require("../models");
const {strConvertHash} = require("../util/utilLogic");
const jwt = require("jsonwebtoken");
const jwtSetting = require("../config/jwtSecret");
const jwtConfig = require("../util/jwtConfig");
const Result = require("../util/Result");

const router = express.Router();

router.post("/", async (req, res, next) => {
    try {
        const {userId, password} = req.body;
        const hashPwd = strConvertHash(password);
        const users = await User.findOne({where: {userId: userId, password: hashPwd}});
        if (users === null) {
            res.json({message: "error"})
        } else {
            const user2json = users.toJSON();
            const token = jwtConfig.jwtSign({
                name: user2json.name,
                id: user2json.id,
                userId: user2json.userId,
            });
            if (token !== null) {
                user2json['token'] = token
                res.json(Result.successMsg(201, user2json));
            }
            else{
                res.json(Result.failMsg(400,"jwt error",null))
            }
        }
    } catch (err) {
        console.log("err", err);
        next(err);
    }
});

module.exports = router;