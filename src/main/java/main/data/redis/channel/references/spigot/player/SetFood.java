package main.data.redis.channel.references.spigot.player;

import main.data.redis.channel.cmd.ChannelCmd;

public class SetFood extends ChannelCmd {
    public SetFood() {
        super("setFood",2);
    }
    public Boolean run(String[] args) {
        return true;
    }
}
