package com.zjy.bll.common;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

/**
 * @author chahongjing
 * @create 2016-12-10 13:38
 */

public class BaseService<Dao extends BaseDao<T>, T> {

    @Autowired
    protected Dao dao;

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public T get(String id) {
        logger.info("调用get方法:id: {}", id);
        return dao.get(id);
    }

    public List<T> query(T entity) {
        logger.info("调用query方法:{}: {}", entity.getClass().getName(), JSON.toJSONString(entity));
        return dao.query(entity);
    }

    public PageInfo<T> queryPage(PageInfomation pi, HashMap<String, Object> query) {
        logger.info("调用queryPage方法:PageInfomation: {}\tquery: {}", JSON.toJSONString(pi), JSON.toJSONString(query));
        PageHelper.startPage(pi.getPageNum(), pi.getPageSize()).setOrderBy(pi.getOrderBy());
        return new PageInfo<>(dao.queryPage(query));
    }

    public int delete(String id) {
        logger.info("调用delete方法:id: {}", id);
        return dao.delete(id);
    }

    public int update(T entity) {
        logger.info("调用update   方法:{}: {}", entity.getClass().getName(), JSON.toJSONString(entity));
        return dao.update(entity);
    }
}
