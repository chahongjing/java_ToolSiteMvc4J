package com.zjy.baseframework;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author chahongjing
 * @create 2016-11-27 15:00
 */
public class JedisHelper {
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
