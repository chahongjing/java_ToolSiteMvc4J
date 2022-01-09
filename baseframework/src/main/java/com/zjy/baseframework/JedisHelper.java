package com.zjy.baseframework;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.*;

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

    public static String hGet(String key, String field) {
        Jedis jedis = jedisPool.getResource();
        return jedis.hget(key, field);
    }

    public static String getSet(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        return jedis.getSet(key, value);
    }

    public static long hSet(String key, String field, String value) {
        Jedis jedis = jedisPool.getResource();
        return jedis.hset(key, field, value);
    }

    public static long hDelete(String key, String field) {
        Jedis jedis = jedisPool.getResource();
        return jedis.hdel(key, field);
    }

    public static Map<String, Object> getAll(String key) {
        Map<String, Object> result = new HashMap<>();
        Jedis jedis = jedisPool.getResource();
        String[] keys = (String[])jedis.keys(key).toArray();
        List<String> mget = jedis.mget(keys);
        for (int i = 0; i < keys.length; i++) {
            result.put(keys[i], mget.get(i));
        }
        return result;
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

    /**
     * 漏桶算法
     * @param key key
     * @param milesecon 毫秒窗口
     * @param count 数量限制
     * @return
     */
    public static boolean limiter(String key, int milesecon, int count) {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        // 将现在的时间滚动固定时长,转换为Date类型赋值
        calendar.add(Calendar.MILLISECOND, -milesecon);
        // 转换为Date类型再赋值
        Date before = calendar.getTime();

        /**
         * 此段并发，加锁或使用lua脚本
         */
        Jedis jedis = jedisPool.getResource();
        jedis.zremrangeByScore(key, 0, before.getTime());
        Set<String> strings = jedis.zrangeByScore(key, 0, now.getTime());
        if(strings.size() >= count) {
            return false;
        } else {
            jedis.zadd(key, now.getTime(), UUID.randomUUID().toString());
        }
        return true;
    }
}
