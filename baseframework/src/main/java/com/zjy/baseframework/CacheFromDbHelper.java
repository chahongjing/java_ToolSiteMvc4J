package com.zjy.baseframework;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.zjy.baseframework.interfaces.ICache;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CacheFromDbHelper implements ICache {
    private final LoadingCache<String, String> memCache = CacheBuilder.
            newBuilder().
            // 指定时间未写入，则回收，下次从loading中取，同步
            expireAfterWrite(30, TimeUnit.MINUTES).
            // 指定时间未读写，则回收，下次从loading中取，同步
            expireAfterAccess(30, TimeUnit.MINUTES).
            // 写入后指定时间到达后刷新缓存，期间会返回旧值。
            // 当缓存项上一次更新操作之后的多久会被刷新。第一个请求进来，执行load把数据加载到内存中（同步过程）
            // 指定的过期时间内比如10秒，都是从cache里读取数据。过了10秒后，没有请求进来，不会移除key。
            // 再有请求过来，才则执行reload，在后台异步刷新的过程中，如果当前是刷新状态，访问到的是旧值。
            // 刷新过程中只有一个线程在执行刷新操作，不会出现多个线程同时刷新同一个key的缓存。
            // 在吞吐量很低的情况下，如很长一段时间内没有请求，再次请求有可能会得到一个旧值（这个旧值可能来自于很长时间之前），这将会引发问题。
            // （可以使用expireAfterWrite和refreshAfterWrite搭配使用解决这个问题）
            refreshAfterWrite(30, TimeUnit.MINUTES)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) {
                    // get from db
                    return "";
                }
            });

    @Override
    public String get(String key) {
        try {
            return memCache.get(key);
        } catch (ExecutionException e) {
            log.error("get  from db cache error!", e);
        }
        return "";
    }

    @Override
    public <T> T get(String key, Class<T> clazz) {
        String v = get(key);
        if(v == null || "".equals(v.trim())) {
            return null;
        }
        return JSON.parseObject(v, clazz);
    }

    @Override
    public <T> void set(String key, T value) {

    }

    @Override
    public boolean delete(String key) {
        return false;
    }

    @Override
    public Object hGet(String key, String field) {
        return null;
    }

    @Override
    public long hSet(String key, String field, String value) {
        return 0;
    }

    @Override
    public long hDelete(String key) {
        return 0;
    }

    @Override
    public long hDelete(String key, String field) {
        return 0;
    }
}
