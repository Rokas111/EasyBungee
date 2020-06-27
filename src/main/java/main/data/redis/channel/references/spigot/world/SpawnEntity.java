package main.data.redis.channel.references.spigot.world;

import main.data.redis.channel.cmd.ChannelCmd;

public class SpawnEntity extends ChannelCmd {
    public SpawnEntity() {
        super("spawnEntity",2);
    }
    public Boolean run(String[] args) {
        return true;
    }
}
