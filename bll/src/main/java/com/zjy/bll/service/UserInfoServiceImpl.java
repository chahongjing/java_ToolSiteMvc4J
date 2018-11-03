package com.zjy.bll.service;

import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.BaseResult;
import com.zjy.baseframework.ServiceException;
import com.zjy.baseframework.enums.ResultStatus;
import com.zjy.bll.common.BaseService;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chahongjing
 * @create 2016-12-05 22:16
 */
@Service
public class UserInfoServiceImpl extends BaseService<UserInfoDao, UserInfo> implements UserInfoService {

    /**
     * 添加用户
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public int add(UserInfo entity) {
        return super.add(entity);
    }

    /**
     * 修改用户
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public int update(UserInfo entity){
        return super.update(entity);
    }

    /**
     * 删除用户
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
     * @param userInfo
     */
    @Override
    @Transactional
    public void saveUser(UserInfoVo userInfo) {
        UserInfoVo vo = getVo(userInfo.getUserId());
        beforeCheck(userInfo);
        // 处理密码
        if(vo.getIsSave()) {
            userInfo.setModifiedOn(new Date());
            update(userInfo);
        } else {
            userInfo.setPassword(shiroRealm.getMd5Hash(userInfo.getPassword(), userInfo.getUserCode()));
            userInfo.setCreatedOn(new Date());
            add(userInfo);
        }
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public BaseResult<UserInfo> login(UserInfo user) {
        BaseResult<UserInfo> result = new BaseResult<>();
        Subject subject = SecurityUtils.getSubject();
//        if (subject.isAuthenticated()) {
//            return null;
//        }
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserCode(), user.getPassword());
        try {
            // 登录
            subject.login(token);
        }catch(AuthenticationException ex) {
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
        UserInfo userInfo = getByUserCode(user.getUserCode());
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

        return BaseResult.OK();
    }

    @Override
    public List<UserInfo> query(UserInfo entity) {
        return (List<UserInfo>)super.queryList(entity);
    }

    @Override
    public PageInfo<? extends UserInfo> queryPageList(UserInfoRequest request) {
        UserInfo user = new UserInfo();
        user.setUserName(request.getUserName());
        user.setUserCode(request.getUserName());
        PageInfo<UserInfoVo> pageInfo = (PageInfo<UserInfoVo>)super.queryPageList(request, user);
        for (UserInfoVo userInfo : pageInfo.getList()) {
            if(userInfo.getSex() != null) {
                userInfo.setSexName(userInfo.getSex().getName());
            }
            if(userInfo.getIsDisabled() != null) {
                userInfo.setIsDisabledName(userInfo.getIsDisabled().getName());
            }
            if(userInfo.getIsSystem() != null) {
                userInfo.setIsSystemName(userInfo.getIsSystem().getName());
            }
        }
        return pageInfo;
    }

    @Override
    public UserInfo getByUserCode(String userCode){
        return dao.getByCode(userCode);
    }

    public UserInfoVo get(String userId) {
        return (UserInfoVo)super.get(userId);
    }

    @Override
    public UserInfoVo getVo(String userId) {
        UserInfoVo vo = get(userId);
        if(vo == null) {
            vo = new UserInfoVo();
            vo.setUserId(userId);
            vo.setSex(Sex.Male);
            vo.setIsSave(false);
            vo.setIsSystem(YesNo.NO);
            vo.setIsDisabled(YesNo.NO);
        } else {
            vo.setIsSave(true);
        }
        return vo;
    }

    protected void beforeCheck(UserInfoVo userInfo) {
        if(StringUtils.isBlank(userInfo.getUserCode())) {
            throw new ServiceException("请输入用户编号！");
        }
        Map<String, BigDecimal> map = dao.queryRepeatCount(userInfo.getUserId(), userInfo.getUserCode());
        if(map != null && map.containsKey("CODECOUNT") && map.get("CODECOUNT").intValue() > 0) {
            throw new ServiceException("编号重复！");
        }
    }
}
