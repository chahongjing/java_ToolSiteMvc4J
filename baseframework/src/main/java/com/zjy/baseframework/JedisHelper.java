package com.zjy.baseframework;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author chahongjing
 * @create 2016-11-27 15:00
 */
public class JedisHelper {
    private static JedisPool jedisPool = SpringContextHolder.getBean("jedisPool");

    public static String get(String key) {
        Jedis jedis = jedisPool.getResource();
        return jedis.get(key);
    }
}
