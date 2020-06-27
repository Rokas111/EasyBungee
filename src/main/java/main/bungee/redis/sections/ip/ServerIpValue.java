package main.bungee.redis.sections.ip;

import main.bungee.BungeeMain;
import main.data.redis.value.RedisValue;
import net.md_5.bungee.api.config.ServerInfo;

public class ServerIpValue extends RedisValue {
    public ServerIpValue(ServerInfo server) {
        super(server.getAddress().getHostName() + ":" + server.getAddress().getPort());
    }
    @Override
    public String getUpdateValue() {
        return BungeeMain.pl.getProxy().getServers().values().stream().filter(server -> (server.getAddress().getHostName() + ":" + server.getAddress().getPort()).equals(getKey())).findFirst().orElse(null).getName();
    }
}
