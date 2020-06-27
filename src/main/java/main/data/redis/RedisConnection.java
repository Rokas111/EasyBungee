package main.data.redis;

import main.data.connection.Connection;
import main.data.exception.ConnectionException;
import main.data.login.RedisLogin;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConnection extends Connection<RedisLogin> {
    private static Object staticLock = new Object();
    private static JedisPool pool;
    private JedisPoolConfig jpc;
    public RedisConnection(RedisLogin login) throws ConnectionException {
        super(login);
        JedisPoolConfig jd = new JedisPoolConfig();
        jd.setMaxWaitMillis(1000L);
        jd.setMinIdle(5);
        jd.setTestOnBorrow(true);
        jd.setMaxTotal(100);
        jd.setBlockWhenExhausted(true);
        this.jpc = jd;
        Jedis jedis = null;
        try {
            jedis = getPool().getResource();
        } catch (Exception e) {
            throw new ConnectionException();
        } finally {
            if (jedis != null) jedis.close();
        }
    }
    public JedisPool getPool() {
        if (pool == null) {
            synchronized(staticLock) {
                if (pool == null) {
                    pool = new JedisPool(this.jpc, getLogin().getAddress(), getLogin().getPort(), 15000,getLogin().getAuth());
                }
            }
        }
        return pool;
    }
    public boolean isConnected() {
        return pool != null;
    }

    public void close() {
        pool.close();
    }
}
