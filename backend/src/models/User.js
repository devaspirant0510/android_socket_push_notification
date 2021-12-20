const Sequelize = require('sequelize');
module.exports = class User extends Sequelize.Model{
    static init(sequelize,initOption){
        return super.init({
            id:{
                type:Sequelize.STRING,
                primaryKey:true,
            },
            name:{
                type:Sequelize.STRING,
                allowNull:false,
            },
            userId:{
                type:Sequelize.STRING,
                allowNull:false,
                unique:true,
            },
            password:{
                type:Sequelize.STRING,
                allowNull:true,
            },
            socketId:{
                type:Sequelize.STRING,
                allowNull:true
            }
        },{
            sequelize,
            timestamps:false,
            underscored:false,
            paranoid:false,
            tableName:"users",
            modelName:"User",
            charset:"utf8",
            collate: 'utf8_general_ci',
        })
    }
    static associate(db){
        db.User.hasMany(db.Room,{foreignKey:"masterId",sourceKey:"id"});
        // db.User.belongsToMany(db.Room,{through:"user_room",foreignKey:"user_id"});
        db.User.hasMany(db.Chat,{foreignKey:"user_id",sourceKey:"id"})

    }
}