package com.zjy.bll.service;

import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.ServiceException;
import com.zjy.bll.common.BaseService;
import com.zjy.bll.dao.FunctionInfoDao;
import com.zjy.bll.request.FunctionInfoRequest;
import com.zjy.bll.vo.FunctionInfoVo;
import com.zjy.entities.FunctionInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class FunctionInfoServiceImpl extends BaseService<FunctionInfoDao, FunctionInfo> implements FunctionInfoService {
    /**
     * 添加用户
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public int add(FunctionInfo entity) {
        return super.add(entity);
    }

    /**
     * 修改用户
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public int update(FunctionInfo entity) {
        return super.update(entity);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public int delete(String id) {
        return super.delete(id);
    }

    /**
     * 保存用户
     *
     * @param vo
     */
    @Override
    @Transactional
    public void save(FunctionInfoVo vo) {
        FunctionInfoVo voDb = getVo(vo.getFunctionId());
        beforeCheck(vo);
        // 处理密码
        if (voDb.getIsSave()) {
            update(vo);
        } else {
            add(vo);
        }
    }

    @Override
    public PageInfo<? extends FunctionInfo> queryPageList(FunctionInfoRequest request) {
        FunctionInfo po = new FunctionInfo();
        po.setName(request.getName());
        PageInfo<FunctionInfoVo> pageInfo = (PageInfo<FunctionInfoVo>) super.queryPageList(request, po);
        return pageInfo;
    }

    public FunctionInfoVo get(String id) {
        return (FunctionInfoVo) super.get(id);
    }

    @Override
    public FunctionInfoVo getVo(String id) {
        FunctionInfoVo vo = get(id);
        if (vo == null) {
            vo = new FunctionInfoVo();
            vo.setFunctionId(id);
            vo.setIsSave(false);
        } else {
            vo.setIsSave(true);
        }
        return vo;
    }

    protected void beforeCheck(FunctionInfoVo po) {
        if (StringUtils.isBlank(po.getName())) {
            throw new ServiceException("请输入功能名称！");
        }
        Map<String, BigDecimal> map = dao.queryRepeatCount(po.getFunctionId(), po.getCode());
        if (map != null && map.containsKey("CODECOUNT") && map.get("CODECOUNT").intValue() > 0) {
            throw new ServiceException("功能名称重复！");
        }
    }

    @Override
    public List<FunctionInfoVo> queryFunctionList(){
        return dao.queryFunctionList();
    }

    @Override
    public List<FunctionInfoVo> queryAllFunctionList(){
        return dao.queryAllFunctionList();
    }
}