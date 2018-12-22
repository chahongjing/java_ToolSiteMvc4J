package com.zjy.baseframework.interfaces;

/**
 * Created by chahongjing on 2018/1/27.
 */
public interface ICache {
    Object get(String key);
    <T> T get(String key, Class<T> clazz);
    <T> void set(String key, T value);
    boolean delete(String key);
}
