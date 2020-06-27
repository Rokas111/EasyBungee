package main.data.redis.channel.references.spigot;

import main.data.redis.channel.cmd.ChannelCmd;

public class ExecuteCommand extends ChannelCmd {
    public ExecuteCommand() {
        super("executeCommand",1);
    }
    public Boolean run(String[] args) {
        return true;
    }
}
