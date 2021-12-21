function successMsg(code,msg){
    return {
        status:code,
        message:"OK",
        data:msg
    }
}
function failMsg(code,err,msg){
    return {
        status:code,
        message:"ERROR",
        errorCode:err,
        data:msg
    }
}
module.exports = {
    successMsg,
    failMsg
}