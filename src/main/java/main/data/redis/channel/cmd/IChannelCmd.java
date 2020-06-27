package main.data.redis.channel.cmd;

public interface IChannelCmd<T> {
    String getAlias();
    T execute(String[] args);
    T run(String[] args);
}
