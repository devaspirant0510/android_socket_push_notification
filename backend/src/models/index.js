const Sequelize = require('sequelize');
const env = process.env.NODE_ENV || 'development';
const config = require(__dirname + '/../config/config.json')[env];
const User = require("./User");
const Room = require("./Room");
const Chat = require("./Chat");
const UserRoom = require("./UserRoom");

const db = {};

let sequelize;
sequelize = new Sequelize(config.database, config.username, config.password, config);

db.sequelize = sequelize;
db.DataType = Sequelize;

db.User = User;
db.Room = Room;
db.Chat = Chat;
db.UserRoom = UserRoom;

User.init(sequelize);
Room.init(sequelize);
Chat.init(sequelize);
UserRoom.init(sequelize);

User.associate(db);
Room.associate(db);
Chat.associate(db)
UserRoom.associate(db);

module.exports = db;