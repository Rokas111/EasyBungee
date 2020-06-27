package main.spigot.redis.channel.cmds.world;

import api.spigot.SpigotServer;
import api.spigot.world.SpigotWorld;
import api.spigot.world.lists.SpigotWorlds;
import main.EasyBungee;
import main.data.redis.channel.references.spigot.world.GetWorlds;
import main.spigot.SpigotMain;

import java.util.stream.Collectors;

public class SGetWorlds extends GetWorlds {
    @Override
    public SpigotWorlds run(String[] args) {
        return new SpigotWorlds(SpigotMain.pl.getServer().getWorlds().stream().map(world -> new SpigotWorld(EasyBungee.name,world.getName())).collect(Collectors.toList()));
    }
}
