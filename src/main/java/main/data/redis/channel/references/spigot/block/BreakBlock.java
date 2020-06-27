package main.data.redis.channel.references.spigot.block;

import main.data.redis.channel.cmd.ChannelCmd;

public class BreakBlock extends ChannelCmd {
    public BreakBlock() {
        super("breakBlock",1);
    }
    public Boolean run(String[] args) {
        return true;
    }
}
