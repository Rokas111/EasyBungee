package main.bungee.redis.channel.cmds;

import api.server.BungeeServer;
import main.bungee.BungeeMain;
import main.bungee.redis.channel.BungeeChannel;
import main.data.redis.channel.references.bungee.Kick;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BKick extends Kick {
    @Override
    public Boolean run(String[] args) {
        if (new BungeeServer().getPlayers().stream().noneMatch(player -> player.getId().toString().equals(args[0]))) return false;
        if (args.length == 1) {
            BungeeMain.pl.getProxy().getPlayer(UUID.fromString(args[0])).disconnect();
            return true;
        }
        BungeeMain.pl.getProxy().getPlayer(args[0]).disconnect(String.join(" ", Stream.of(args).collect(Collectors.toList()).subList(1,args.length)));
        return true;
    }
}
