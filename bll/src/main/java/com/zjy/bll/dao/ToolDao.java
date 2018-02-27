package com.zjy.bll.dao;

import com.zjy.bll.common.BaseDao;
import com.zjy.entities.TableColumnInfo;
import com.zjy.entities.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2018/2/27.
 */
@Repository
public interface ToolDao extends BaseDao<TableColumnInfo> {
    List<TableColumnInfo> getTableInfo(@Param("tableName") String tableName);
}
