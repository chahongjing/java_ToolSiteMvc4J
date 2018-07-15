package com.zjy.bll.service.cashManagement;

import com.github.pagehelper.PageInfo;
import com.zjy.bll.common.BaseService;
import com.zjy.bll.dao.cashManagement.CostDao;
import com.zjy.bll.request.cashManagement.CostRequest;
import com.zjy.entities.cashManagement.Cost;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chahongjing
 * @create 2016-12-05 22:16
 */
@Service
public class CostServiceImpl extends BaseService<CostDao, Cost> implements CostService {
    @Override
    public PageInfo<Cost> getList(CostRequest request) {
        Map<String, Object> query = new HashMap<>();
        query.put("userId", request.getUserId());
        return (PageInfo<Cost>)queryPage(request, query);
    }
}
