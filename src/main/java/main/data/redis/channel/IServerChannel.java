package main.data.redis.channel;

import main.data.redis.channel.cmd.IChannelCmd;

import java.util.List;

public interface IServerChannel {
    List<IChannelCmd> getCommands();
}
