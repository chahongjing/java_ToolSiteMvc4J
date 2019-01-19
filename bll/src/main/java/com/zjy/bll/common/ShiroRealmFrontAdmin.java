package com.zjy.bll.common;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * shiro认证
 *
 * @author chahongjing
 * @create 2016-10-27 23:04
 */
public class ShiroRealmFrontAdmin extends ShiroRealmBackAdmin {

    public ShiroRealmFrontAdmin() {
        super();
        this.setAuthenticationTokenClass(ShiroTokenFrontAdmin.class);
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
        return super.doGetAuthenticationInfo(authcToken);
    }
}
