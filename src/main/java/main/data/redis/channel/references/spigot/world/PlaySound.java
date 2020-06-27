package main.data.redis.channel.references.spigot.world;

import main.data.redis.channel.cmd.ChannelCmd;

public class PlaySound extends ChannelCmd {
    public PlaySound() {
        super("playSound",2);
    }
    public Boolean run(String[] args) {
        return true;
    }
}
