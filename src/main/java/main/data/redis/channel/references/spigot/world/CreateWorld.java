package main.data.redis.channel.references.spigot.world;

import main.data.redis.channel.cmd.ChannelCmd;

public class CreateWorld extends ChannelCmd {
    public CreateWorld() {
        super("createWorld",1);
    }
    public Boolean run(String[] args) {
        return true;
    }
}
