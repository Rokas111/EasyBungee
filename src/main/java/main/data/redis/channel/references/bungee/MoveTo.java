package main.data.redis.channel.references.bungee;

import main.data.redis.channel.cmd.ChannelCmd;

public class MoveTo extends ChannelCmd {
    public MoveTo() {
        super("moveto",2);
    }
    public Boolean run(String[] args) {
        return true;
    }
}
