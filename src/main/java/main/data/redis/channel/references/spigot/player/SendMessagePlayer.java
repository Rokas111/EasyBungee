package main.data.redis.channel.references.spigot.player;

import main.data.redis.channel.cmd.ChannelCmd;

public class SendMessagePlayer extends ChannelCmd {
    public SendMessagePlayer() {
        super("sendMessage",2);
    }
    public Boolean run(String[] args) {
        return true;
    }
}
