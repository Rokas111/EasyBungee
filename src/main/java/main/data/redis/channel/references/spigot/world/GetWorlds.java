package main.data.redis.channel.references.spigot.world;

import api.spigot.world.lists.SpigotWorlds;
import main.data.redis.channel.cmd.ObtainCmd;

public class GetWorlds extends ObtainCmd<SpigotWorlds> {
    public GetWorlds() {
        super("getWorlds",0);
    }
    public SpigotWorlds run(String[] args) {
        return null;
    }
}
