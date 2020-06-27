package main.data.redis.value.list;

import main.EasyBungee;
import main.data.redis.value.RedisValue;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RedisList extends RedisValue {
    private final Set<String> values = new HashSet<>();
    public RedisList(String name) {
        super(name);
    }
    public void add(String value) {
        values.add(value);
    }
    public void remove(String value) {
        values.remove(value);
    }
    public List<String> getElements() {
        try (Jedis jedis = EasyBungee.redisConnection.getPool().getResource()) {
            if (!EasyBungee.redisConnection.getLogin().getAuth().isEmpty()) {
                jedis.auth(EasyBungee.redisConnection.getLogin().getAuth());
            }
            if (!jedis.exists(getKey())) return new ArrayList<>();
            return new ArrayList<>(jedis.smembers(getKey()));
        }
    }
    public String getUpdateValue() {
        return "";
    }
    @Override
    public void update() {
        try (Jedis jedis = EasyBungee.redisConnection.getPool().getResource()) {
            if (!EasyBungee.redisConnection.getLogin().getAuth().isEmpty()) {
                jedis.auth(EasyBungee.redisConnection.getLogin().getAuth());
            }
            values.stream().filter(value -> !jedis.sismember(getKey(),value)).forEach(value -> jedis.sadd(getKey(),value));
            jedis.expire(getKey(),30);
        }
    }
}
