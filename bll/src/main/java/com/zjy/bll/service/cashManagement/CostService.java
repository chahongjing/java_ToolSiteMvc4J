package com.zjy.bll.service.cashManagement;

import com.github.pagehelper.PageInfo;
import com.zjy.bll.request.cashManagement.CostRequest;
import com.zjy.entities.cashManagement.Cost;

import java.util.List;

/**
 * @author chahongjing
 * @create 2016-12-05 22:16
 */
public interface CostService {
    PageInfo<Cost> getList(CostRequest request);
}
