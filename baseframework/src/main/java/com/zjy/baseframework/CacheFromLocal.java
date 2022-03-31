package com.zjy.baseframework;

import com.zjy.baseframework.interfaces.ICache;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chahongjing on 2017/6/10.
 */
@Component
public class CacheFromLocal implements ICache {
    private ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();

    @Override
    public <T> T get(String key, Class<T> clazz) {
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

    @Override
    public Map<String, Object> getAll(String key) {
        Map<String, Object> result = new HashMap<>();
        map.entrySet().stream().filter(entry -> entry.getKey().startsWith(key)).forEach(entry -> result.put(entry.getKey(), entry.getValue()));
        return result;
    }

    public Object hGet(String key, String field) {
        return map.get(getHKey(key, field));
    }

    public long hSet(String key, String field, String value) {
        map.put(getHKey(key, field), value);
        return 0;
    }

    public long hDelete(String key) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if(entry.getKey().startsWith(key)) {
                map.remove(getHKey(key, entry.getKey()));
            }
        }
        return 0;
    }

    public long hDelete(String key, String field) {
        map.remove(getHKey(key, field));
        return 0;
    }

    private String getHKey(String key, String field) {
        return String.format("%s:%s", key, field);
    }
}
