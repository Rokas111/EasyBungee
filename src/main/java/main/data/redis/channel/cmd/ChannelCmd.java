package main.data.redis.channel.cmd;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import main.data.redis.channel.cmd.IChannelCmd;

@RequiredArgsConstructor
public abstract class ChannelCmd implements IChannelCmd<Boolean> {
    @NonNull @Getter private String alias;
    private int minArgumentSize = 0;
    public ChannelCmd(String alias,int minArgumentSize) {
        this.alias = alias;
        this.minArgumentSize = minArgumentSize;
    }
    public Boolean execute(String[] args) {
        if (minArgumentSize > args.length) return false;
        return run(args);
    }
}
