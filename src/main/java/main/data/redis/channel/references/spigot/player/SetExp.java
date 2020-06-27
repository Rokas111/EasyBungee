package main.data.redis.channel.references.spigot.player;

import main.data.redis.channel.cmd.ChannelCmd;

public class SetExp extends ChannelCmd {
    public SetExp() {
        super("setExp",2);
    }
    public Boolean run(String[] args) {
        return true;
    }
}
