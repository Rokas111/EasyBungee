package main.bungee.redis.sections.ip;

import main.bungee.BungeeMain;
import main.data.redis.value.section.RedisSection;

public class ServerIpSection extends RedisSection {
    public ServerIpSection() {
        super("servers");
        BungeeMain.pl.getProxy().getServers().values().forEach(server -> add(new ServerIpValue(server)));
    }

}
