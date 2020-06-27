package main.data.redis.channel.references.spigot.block;

import main.data.redis.channel.cmd.ChannelCmd;

public class SetBlock extends ChannelCmd {
    public SetBlock() {
        super("setBlock",1);
    }
    public Boolean run(String[] args) {
        return true;
    }
}
