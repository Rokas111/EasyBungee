package main.data.redis.channel.references.spigot.player;

import main.data.redis.channel.cmd.ChannelCmd;

public class PlaySoundPlayer extends ChannelCmd {
    public PlaySoundPlayer() {
        super("playSoundPlayer",2);
    }
    public Boolean run(String[] args) {
        return true;
    }
}
