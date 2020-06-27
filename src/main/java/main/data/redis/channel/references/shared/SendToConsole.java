package main.data.redis.channel.references.shared;

import main.data.redis.channel.cmd.ChannelCmd;

public class SendToConsole extends ChannelCmd {
    public SendToConsole() {
        super("sendtoconsole",1);
    }
    public Boolean run(String[] args) {
        return true;
    }
}
