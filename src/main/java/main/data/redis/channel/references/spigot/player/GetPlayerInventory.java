package main.data.redis.channel.references.spigot.player;

import api.spigot.world.lists.SpigotInventory;
import main.data.redis.channel.cmd.ObtainCmd;

public class GetPlayerInventory extends ObtainCmd<SpigotInventory> {
    public GetPlayerInventory() {
        super("getPlayerInventory",1);
    }
    public SpigotInventory run(String[] args) {
        return null;
    }
}
