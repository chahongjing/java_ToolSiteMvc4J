package com.zjy.baseframework.interfaces;

/**
 * Created by chahongjing on 2018/1/27.
 */
public interface ICache {
    <T> T get(String key);
     void set(String key, Object value);
    boolean clear(String key);
}
