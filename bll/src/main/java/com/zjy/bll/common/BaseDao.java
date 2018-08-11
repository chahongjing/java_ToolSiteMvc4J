package com.zjy.bll.common;

import java.util.List;
import java.util.Map;

/**
 * 公共dao
 * @author chahongjing
 * @create 2016-12-10 13:36
 */
public interface BaseDao<T> {
    /**
     * 获取详情
     * @param id
     * @return
     */
    T get(String id);

    /**
     * 查询简单列表
     * @param entity
     * @return
     */
    List<? extends T> query(T entity);

    /**
     * 查询复杂列表
     * @param query
     * @return
     */
    List<? extends T> queryByMapFilter(Map<String, Object> query);

    /**
     * 添加
     * @param entity
     * @return
     */
    int add(T entity);

    /**
     * 修改
     * @param entity
     * @return
     */
    int update(T entity);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(String id);
}
