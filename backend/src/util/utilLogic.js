const crypto = require('crypto');

function strConvertHash(value){
    return crypto.createHash('sha512').update(value).digest('hex');
}
module.exports = {
    strConvertHash,
}
