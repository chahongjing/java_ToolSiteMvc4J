package com.zjy.bll.service;

import com.zjy.baseframework.ServiceException;
import com.zjy.bll.baseBean.PageBean;
import com.zjy.bll.common.BaseService;
import com.zjy.bll.dao.PermissionDao;
import com.zjy.bll.request.PermissionRequest;
import com.zjy.bll.vo.PermissionVo;
import com.zjy.entities.Permission;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl extends BaseService<PermissionDao, Permission> implements PermissionService {
    /**
     * 添加用户
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public int add(Permission entity) {
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
    public int update(Permission entity) {
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
    public void save(PermissionVo vo) {
        PermissionVo voDb = getVo(vo.getPermissionId());
        beforeCheck(vo);
        // 处理密码
        if (voDb.getIsSave()) {
            update(vo);
        } else {
            add(vo);
        }
    }

    @Override
    public PageBean<? extends Permission> queryPageList(PermissionRequest request) {
        Permission po = new Permission();
        po.setName(request.getName());
        po.setFunctionId(request.getFunctionId());
        return (PageBean<PermissionVo>) super.queryPageList(request, po);
    }

    public PermissionVo get(String id) {
        return (PermissionVo) super.get(id);
    }

    @Override
    public PermissionVo getVo(String id) {
        PermissionVo vo = get(id);
        if (vo == null) {
            vo = new PermissionVo();
            vo.setPermissionId(id);
            vo.setIsSave(false);
        } else {
            vo.setIsSave(true);
        }
        return vo;
    }

    @Override
    public List<PermissionVo> queryAllPermissionList() {
        return dao.queryAllPermissionList();
    }

    protected void beforeCheck(PermissionVo po) {
        if (StringUtils.isBlank(po.getName())) {
            throw new ServiceException("请输入功能名称！");
        }
        Map<String, Integer> map = dao.queryRepeatCount(po.getPermissionId(), po.getCode());
        if (map != null && map.containsKey("codeCount") && map.get("codeCount") > 0) {
            throw new ServiceException("权限编码重复！");
        }
    }
}
