const Sequelize = require('sequelize');
module.exports = class UserRoom extends Sequelize.Model{
    static init(sequelize,initOption){
        return super.init({
            user_id:{
                type:Sequelize.STRING,
                primaryKey:true,
            },
        },{
            sequelize,
            timestamps:false,
            underscored:false,
            paranoid:false,
            tableName:"user_room",
            modelName:"UserRoom",
            charset:"utf8",
            collate: 'utf8_general_ci',
        });
    }
    static associate(db){
        db.UserRoom.belongsTo(db.User,{foreignKey:"user_id",targetKey:"id"});
        db.UserRoom.belongsTo(db.Room,{foreignKey:"room_id",targetKey:"id"});
    }
}
