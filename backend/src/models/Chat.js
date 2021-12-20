const Sequelize = require('sequelize');
module.exports = class Chat extends Sequelize.Model{
    static init(sequelize,initOption){
        return super.init({
            id:{
                type:Sequelize.STRING,
                primaryKey:true,
            },
            message:{
                type:Sequelize.STRING,
            }
        },{
            sequelize,
            timestamps:false,
            underscored:false,
            paranoid:false,
            tableName:"chats",
            modelName:"Chat",
            charset:"utf8",
            collate: 'utf8_general_ci',
        })
    }
    static associate(db){
        db.Chat.belongsTo(db.User,{foreignKey:"user_id",targetKey:"id"});
        db.Chat.belongsTo(db.Room,{foreignKey:"room_id",targetKey:"id"});
    }
}