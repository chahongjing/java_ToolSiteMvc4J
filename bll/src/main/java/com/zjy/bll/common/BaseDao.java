package com.zjy.bll.common;

import java.util.List;
import java.util.Map;

/**
 * @author chahongjing
 * @create 2016-12-10 13:36
 */
public interface BaseDao<T> {
    T get(String id);

    List<T> query(T entity);

    List<T> queryPage(Map<String, Object> query);

    int add(T entity);

    int delete(String id);

    int update(T entity);
}
