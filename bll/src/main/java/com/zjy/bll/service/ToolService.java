package com.zjy.bll.service;

import com.zjy.baseframework.enums.DbType;
import com.zjy.entities.TableColumnInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/2/27.
 */
public interface ToolService {
    String getTableInfo(DbType dbType, String url, String user, String password, String tableName);
}
