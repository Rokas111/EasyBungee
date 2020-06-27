package main.data.redis.value.section;

import lombok.Getter;
import main.EasyBungee;
import main.data.redis.value.IRedisValue;
import main.data.redis.value.RedisValue;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RedisSection<T extends IRedisValue> extends RedisValue{
    @Getter private final List<T> values = new ArrayList<>();
    public RedisSection(String name) {
        super(name);
    }
    public RedisSection add(T value) {
        values.add(value);
        return this;
    }
    public RedisSection remove(T value) {
        values.remove(value);
        return this;
    }
    public String getUpdateValue() {
        return "";
    }
    public HashMap<String,String> getKeys() {
        try (Jedis jedis = EasyBungee.redisConnection.getPool().getResource()) {
            if (!EasyBungee.redisConnection.getLogin().getAuth().isEmpty()) {
                jedis.auth(EasyBungee.redisConnection.getLogin().getAuth());
            }
            return (HashMap<String, String>) jedis.hgetAll(getKey());
        }
    }
    @Override
    public void update() {
            try (Jedis jedis = EasyBungee.redisConnection.getPool().getResource()) {
                if (!EasyBungee.redisConnection.getLogin().getAuth().isEmpty()) {
                    jedis.auth(EasyBungee.redisConnection.getLogin().getAuth());
                }
                Transaction t = jedis.multi();
                t.del(getKey());
                t.exec();
                values.forEach(v -> {
                    if (v != null && v.getKey() != null) {
                        Transaction ts = jedis.multi();
                        if (v.getUpdateValue() == null) {
                            ts.hdel(getKey(), v.getKey());
                            ts.exec();
                            return;
                        }
                        if (getKey().isEmpty()) {
                            v.update();
                        } else {
                            ts.hset(getKey(), v.getKey(), v.getUpdateValue());
                            ts.expire(getKey(), 30);
                        }
                        ts.exec();
                    }
                });
            }
    }
    public String getValue(T value) {
        try (Jedis jedis = EasyBungee.redisConnection.getPool().getResource()) {
            if (!EasyBungee.redisConnection.getLogin().getAuth().isEmpty()) {
                jedis.auth(EasyBungee.redisConnection.getLogin().getAuth());
            }
            return jedis.hget(getKey(),value.getKey());
        }
    }
}
