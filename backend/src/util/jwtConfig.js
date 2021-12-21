const jwt = require("jsonwebtoken");
const jwtSecret = require("../config/jwtSecret");
const {failMsg } = require("../util/Result");

function jwtSign(payload) {
    const token = jwt.sign(payload, jwtSecret.secretKey, jwtSecret.option);
    return token;
}

async function jwtVerify(token, secretKey) {
    try {
        const decode = jwt.verify(token, secretKey);
        return decode;
    } catch (err) {
        if (err.message === 'jwt expired') {
            console.log(failMsg(400, "jwt expired", null));
        } else if (err.message === 'invalid token') {
            console.log(failMsg(400, 'invalid token', null));
        } else {
            console.log(failMsg(400, "invalid token", null));
        }
        return null;
    }
}

module.exports = {
    jwtSign,
    jwtVerify
}