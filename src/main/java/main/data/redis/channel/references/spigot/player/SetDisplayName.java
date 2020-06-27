package main.data.redis.channel.references.spigot.player;

import main.data.redis.channel.cmd.ChannelCmd;

public class SetDisplayName extends ChannelCmd {
    public SetDisplayName() {
        super("setDisplayName",2);
    }
    public Boolean run(String[] args) {
        return true;
    }
}
