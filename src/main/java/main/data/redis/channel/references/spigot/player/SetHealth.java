package main.data.redis.channel.references.spigot.player;

import main.data.redis.channel.cmd.ChannelCmd;

public class SetHealth extends ChannelCmd {
    public SetHealth() {
        super("setHealth",2);
    }
    public Boolean run(String[] args) {
        return true;
    }
}
