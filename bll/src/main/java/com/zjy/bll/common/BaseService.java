package com.zjy.bll.common;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.ServiceException;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 公共service
 * @author chahongjing
 * @create 2016-12-10 13:38
 */

public class BaseService<Dao extends BaseDao<T>, T> {

    @Autowired
    private SqlSessionFactoryBean sqlSessionFactory;

    @Autowired
    protected ShiroRealm shiroRealm;

    /**
     * 公共dao
     */
    @Autowired
    protected Dao dao;

    /**
     * 日志
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 添加
     * @param entity
     * @return
     */
    public int add(T entity) {
        logger.info("调用add   方法:{}: {}", entity.getClass().getName(), JSON.toJSONString(entity));
        return dao.add(entity);
    }

    /**
     * 修改
     * @param entity
     * @return
     */
    public int update(T entity) {
        logger.info("调用update   方法:{}: {}", entity.getClass().getName(), JSON.toJSONString(entity));
        return dao.update(entity);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(String id) {
        logger.info("调用delete方法:id: {}", id);
        return dao.delete(id);
    }

    /**
     * 获取详情
     * @param id
     * @return
     */
    public T get(String id) {
        logger.info("调用get方法:id: {}", id);
        return dao.get(id);
    }

    /**
     * 查询简单列表
     * @param entity
     * @return
     */
    public List<? extends T> queryList(T entity) {
        logger.info("调用queryList方法:{}: {}", entity.getClass().getName(), JSON.toJSONString(entity));
        return dao.query(entity);
    }

    /**
     * 查询简单列表（分页）
     * @param entity
     * @return
     */
    public PageInfo<? extends T> queryPageList(PageInfomation pi, T entity) {
        logger.info("调用queryPageList方法:{}: {}", entity.getClass().getName(), JSON.toJSONString(entity));
        PageHelper.startPage(pi.getPageNum(), pi.getPageSize()).setOrderBy(pi.getOrderBy());
        return new PageInfo<>(this.queryList(entity));
    }

    /**
     * 查询复杂列表
     * @param query
     * @return
     */
    public List<? extends T> queryListByMapFilter(Map<String, Object> query) {
        logger.info("调用queryListByMapFilter方法:query: {}", JSON.toJSONString(query));
        return dao.queryByMapFilter(query);
    }

    /**
     * 查询复杂列表（分页）
     * @param pi
     * @param query
     * @return
     */
    public PageInfo<? extends T> queryPageListByMapFilter(PageInfomation pi, Map<String, Object> query) {
        logger.info("调用queryPageListByMapFilter方法:PageInfomation: {}\tquery: {}", JSON.toJSONString(pi), JSON.toJSONString(query));
        PageHelper.startPage(pi.getPageNum(), pi.getPageSize()).setOrderBy(pi.getOrderBy());
        return new PageInfo<>(this.queryListByMapFilter(query));
    }

    private void getSession() {
        try {
            SqlSession sqlSession = sqlSessionFactory.getObject().openSession();
        } catch (Exception e) {
            new ServiceException("");
        }
    }
}
