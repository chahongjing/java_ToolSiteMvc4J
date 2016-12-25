package com.zjy.bll.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chahongjing
 * @create 2016-12-10 13:38
 */

public class BaseService<Dao extends BaseDao<T>, T> {

    @Autowired
    protected Dao dao;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public T get(String id) {
        logger.info("调用get方法:id: {}", id);
        return (T)dao.get(id);
    }
}
