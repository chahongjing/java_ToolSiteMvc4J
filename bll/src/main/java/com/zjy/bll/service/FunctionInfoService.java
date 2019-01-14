package com.zjy.bll.service;

import com.zjy.bll.basebean.PageBean;
import com.zjy.bll.request.FunctionInfoRequest;
import com.zjy.bll.vo.FunctionInfoVo;
import com.zjy.entities.FunctionInfo;

import java.util.List;

public interface FunctionInfoService {
    int add(FunctionInfo po);

    int update(FunctionInfo po);

    int delete(String id);

    void save(FunctionInfoVo vo);

    FunctionInfoVo getVo(String id);

    PageBean<? extends FunctionInfo> queryPageList(FunctionInfoRequest request);

    List<FunctionInfoVo> queryFunctionList();

    List<FunctionInfoVo> queryAllFunctionList();
}
