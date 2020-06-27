package main.data.redis.channel.message.callbacks;

public interface onComplete<T> {
    void onComplete(T success);
}
