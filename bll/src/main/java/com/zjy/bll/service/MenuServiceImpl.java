package com.zjy.bll.service;

import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.ServiceException;
import com.zjy.bll.common.BaseService;
import com.zjy.bll.dao.MenuDao;
import com.zjy.bll.request.MenuRequest;
import com.zjy.bll.vo.MenuVo;
import com.zjy.bll.vo.RolePermissionVo;
import com.zjy.bll.vo.UserRoleVo;
import com.zjy.entities.Menu;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl extends BaseService<MenuDao, Menu> implements MenuService {

    @Autowired
    protected RoleInfoService roleInfoSrv;

    @Autowired
    protected UserRoleService userRoleSrv;

    @Autowired
    protected RolePermissionService rolePermissionSrv;

    /**
     * 添加用户
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public int add(Menu entity) {
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
    public int update(Menu entity) {
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
    public void save(MenuVo vo) {
        MenuVo voDb = getVo(vo.getMenuId());
        beforeCheck(vo);
        // 处理密码
        if (voDb.getIsSave()) {
            update(vo);
        } else {
            add(vo);
        }
    }

    @Override
    public PageInfo<? extends Menu> queryPageList(MenuRequest request) {
        Menu po = new Menu();
        po.setName(request.getName());
        PageInfo<MenuVo> pageInfo = (PageInfo<MenuVo>) super.queryPageList(request, po);
        return pageInfo;
    }

    public MenuVo get(String id) {
        return (MenuVo) super.get(id);
    }

    @Override
    public MenuVo getVo(String id) {
        MenuVo vo = get(id);
        if (vo == null) {
            vo = new MenuVo();
            vo.setMenuId(id);
            vo.setIsSave(false);
        } else {
            vo.setIsSave(true);
        }
        return vo;
    }

    protected void beforeCheck(MenuVo po) {
        if (StringUtils.isBlank(po.getName())) {
            throw new ServiceException("请输入功能名称！");
        }
        Map<String, BigDecimal> map = dao.queryRepeatCount(po.getMenuId(), po.getCode());
        if (map != null && map.containsKey("CODECOUNT") && map.get("CODECOUNT").intValue() > 0) {
            throw new ServiceException("功能名称重复！");
        }
    }

    public List<MenuVo> queryParentList() {
        return dao.queryParentList();
    }

    @Override
    public List<MenuVo> queryPageMenuList(){
        return dao.queryPageMenuList();
    }

    @Override
    public List<MenuVo> queryPermissionMenu() {
        String userId = shiroRealm.getCurrentUser().getUserId();
        List<UserRoleVo> roleList = userRoleSrv.queryListByUserId(userId);
        String roleId = "9ca5cbcc-7f4a-4402-bc2e-90ae2103dbed";
        List<String> roleIdList = roleList.stream().map(item -> item.getRoleId()).collect(Collectors.toList());
//        List<String> roleIdList = new ArrayList<>();
        //roleIdList.add(roleId);
        List<MenuVo> result = new ArrayList<>(), list = (List<MenuVo>) dao.query(null);
        List<MenuVo> parentList = list.stream().filter(item -> StringUtils.isBlank(item.getPId())).collect(Collectors.toList());
        List<MenuVo> children = list.stream().filter(item -> StringUtils.isNotBlank(item.getPId())).collect(Collectors.toList());
        List<RolePermissionVo> rolePermissionVos = rolePermissionSrv.queryRolePermission(roleIdList);
        for (MenuVo parent : parentList) {
            if(!rolePermissionVos.stream().anyMatch(item -> roleIdList.contains(item.getRoleId()) && parent.getMenuId().equals(item.getPermissionId()))) {
                continue;
            }
            result.add(parent);
            List<MenuVo> temp = children.stream().filter(item -> item.getPId().equals(parent.getMenuId())
            && rolePermissionVos.stream().anyMatch(innerItem -> roleIdList.contains(innerItem.getRoleId()) && item.getMenuId().equals(innerItem.getPermissionId()))
            ).collect(Collectors.toList());
            result.addAll(temp);
        }
        return result;
    }

    @Override
    public List<MenuVo> queryAllMenu() {
        List<MenuVo> result = new ArrayList<>(), list = (List<MenuVo>) dao.query(null);
        List<MenuVo> parentList = list.stream().filter(item -> StringUtils.isBlank(item.getPId())).collect(Collectors.toList());
        List<MenuVo> children = list.stream().filter(item -> StringUtils.isNotBlank(item.getPId())).collect(Collectors.toList());
        for (MenuVo parent : parentList) {
            result.add(parent);
            List<MenuVo> temp = children.stream().filter(item -> item.getPId().equals(parent.getMenuId())).collect(Collectors.toList());
            result.addAll(temp);
        }
        return result;
    }
}
