package main.data.redis.value;

public interface IRedisValue {
    String getUpdateValue();
    //String getValue();
    String getKey();
    void update();
}
