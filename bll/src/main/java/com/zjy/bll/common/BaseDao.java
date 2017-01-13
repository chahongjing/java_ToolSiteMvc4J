package com.zjy.bll.common;

import java.util.HashMap;
import java.util.List;

/**
 * @author chahongjing
 * @create 2016-12-10 13:36
 */
public interface BaseDao<T> {
     T get(String id);

     List<T> query(T entity);

     List<T> queryPage(HashMap<String, Object> query);

     int add(T entity);

     int delete(String id);

     int update(T entity);
}
