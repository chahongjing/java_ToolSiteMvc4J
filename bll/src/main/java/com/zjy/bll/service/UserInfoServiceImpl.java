package com.zjy.bll.service;

import com.zjy.baseframework.BaseResult;
import com.zjy.baseframework.ServiceException;
import com.zjy.baseframework.enums.ResultStatus;
import com.zjy.bll.basebean.PageBean;
import com.zjy.bll.common.BackAdminUsernamePasswordToken;
import com.zjy.bll.common.BaseService;
import com.zjy.bll.common.ShiroRealm;
import com.zjy.bll.dao.UserInfoDao;
import com.zjy.bll.request.UserInfoRequest;
import com.zjy.bll.vo.UserInfoVo;
import com.zjy.entities.UserInfo;
import com.zjy.entities.enums.Sex;
import com.zjy.entities.enums.YesNo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chahongjing
 * @create 2016-12-05 22:16
 */
@Service
public class UserInfoServiceImpl extends BaseService<UserInfoDao, UserInfo> implements UserInfoService {

    @Autowired
    protected ShiroRealm shiroRealm;

    /**
     * 添加用户
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public int add(UserInfo entity) {
        entity.setCreatedBy(shiroRealm.getCurrentUser().getUserId());
        entity.setCreatedOn(new Date());
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
    public int update(UserInfo entity) {
        entity.setModifiedBy(shiroRealm.getCurrentUser().getUserId());
        entity.setModifiedOn(new Date());
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
    public void save(UserInfoVo vo) {
        UserInfoVo voDb = getVo(vo.getUserId());
        beforeCheck(vo);
        // 处理密码
        if (voDb.getIsSave()) {
            update(vo);
        } else {
            vo.setPassword(shiroRealm.getMd5Hash(vo.getPassword(), vo.getUserCode()));
            add(vo);
        }
    }

    @Override
    public UserInfoVo get(String id) {
        return (UserInfoVo) super.get(id);
    }

    @Override
    public UserInfoVo getVo(String id) {
        UserInfoVo vo = get(id);
        if (vo == null) {
            vo = new UserInfoVo();
            vo.setUserId(id);
            vo.setSex(Sex.Male);
            vo.setIsSave(false);
            vo.setIsSystem(YesNo.NO);
            vo.setIsDisabled(YesNo.NO);
            vo.setCreatedOn(new Date());
            vo.setCreatedBy(shiroRealm.getCurrentUser().getUserId());
        } else {
            vo.setIsSave(true);
        }
        return vo;
    }

    @Override
    public PageBean<? extends UserInfo> queryPageList(UserInfoRequest request) {
        UserInfoVo user = new UserInfoVo();
        user.setUserName(request.getUserName());
        user.setUserCode(request.getUserName());
        user.setSex(request.getSex());
        List<String> orderBy = new ArrayList<>();
        if(request.getNameOrderBy() != null) {
            orderBy.add("user.userName " + request.getNameOrderBy().toString());
        }
        if(request.getCodeOrderBy() != null) {
            orderBy.add("user.userCode " + request.getCodeOrderBy().toString());
        }
        if(request.getCreatedOnOrderBy() != null) {
            orderBy.add("user.createdOn " + request.getCreatedOnOrderBy().toString());
        }
        request.setOrderBy(String.join(", ", orderBy));
        return super.queryPageList(request, user);
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @Override
    public BaseResult<UserInfoVo> login(UserInfo user) {
        BaseResult<UserInfoVo> result = new BaseResult<>();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new BackAdminUsernamePasswordToken(user.getUserCode(), user.getPassword());
        try {
            // 登录
            subject.login(token);
        } catch (AuthenticationException ex) {
            result.setStatus(ResultStatus.NO);
            result.setMessage("用户名或密码错误！");
            return result;
        } catch (Exception ex) {
            logger.error("登录异常！", ex);
            result.setStatus(ResultStatus.NO);
            result.setMessage("登录异常！");
            return result;
        }
        // 登录成功
        UserInfoVo userInfo = getByUserCode(user.getUserCode());
        // 踢除多端同一用户session
        shiroRealm.kickOutUser(userInfo.getUserCode());
        userInfo.setPermissionList(shiroRealm.getPermissions(userInfo.getUserId()));
        userInfo.setPassword(null);
        result.setStatus(ResultStatus.OK);
        result.setValue(userInfo);
        return result;
    }

    @Override
    public BaseResult<String> logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
            // session 会销毁，在SessionListener监听session销毁，清理权限缓存
        }

        return BaseResult.ok();
    }

    @Override
    public List<UserInfo> query(UserInfo entity) {
        return (List<UserInfo>) super.queryList(entity);
    }

    @Override
    public UserInfoVo getByUserCode(String userCode) {
        return dao.getByCode(userCode);
    }

    protected void beforeCheck(UserInfoVo vo) {
        if (StringUtils.isBlank(vo.getUserCode())) {
            throw new ServiceException("请输入用户编号！");
        }
        Map<String, BigDecimal> map = dao.queryRepeatCount(vo.getUserId(), vo.getUserCode());
        if (map != null && map.containsKey("CODECOUNT") && map.get("CODECOUNT").intValue() > 0) {
            throw new ServiceException("编号重复！");
        }
        if (StringUtils.isBlank(vo.getUserName())) {
            throw new ServiceException("请输入用户名称！");
        }
        if (!vo.getIsSave() && StringUtils.isBlank(vo.getPassword())) {
            throw new ServiceException("请输入密码！");
        }
        if (!vo.getIsSave() && !vo.getPassword().equals(vo.getPasswordAgain())) {
            throw new ServiceException("两次密码不一致！");
        }
    }

    @Override
    public void changePassword(String userCode, String oldPassword, String newPassword) {
        UserInfo currentUser = shiroRealm.getCurrentUser();
        if (currentUser == null) {
            throw new ServiceException("用户未登录！");
        }
        String userCodeCur = currentUser.getUserCode();
        if (!userCodeCur.equals(userCode)) {
            throw new ServiceException("参数错误！");
        }
        UserInfo user = this.getByUserCode(userCodeCur);
        if (user == null) {
            throw new ServiceException("用户不存在！");
        }
        if (StringUtils.isBlank(oldPassword)) {
            throw new ServiceException("请输入原密码！");
        }
        if (StringUtils.isBlank(newPassword)) {
            throw new ServiceException("请输入新密码！");
        }
        String oldPasswordEnc = shiroRealm.getMd5Hash(oldPassword, userCodeCur);
        String newPasswordEnc = shiroRealm.getMd5Hash(newPassword, userCodeCur);
        if (!oldPasswordEnc.equals(user.getPassword())) {
            throw new ServiceException("原密码错误！");
        }
        user.setPassword(newPasswordEnc);
        dao.updateUserPassword(user.getUserId(), user.getPassword());
    }

    @Override
    public void resetPassword(String userCode, String password) {
        UserInfo user = this.getByUserCode(userCode);
        if (user == null) {
            throw new ServiceException("用户不存在！");
        }
        if (StringUtils.isBlank(password)) {
            throw new ServiceException("密码不能为空！");
        }
        String newPasswordEnc = shiroRealm.getMd5Hash(password, userCode);
        user.setPassword(newPasswordEnc);
        dao.updateUserPassword(user.getUserId(), user.getPassword());
    }
}
