package com.zjy.bll.common;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * Created by Administrator on 2018/5/21.
 */
public class CustomCredentialsMatcher extends HashedCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
        // 如果密码有加密，则传过来的密码必须是经过处理之后的值
        // return super.doCredentialsMatch(authcToken, info);
        Object tokenHashedCredentials = hashProvidedCredentials(authcToken, info);
        String password = tokenHashedCredentials.toString();
//        UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
//        String password = new String(token.getPassword());
        return super.equals(password, info.getCredentials().toString());
    }
}
