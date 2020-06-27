package main.data.redis.channel.references.spigot.player;

import api.spigot.world.SpigotLocation;
import main.data.redis.channel.cmd.ObtainCmd;

public class GetLocation extends ObtainCmd<SpigotLocation> {
    public GetLocation() {
        super("getLocation",1);
    }
    public SpigotLocation run(String[] args) {
        return null;
    }
}
