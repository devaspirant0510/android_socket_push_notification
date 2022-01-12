const express = require("express");
const {User} = require("../models");
const {strConvertHash} = require("../util/utilLogic");
const jwt = require("jsonwebtoken");
const jwtSetting = require("../config/jwtSecret");
const jwtConfig = require("../util/jwtConfig");
const Result = require("../util/Result");
const {failMsg} = require("../util/Result");

const router = express.Router();

router.post("/", async (req, res, next) => {
    try {
        const {userId, password} = req.body;
        const hashPwd = strConvertHash(password);
        const users = await User.findOne({where: {userId: userId, password: hashPwd}});
        if (users === null) {
            res.json(Result.failMsg(401,"해당 아이디 또는 비밀번호가 존재하지 않습니다."));
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
                res.json(Result.failMsg(400,"jwt error"))
            }
        }
    } catch (err) {
        console.log("err", err);
        next(err.toString());
    }
});

module.exports = router;