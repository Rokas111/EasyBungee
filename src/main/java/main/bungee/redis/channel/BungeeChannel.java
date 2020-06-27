package main.bungee.redis.channel;

import api.server.BungeeServer;
import main.bungee.redis.channel.cmds.BKick;
import main.bungee.redis.channel.cmds.BMoveTo;
import main.bungee.redis.channel.cmds.BSendToConsole;
import main.data.redis.channel.ServerChannel;
import main.data.redis.channel.cmd.IChannelCmd;
import java.util.Arrays;
import java.util.List;

public class BungeeChannel extends ServerChannel {
    public BungeeChannel() {
        super();
    }
    public List<IChannelCmd> getCommands() {
        return Arrays.asList(new BKick(),new BMoveTo(),new BSendToConsole());
    }
}
