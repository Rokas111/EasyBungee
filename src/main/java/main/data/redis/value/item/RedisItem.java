package main.data.redis.value.item;

import lombok.Getter;

import java.util.HashMap;

public abstract class RedisItem implements IRedisItem {
    @Getter private String id;
    public RedisItem(String id) {
        this.id = id;
    }
    public HashMap<String,String> toRedis() {
        HashMap<String,String> keys = new HashMap<>();
        getRedisKeys().forEach((key,value) -> keys.put(id + "." + key,value));
        return keys;
    }
}
