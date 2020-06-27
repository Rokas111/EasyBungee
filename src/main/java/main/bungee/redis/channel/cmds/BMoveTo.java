package main.bungee.redis.channel.cmds;

import api.server.BungeeServer;
import main.bungee.BungeeMain;
import main.data.redis.channel.references.bungee.MoveTo;
import net.md_5.bungee.api.ProxyServer;

import java.util.UUID;

public class BMoveTo extends MoveTo {
    @Override
    public Boolean run(String[] args) {
        if (new BungeeServer().getPlayers().stream().noneMatch(player -> player.getId().toString().equals(args[0])) || new BungeeServer().getServers().stream().noneMatch(server -> server.getName().equals(args[1]))) {return false;}
        BungeeMain.pl.getProxy().getPlayer(UUID.fromString(args[0])).connect(ProxyServer.getInstance().getServerInfo(args[1]));
        return true;
    }
}
