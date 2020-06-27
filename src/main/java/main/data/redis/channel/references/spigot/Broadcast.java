package main.data.redis.channel.references.spigot;

import main.data.redis.channel.cmd.ChannelCmd;

public class Broadcast extends ChannelCmd {
    public Broadcast() {
        super("broadcast",1);
    }
    public Boolean run(String[] args) {
        return true;
    }
}
