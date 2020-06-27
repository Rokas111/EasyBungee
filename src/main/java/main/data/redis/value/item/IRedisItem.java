package main.data.redis.value.item;

import java.util.HashMap;

public interface IRedisItem {
    HashMap<String,String> getRedisKeys();
    String getId();
}
