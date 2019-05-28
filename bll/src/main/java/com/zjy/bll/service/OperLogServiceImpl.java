package com.zjy.bll.service;

import com.zjy.bll.basebean.PageBean;
import com.zjy.bll.common.BaseService;
import com.zjy.bll.dao.OperLogDao;
import com.zjy.bll.request.OperLogRequest;
import com.zjy.bll.vo.OperLogVo;
import com.zjy.entities.OperLog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperLogServiceImpl extends BaseService<OperLogDao, OperLog> implements OperLogService {

    @Override
    public OperLogVo get(String logId) {
        return dao.get(logId);
    }

    @Override
    public PageBean<OperLogVo> queryPageList(OperLogRequest request) {
        OperLogVo po = new OperLogVo();
        po.setLogLevel(request.getLogLevel());
        return (PageBean<OperLogVo>) super.queryPageList(request, po);
    }

    @Override
    public List<OperLogVo> queryList(OperLogVo vo) {
        return dao.query(vo);
    }

    /**
     * 删除
     *
     * @return
     */
    public int deleteAll() {
        return dao.deleteAll();
    }
}
