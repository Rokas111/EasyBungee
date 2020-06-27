package main.data.redis.channel.references.spigot.player;

import main.data.redis.channel.cmd.ChannelCmd;

public class SetGameMode extends ChannelCmd {
    public SetGameMode() {
        super("setGameMode",2);
    }
    public Boolean run(String[] args) {
        return true;
    }
}
