package com.zjy.bll.common;

import com.zjy.baseframework.interfaces.ICache;
import com.zjy.bll.service.UserInfoService;
import com.zjy.entities.UserInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by chahongjing on 2017/6/10.
 */
public class UserUtils extends ShiroRealm {
    @Autowired
    private UserInfoService userInfoSvc;

    @Autowired
    private ICache cacheHelper;

    public UserUtils() {
        if (UserUtils.myfun == null) {
            UserUtils.myfun = (userCode) -> userInfoSvc.getByUserCode(userCode);
        }
    }

    public UserInfo getCurrentUser() {
        UserInfo user = cacheHelper.get(currentKey);
        if (user == null) {
            user = (UserInfo) getUser();
            if (user == null) throw new UnauthenticatedException("用户未登录！");
            cacheHelper.set(currentKey, user);
        }
        return user;
    }

    public void logout() {
        cacheHelper.clear(currentKey);
    }
}
