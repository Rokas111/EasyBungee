package main.data.redis.channel.references.spigot.player;

import main.data.redis.channel.cmd.ChannelCmd;

public class Kill extends ChannelCmd {
    public Kill() {
        super("kill",1);
    }
    public Boolean run(String[] args) {
        return true;
    }
}
