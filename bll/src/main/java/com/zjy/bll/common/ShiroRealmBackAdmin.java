package com.zjy.bll.common;

import com.zjy.bll.service.RolePermissionService;
import com.zjy.bll.service.UserInfoService;
import com.zjy.bll.service.UserRoleService;
import com.zjy.entities.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * shiro认证
 *
 * @author chahongjing
 * @create 2016-10-27 23:04
 */
public class ShiroRealmBackAdmin extends AuthorizingRealm {

    @Autowired
    protected UserInfoService userInfoSvc;

    @Autowired
    protected UserRoleService userRoleSrv;

    @Autowired
    protected RolePermissionService rolePermissionSrv;

    public ShiroRealmBackAdmin() {
        super();
        this.setAuthenticationTokenClass(ShiroTokenBackAdmin.class);
    }

    /**
     * 获取当前登录用户数据库信息
     *
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        UserInfo user = userInfoSvc.getByUserCode(token.getUsername());
        if (null != user) {
            return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getUserCode()),
                    user.getUserCode());
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
        List<String> roles = userRoleSrv.queryUserRoleCodeList(user.getUserId());
        List<String> permissions = rolePermissionSrv.getPermissions(user.getUserId());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //给当前用户设置角色
        info.addRoles(roles);
        //给当前用户设置权限
        info.addStringPermissions(permissions);
        return info;
    }

    public void clearCachedAuthorizationInfo() {
        super.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
        // clearCachedAuthenticationInfo, clearAllCachedAuthorizationInfo, clearAllCachedAuthenticationInfo, clearAllCache
    }

    /**
     * 登录成功
     *
     * @param token
     * @param info
     * @throws AuthenticationException
     */
    @Override
    protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws AuthenticationException {
        super.assertCredentialsMatch(token, info);
        String userCode = ((UsernamePasswordToken) token).getUsername();
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(ShiroRealmUtils.realmAttrKey, getClass().getSimpleName());
        session.setAttribute(ShiroRealmUtils.userCodeAttrKey + getClass().getSimpleName(), userCode);
        kickOutUser(userCode);
    }

    /**
     * 踢除其它端的用户
     * @param userCode
     */
    protected void kickOutUser(String userCode) {
        if (StringUtils.isBlank(userCode)) throw new IllegalArgumentException("用户编码不能为空！");
        String curSessionId = SecurityUtils.getSubject().getSession().getId().toString();
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager.getSessionManager();
        Collection<Session> activeSessions = sessionManager.getSessionDAO().getActiveSessions();
        for (Session session : activeSessions) {
            String sessionUserCode = String.valueOf(session.getAttribute(ShiroRealmUtils.userCodeAttrKey + getClass().getSimpleName()));
            // 当前session或登录名不相等略过
            if (!userCode.equals(sessionUserCode) || curSessionId.equals(session.getId())) continue;
            session.setTimeout(0);
        }
    }

    /**
     * 获取权限
     * @return
     */
    public Set<String> getPermissions() {
        AuthorizationInfo authorizationInfo = getAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
        return (Set<String>) authorizationInfo.getStringPermissions();
    }
}
