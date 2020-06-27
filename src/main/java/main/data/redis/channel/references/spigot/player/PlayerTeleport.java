package main.data.redis.channel.references.spigot.player;

import main.data.redis.channel.cmd.ChannelCmd;

public class PlayerTeleport extends ChannelCmd {
    public PlayerTeleport() {
        super("teleport",2);
    }
    public Boolean run(String[] args) {
        return true;
    }
}
