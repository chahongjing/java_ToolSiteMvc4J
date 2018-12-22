package com.zjy.baseframework;

import com.zjy.baseframework.interfaces.ICache;
import org.apache.shiro.codec.Base64;

import java.io.*;

public class JedisCacheHelper implements ICache {
    @Override
    public Object get(String key) {
        String val = JedisHelper.get(key);
        Object deserialize = deserialize(val);
        return deserialize;
    }
    @Override
    public <T> T get(String key, Class<T> clazz) {
        String val = JedisHelper.get(key);
        Object deserialize = deserialize(val);
        return (T) deserialize;
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
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bis = new ByteArrayInputStream(Base64.decode(str));
            ois = new ObjectInputStream(bis);
            return ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException("deserialize session error", e);
        } finally {
            try {
                ois.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private static String serialize(Object obj) {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            return Base64.encodeToString(bos.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("serialize session error", e);
        } finally {
            try {
                oos.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}