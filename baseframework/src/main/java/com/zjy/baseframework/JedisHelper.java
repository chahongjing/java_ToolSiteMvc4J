package com.zjy.baseframework;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

/**
 * @author chahongjing
 * @create 2016-11-27 15:00
 */
public class JedisHelper {

    private JedisHelper() {
    }

    private static JedisPool jedisPool = SpringContextHolder.getBean("jedisPool");

    public static String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        return jedis.set(key, value);
    }

    public static String get(String key) {
        Jedis jedis = jedisPool.getResource();
        return jedis.get(key);
    }

    public static String getSet(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        return jedis.getSet(key, value);
    }

    /**
     * 分布式加锁
     *
     * @param key 如库存商品id
     * @param value 如当前请求requestid，防止非当前操作用户释放锁
     * @param millisecond
     * @return
     */
    public static boolean setLock(String key, String value, int millisecond) {
        Jedis jedis = jedisPool.getResource();
        SetParams sp = new SetParams();
        String result = jedis.set(key, value, sp.nx().px(millisecond));
        // String result = jedis.set(key, value, "NX", "PX", millisecond);
        return "OK".equals(result);
    }

    /**
     * 分布式解锁
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean releaseLock(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(key), Collections.singletonList(value));
        return "1".equals(String.valueOf(result));
    }

    public static Long delete(String key) {
        Jedis jedis = jedisPool.getResource();
        return jedis.del(key);
    }

    public static Boolean exists(String key) {
        Jedis jedis = jedisPool.getResource();
        return jedis.exists(key);
    }

    public static String rename(String oldkey, String newkey) {
        Jedis jedis = jedisPool.getResource();
        return jedis.rename(oldkey, newkey);
    }

    public static Long expire(String key, int seconds) {
        Jedis jedis = jedisPool.getResource();
        return jedis.expire(key, seconds);
    }
}
