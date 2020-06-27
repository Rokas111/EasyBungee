package main.data.redis.channel.references.spigot.block;

import api.spigot.world.SpigotBlock;
import main.data.redis.channel.cmd.ObtainCmd;

public class GetBlock extends ObtainCmd<SpigotBlock> {
    public GetBlock() {
        super("getBlock",1);
    }
    public SpigotBlock run(String[] args) {
        return null;
    }
}
