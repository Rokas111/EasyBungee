package main.data.redis.channel.references.spigot.player;

import main.data.redis.channel.cmd.ChannelCmd;

public class SetExpLevel extends ChannelCmd {
    public SetExpLevel() {
        super("setExpLevel",2);
    }
    public Boolean run(String[] args) {
        return true;
    }
}
