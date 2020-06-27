package main.data.redis.channel.references.spigot.player;

import api.spigot.world.item.SpigotItem;
import main.data.redis.channel.cmd.ObtainCmd;

public class GetPlayerItemInHand extends ObtainCmd<SpigotItem> {
    public GetPlayerItemInHand() {
        super("getPlayerItemInHand",1);
    }
    public SpigotItem run(String[] args) {
        return null;
    }
}
