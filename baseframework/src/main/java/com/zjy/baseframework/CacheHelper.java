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
    public <T> T get(String key) {
        return (T)map.get(key);
    }

    @Override
    public void set(String key, Object value){
        map.put(key, value);
    }

    @Override
    public boolean clear(String key){
        map.remove(key);
        return true;
    }
}
