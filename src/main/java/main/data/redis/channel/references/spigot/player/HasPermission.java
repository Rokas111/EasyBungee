package main.data.redis.channel.references.spigot.player;

import main.data.redis.channel.cmd.ChannelCmd;

public class HasPermission extends ChannelCmd {
    public HasPermission() {
        super("hasPermission",2);
    }
    public Boolean run(String[] args) {
        return true;
    }
}
