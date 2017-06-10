package com.zjy.baseframework;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chahongjing on 2017/6/10.
 */
public class CacheHelper {
    private static ConcurrentHashMap<String, Object> map = new ConcurrentHashMap();
    public static Object get(String key) {
        return map.get(key);
    }
    public static void set(String key, Object value){
        map.put(key, value);
    }
    public static boolean clear(String key){
        map.remove(key);
        return true;
    }
}
