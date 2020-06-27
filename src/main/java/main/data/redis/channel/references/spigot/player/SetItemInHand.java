package main.data.redis.channel.references.spigot.player;

import main.data.redis.channel.cmd.ChannelCmd;

public class SetItemInHand extends ChannelCmd {
    public SetItemInHand() {
        super("setItemInHand",2);
    }
    public Boolean run(String[] args) {
        return true;
    }
}
