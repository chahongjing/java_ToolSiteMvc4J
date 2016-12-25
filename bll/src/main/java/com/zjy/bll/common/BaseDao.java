package com.zjy.bll.common;

import java.util.UUID;

/**
 * @author chahongjing
 * @create 2016-12-10 13:36
 */
public interface BaseDao<T> {
     T get(String id);
}
