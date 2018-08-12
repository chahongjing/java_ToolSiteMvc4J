package com.zjy.bll.service;

import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.BaseResult;
import com.zjy.baseframework.ServiceException;
import com.zjy.baseframework.enums.ResultStatus;
import com.zjy.bll.common.BaseService;
import com.zjy.bll.common.ShiroRealm;
import com.zjy.bll.dao.UserInfoDao;
import com.zjy.bll.request.UserInfoRequest;
import com.zjy.bll.vo.UserInfoVo;
import com.zjy.entities.Sex;
import com.zjy.entities.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        UserInfoVo vo = getVo(userInfo.getUserGuid());
        beforeCheck(userInfo);
        if(vo.getIsSave()) {
            update(userInfo);
        } else {
            add(userInfo);
        }
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public BaseResult<String> login(UserInfo user) {
        BaseResult<String> result = new BaseResult<>();
        Subject subject = SecurityUtils.getSubject();
//        if (subject.isAuthenticated()) {
//            return null;
//        }
//        String password = userUtils.getMd5Hash(user.getPassword(), user.getUserCode());
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserCode(), user.getPassword());
        try {
            // 登录
            subject.login(token);
        } catch (Exception ex) {
            logger.error("用户名或密码错误！", ex);
            result.setStatus(ResultStatus.NO);
            result.setMessage("用户名或密码错误！");
            return result;
        }
        // 登录成功
        result.setStatus(ResultStatus.OK);
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
        }
        return pageInfo;
    }

    @Override
    public UserInfo getByUserCode(String userCode){
        return dao.getByCode(userCode);
    }

    @Override
    public UserInfoVo getVo(String userGuid) {
        UserInfoVo vo = (UserInfoVo)super.get(userGuid);
        if(vo == null) {
            vo = new UserInfoVo();
            vo.setUserGuid(userGuid);
            vo.setSex(Sex.Male);
            vo.setIsSave(false);
            vo.setIsDisabled(false);
        } else {
            vo.setIsSave(true);
        }
        return vo;
    }

    protected void beforeCheck(UserInfoVo userInfo) {
        Map<String, Long> map = dao.queryRepeatCount(userInfo.getUserGuid(), userInfo.getUserCode());
        if(map != null && map.containsKey("CODECOUNT") && map.get("CODECOUNT").intValue() > 0) {
            throw new ServiceException("编号重复！");
        }
    }
}
