package com.zjy.bll.service;

import com.zjy.baseframework.ServiceException;
import com.zjy.bll.baseBean.PageBean;
import com.zjy.bll.common.BaseService;
import com.zjy.bll.dao.RoleInfoDao;
import com.zjy.bll.request.RoleInfoRequest;
import com.zjy.bll.vo.RoleInfoVo;
import com.zjy.entities.RoleInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class RoleInfoServiceImpl extends BaseService<RoleInfoDao, RoleInfo> implements RoleInfoService {

    @Autowired
    protected MenuService menuSrv;

    @Autowired
    protected FunctionInfoService functionInfoSrv;

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
    public int add(RoleInfo entity) {
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
    public int update(RoleInfo entity) {
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
    public void save(RoleInfoVo vo) {
        RoleInfoVo voDb = getVo(vo.getRoleId());
        beforeCheck(vo);
        // 处理密码
        if (voDb.getIsSave()) {
            update(vo);
        } else {
            add(vo);
        }
    }

    @Override
    public PageBean<RoleInfoVo> queryPageList(RoleInfoRequest request) {
        RoleInfo po = new RoleInfo();
        po.setName(request.getName());
        return (PageBean<RoleInfoVo>) super.queryPageList(request, po);
    }

    @Override
    public RoleInfoVo get(String id) {
        return (RoleInfoVo) super.get(id);
    }

    @Override
    public RoleInfoVo getVo(String id) {
        RoleInfoVo vo = get(id);
        if (vo == null) {
            vo = new RoleInfoVo();
            vo.setRoleId(id);
            vo.setIsSave(false);
        } else {
            vo.setIsSave(true);
        }
        return vo;
    }

    @Override
    public List<RoleInfoVo> queryAllRole() {
        return dao.queryAllRole();
    }

    protected void beforeCheck(RoleInfoVo po) {
        if (StringUtils.isBlank(po.getName())) {
            throw new ServiceException("请输入功能名称！");
        }
        Map<String, BigDecimal> map = dao.queryRepeatCount(po.getRoleId(), po.getCode());
        if (map != null && map.containsKey("CODECOUNT") && map.get("CODECOUNT").intValue() > 0) {
            throw new ServiceException("功能名称重复！");
        }
    }
}
