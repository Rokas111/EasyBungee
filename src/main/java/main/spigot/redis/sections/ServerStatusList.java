package main.spigot.redis.sections;

import main.data.redis.value.list.RedisList;

public class ServerStatusList extends RedisList {
    public ServerStatusList(String ip) {
        super("serverstatus");
        add(ip);
    }
}
