package com.zjy.bll.common.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * Created by Administrator on 2018/5/21.
 */
public class CustomCredentialsMatcher extends HashedCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
        return super.doCredentialsMatch(authcToken, info);
//        Object tokenHashedCredentials = hashProvidedCredentials(authcToken, info);
//        String password = tokenHashedCredentials.toString();
//        // 密码原始值
//        UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
//        String password = new String(token.getPassword());
//        return super.equals(password, info.getCredentials().toString());
    }
}
