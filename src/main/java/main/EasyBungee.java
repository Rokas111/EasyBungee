package main;

import main.data.mongo.MongoConnection;
import main.data.mysql.MySQLConnection;
import main.data.redis.RedisConnection;
import main.data.redis.channel.ServerChannel;

public class EasyBungee {
    public static final String PLUGIN_NAME = "EasyBungee";
    public static final String CONSOLE_PLUGIN_NAME = "&f[&aEasyBungee&f]";
    public static MongoConnection mongoConnection = null;
    public static RedisConnection redisConnection = null;
    public static MySQLConnection mySQLConnection = null;
    public static ServerChannel serverChannel = null;
    public static String name = "";
    public static void closeAll() {
        if (mongoConnection != null) mongoConnection.close();
        if (redisConnection != null) redisConnection.close();
        if (mySQLConnection != null) mySQLConnection.close();
    }
}
