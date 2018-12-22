package com.zjy.baseframework;

import com.zjy.baseframework.interfaces.ICache;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chahongjing on 2017/6/10.
 */
@Component
public class CacheHelper implements ICache {
    private ConcurrentHashMap<String, Object> map = new ConcurrentHashMap();

    @Override
    public <T> T get(String key, Class<T> T) {
        return (T)map.get(key);
    }
    @Override
    public Object get(String key) {
        return map.get(key);
    }

    @Override
    public <T> void set(String key, T value){
        map.put(key, value);
    }

    @Override
    public boolean delete(String key){
        map.remove(key);
        return true;
    }
}
