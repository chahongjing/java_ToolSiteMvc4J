package com.zjy.bll.common;

import com.zjy.baseframework.CacheHelper;
import com.zjy.baseframework.MyRealm;
import com.zjy.bll.service.UserInfoService;
import com.zjy.entities.UserInfo;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoaderListener;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by chahongjing on 2017/6/10.
 */
@Component
public class UserUtils extends MyRealm {
    @Autowired
    private UserInfoService userInfoSvc;

    public UserUtils() {
        if(UserUtils.myfun == null) {
            UserUtils.myfun = (userCode) -> userInfoSvc.getByUserCode(userCode);
        }
    }

    public UserInfo getCurrentUser() {
        UserInfo user = (UserInfo)CacheHelper.get(currentKey);
        if(user == null) {
            user = (UserInfo)getUser();
            if(user == null) throw new UnauthenticatedException("用户未登录！");
            CacheHelper.set(currentKey, user);
        }
        return user;
    }

    public void logout() {
        CacheHelper.clear(currentKey);
    }
}
