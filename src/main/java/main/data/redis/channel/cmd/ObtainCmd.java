package main.data.redis.channel.cmd;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ObtainCmd<T> implements IChannelCmd<T> {
    @NonNull @Getter private String alias;
    private int minArgumentSize = 0;
    public ObtainCmd(String alias,int minArgumentSize) {
        this.alias = alias;
        this.minArgumentSize = minArgumentSize;
    }
    public T execute(String[] args) {
        if (minArgumentSize > args.length) return null;
        return run(args);
    }
}
