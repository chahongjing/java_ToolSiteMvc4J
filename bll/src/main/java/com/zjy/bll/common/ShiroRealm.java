package com.zjy.bll.common;

import com.zjy.baseframework.CacheHelper;
import com.zjy.baseframework.KeyHelper;
import com.zjy.bll.service.RolePermissionService;
import com.zjy.bll.service.UserInfoService;
import com.zjy.bll.service.UserRoleService;
import com.zjy.bll.vo.RolePermissionVo;
import com.zjy.bll.vo.UserRoleVo;
import com.zjy.entities.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * shiro认证
 *
 * @author chahongjing
 * @create 2016-10-27 23:04
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoService userInfoSvc;

    @Autowired
    private UserRoleService userRoleSrv;

    @Autowired
    private RolePermissionService rolePermissionSrv;

    @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    private CacheHelper cacheHelper;

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
        List<String> roles = getRoles(user.getUserId());
        List<String> permissions = getPermissions(user.getUserId());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //给当前用户设置角色
        info.addRoles(roles);
        //给当前用户设置权限
        info.addStringPermissions(permissions);
        return info;
    }

    public void kickOutUser(String userCode){
        if(StringUtils.isBlank(userCode)) throw new IllegalArgumentException("用户编码不能为空！");
        String curSessionId = SecurityUtils.getSubject().getSession().getId().toString();
        Collection<Session> activeSessions = sessionDAO.getActiveSessions();
        for (Session session : activeSessions) {
            String sessionUserCode = String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
            if(!userCode.equals(sessionUserCode) || curSessionId.equals(session.getId())) continue;
            session.setTimeout(0);
        }
    }

    /**
     * 当前登录用户信息
     *
     * @return 用户信息
     */
    protected Subject getSubject() {
        Subject currentUser = SecurityUtils.getSubject();
//        Session session = currentUser.getSession();
        if (currentUser == null) {
            throw new UnauthenticatedException("登录超时！");
        }
        return currentUser;
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    public UserInfo getCurrentUser() {
        return (UserInfo) getSubject().getPrincipal();
    }

    public static String getUserId() {
        try {
            Subject subject = SecurityUtils.getSubject();
            UserInfo principal = (UserInfo) subject.getPrincipal();
            if (principal != null) {
                return principal.getUserId();
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 判断是否有角色
     *
     * @param role
     * @return
     */
    public boolean hasRole(String role) {
        return getSubject().hasRole(role);
    }

    /**
     * 判断是否有权限
     *
     * @param permission
     * @return
     */
    public boolean isPermitted(String permission) {
        return getSubject().isPermitted(permission);
    }

    public void clearCachedAuthorizationInfo() {
        super.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
        // clearCachedAuthenticationInfo, clearAllCachedAuthorizationInfo, clearAllCachedAuthenticationInfo, clearAllCache
    }

    /**
     * 获取md5 hash+盐值加密后的值
     *
     * @param password 密码
     * @param salt     盐值
     * @return
     */
    public String getMd5Hash(String password, String salt) {
        HashedCredentialsMatcher credentialsMatcher = (HashedCredentialsMatcher) getCredentialsMatcher();
        SimpleHash simpleHash = new SimpleHash(credentialsMatcher.getHashAlgorithmName(), password, ByteSource.Util.bytes(salt),
                credentialsMatcher.getHashIterations());
//        String newCredentials = new Md5Hash(password, salt, credentialsMatcher.getHashIterations()).toBase64();
//        return simpleHash.toBase64();
        return simpleHash.toString();
    }

    public List<String> getRoles(String userId) {
        List<UserRoleVo> userRoleList = userRoleSrv.queryListByUserId(userId);
        return userRoleList.stream().map(UserRoleVo::getRoleCode).distinct().collect(Collectors.toList());
    }

    public List<String> getPermissions(String userId) {
        String key = KeyHelper.getTsmKey(KeyHelper.USER_PERMISSION_LIST_KEY, userId);
        List<RolePermissionVo> permissionList = cacheHelper.get(key, List.class);
        if (org.apache.commons.collections.CollectionUtils.isEmpty(permissionList)) {
            List<UserRoleVo> userRoleList = userRoleSrv.queryListByUserId(userId);
            List<String> roleIdList = userRoleList.stream().map(UserRoleVo::getRoleId).distinct().collect(Collectors.toList());
            permissionList = rolePermissionSrv.queryRolePermission(roleIdList);
            cacheHelper.set(key, permissionList);
        }
        return permissionList.stream().filter(item -> StringUtils.isNotBlank(item.getPermissionCode()))
                .map(RolePermissionVo::getPermissionCode).distinct().collect(Collectors.toList());
    }
}
