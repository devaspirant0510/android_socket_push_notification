const Sequelize = require('sequelize');
module.exports = class Room extends Sequelize.Model{
    static init(sequelize,initOption){
        return super.init({
            id:{
                type:Sequelize.STRING,
                primaryKey:true,
            },
            name:{
                type:Sequelize.STRING,
                allowNull:true,
            }
        },{
            sequelize,
            timestamps:false,
            underscored:false,
            paranoid:false,
            tableName:"rooms",
            modelName:"Room",
            charset:"utf8",
            collate: 'utf8_general_ci',
        });
    }
    static associate(db){
        db.Room.belongsTo(db.User,{foreignKey:"masterId",targetKey:"id"})
        // db.Room.belongsToMany(db.User,{through:"user_room",foreignKey:"room_id"});
        db.Room.hasMany(db.Chat,{foreignKey:"room_id",sourceKey:"id"});
    }
}