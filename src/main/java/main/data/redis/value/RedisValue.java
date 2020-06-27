package main.data.redis.value;

import main.EasyBungee;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public abstract class RedisValue implements IRedisValue {
    private String key;
    public RedisValue(String key) {
        this.key = key;
    }
    public String getKey() {
        return this.key;
    }
    public void update() {
        if (getUpdateValue() != null) {
            try (Jedis jedis = EasyBungee.redisConnection.getPool().getResource()) {
                if (EasyBungee.redisConnection.getLogin().getAuth().isEmpty()) {
                    jedis.auth(EasyBungee.redisConnection.getLogin().getAuth());
                }
                Transaction t = jedis.multi();
                t.set(getKey(), getUpdateValue());
                t.expire(getKey(), 60);
                t.exec();
            }
        }
    }
    /*public String getValue() {
        try (Jedis jedis = EasyBungee.redisConnection.getPool().getResource()) {
            if (EasyBungee.redisConnection.getLogin().getAuth().isEmpty()) {
                jedis.auth(EasyBungee.redisConnection.getLogin().getAuth());
            }
            return jedis.get(getKey());
        }
    }*/
}
