package com.zjy.bll.dao;

import com.zjy.bll.common.BaseDao;
import com.zjy.bll.vo.FunctionInfoVo;
import com.zjy.entities.FunctionInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface FunctionInfoDao extends BaseDao<FunctionInfo> {
    Map<String, BigDecimal> queryRepeatCount(@Param("functionId") String permissionId, @Param("code") String code);

    List<FunctionInfoVo> queryFunctionList();

    List<FunctionInfoVo> queryAllFunctionList();
}
