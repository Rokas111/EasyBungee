package main.data.redis.channel.references.spigot.world;

import main.data.redis.channel.cmd.ChannelCmd;

public class DropItem extends ChannelCmd {
    public DropItem() {
        super("dropItem",2);
    }
    public Boolean run(String[] args) {
        return true;
    }
}
