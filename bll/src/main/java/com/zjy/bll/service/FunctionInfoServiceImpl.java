package com.zjy.bll.service;

import com.zjy.baseframework.ServiceException;
import com.zjy.bll.baseBean.PageBean;
import com.zjy.bll.common.BaseService;
import com.zjy.bll.dao.FunctionInfoDao;
import com.zjy.bll.request.FunctionInfoRequest;
import com.zjy.bll.vo.FunctionInfoVo;
import com.zjy.entities.FunctionInfo;
import com.zjy.entities.Permission;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class FunctionInfoServiceImpl extends BaseService<FunctionInfoDao, FunctionInfo> implements FunctionInfoService {

    @Autowired
    protected CommonService commonSrv;

    @Autowired
    protected PermissionService permissionSrv;

    /**
     * 添加用户
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public int add(FunctionInfo entity) {
        Permission permission = new Permission();
        permission.setPermissionId(commonSrv.getNewId());
        permission.setFunctionId(entity.getFunctionId());
        permission.setName("进入页面");
        permission.setCode(String.format("%s_enter", entity.getCode()));
        permission.setSeq(0);
        permissionSrv.add(permission);
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
        permissionSrv.delete(id);
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
    public PageBean<? extends FunctionInfo> queryPageList(FunctionInfoRequest request) {
        FunctionInfo po = new FunctionInfo();
        po.setName(request.getName());
        return super.queryPageList(request, po);
    }

    @Override
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
        Map<String, Integer> map = dao.queryRepeatCount(po.getFunctionId(), po.getCode());
        if (map != null && map.containsKey("codeCount") && map.get("codeCount") > 0) {
            throw new ServiceException("功能编码重复！");
        }
    }

    @Override
    public List<FunctionInfoVo> queryFunctionList() {
        return dao.queryFunctionList();
    }

    @Override
    public List<FunctionInfoVo> queryAllFunctionList() {
        return dao.queryAllFunctionList();
    }
}
