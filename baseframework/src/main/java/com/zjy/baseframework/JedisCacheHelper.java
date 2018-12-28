package com.zjy.baseframework;

import com.zjy.baseframework.interfaces.ICache;
import org.apache.shiro.codec.Base64;

import java.io.*;

public class JedisCacheHelper implements ICache {
    @Override
    public Object get(String key) {
        String val = JedisHelper.get(key);
        return deserialize(val);
    }
    @Override
    public <T> T get(String key, Class<T> clazz) {
        String val = JedisHelper.get(key);
        return  (T)deserialize(val);
    }

    @Override
    public <T> void set(String key, T value) {
        String val = serialize(value);
        JedisHelper.set(key, val);
    }

    @Override
    public boolean delete(String key) {
        JedisHelper.delete(key);
        return true;
    }

    private static Object deserialize(String str) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decode(str));
             ObjectInputStream ois = new ObjectInputStream(bis);){
            return ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException("deserialize session error", e);
        }
    }

    private static String serialize(Object obj) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos);){
            oos.writeObject(obj);
            return Base64.encodeToString(bos.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("serialize session error", e);
        }
    }
}