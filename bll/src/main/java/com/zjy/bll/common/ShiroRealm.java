package com.zjy.bll.common;

import com.zjy.entities.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * shiro认证
 *
 * @author chahongjing
 * @create 2016-10-27 23:04
 */
public class ShiroRealm extends AuthorizingRealm {

    public static String currentKey = "currentUser";

    protected static Function<String, Object> myfun;

    /**
     * 获取当前登录用户数据库信息
     *
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        UserInfo user = (UserInfo) myfun.apply(token.getUsername());
        if (null != user) {
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user, user.getPassword(), user.getUserName());

            CustomCredentialsMatcher credentialsMatcher = (CustomCredentialsMatcher) getCredentialsMatcher();
            ByteSource salt = ByteSource.Util.bytes(user.getUserCode());
            SimpleHash sh = new SimpleHash(credentialsMatcher.getHashAlgorithmName(), user.getPassword(), salt, credentialsMatcher.getHashIterations());
            //通过关于盐值的构造器，将前端传入的密码在加密时再加入盐值
            authcInfo = new SimpleAuthenticationInfo(user, sh.toString(), salt, user.getUserName());

            return authcInfo;
        } else {
            return null;
        }
    }

    /**
     * 获取当前用户被授予角色和权限
     * 经测试:本例中该方法的调用时机为需授权资源被访问时
     * 经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache
     * 个人感觉若使用了Spring3.1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache
     * 比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        UserInfo user = (UserInfo) principals.getPrimaryPrincipal();
        List<String> roles = new ArrayList<>();
        List<String> permissions = new ArrayList<>();

        roles.add("admin");
        permissions.add("admin:testPermission");

//        @RequiresRoles("admin")
//        @RequiresPermissions(value = {"admin:testPermission"}, logical = Logical.OR)

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //给当前用户设置角色
        info.addRoles(roles);
        //给当前用户设置权限
        info.addStringPermissions(permissions);
        return info;
    }

    public Object getUser() {
        Subject currentUser = SecurityUtils.getSubject();
//        Session session = currentUser.getSession();
        if (null != currentUser) {
            return currentUser.getPrincipal();
        }
        return null;
    }

    public String getMd5Hash(String salt, String password) {
        CustomCredentialsMatcher credentialsMatcher = (CustomCredentialsMatcher) getCredentialsMatcher();
        Object simpleHash = new SimpleHash(credentialsMatcher.getHashAlgorithmName(), password, ByteSource.Util.bytes(salt),
                credentialsMatcher.getHashIterations());
        return simpleHash.toString();
    }
}
