package main.data.redis.channel.references.spigot.player;

import main.data.redis.channel.cmd.ChannelCmd;

public class OpenInventory extends ChannelCmd {
    public OpenInventory() {
        super("openInventory",2);
    }
    public Boolean run(String[] args) {
        return true;
    }
}
