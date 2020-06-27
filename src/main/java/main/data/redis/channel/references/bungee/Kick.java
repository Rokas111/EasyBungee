package main.data.redis.channel.references.bungee;

import main.data.redis.channel.cmd.ChannelCmd;

public class Kick extends ChannelCmd {
    public Kick() {
        super("kick",1);
    }
    public Boolean run(String[] args) {
        return true;
    }
}
