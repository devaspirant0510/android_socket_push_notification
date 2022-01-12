function successMsg(code,msg){
    return {
        status:code,
        message:"OK",
        data:msg
    }
}
function failMsg(code,msg){
    return {
        status:code,
        message:"ERROR",
        data:msg
    }
}
module.exports = {
    successMsg,
    failMsg
}