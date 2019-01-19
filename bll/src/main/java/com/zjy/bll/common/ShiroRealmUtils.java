package com.zjy.bll.common;

import com.zjy.baseframework.SpringContextHolder;
import com.zjy.bll.service.RolePermissionService;
import com.zjy.bll.service.UserRoleService;
import com.zjy.bll.vo.RolePermissionVo;
import com.zjy.bll.vo.UserRoleVo;
import com.zjy.entities.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.util.ByteSource;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ShiroRealmUtils {

    static String userCodeAttrKey = "userCode";
    static String realmAttrKey = "realm";

    private ShiroRealmUtils() {
    }

    private static ShiroRealmBackAdmin shiroRealmBackAdmin = SpringContextHolder.getBean("shiroRealmBackAdmin");
    private static ShiroRealmFrontAdmin shiroRealmFrontAdmin = SpringContextHolder.getBean("shiroRealmFrontAdmin");

    private static RedisShiroSessionDao sessionDao = SpringContextHolder.getBean("sessionDAO");
    private static UserRoleService userRoleSrv = SpringContextHolder.getBean(UserRoleService.class);
    private static RolePermissionService rolePermissionSrv = SpringContextHolder.getBean(RolePermissionService.class);

    public static AuthorizingRealm getRealm() {
        String realmClassName = (String) SecurityUtils.getSubject().getSession().getAttribute(ShiroRealmUtils.realmAttrKey);
        if (shiroRealmBackAdmin.getClass().getSimpleName().equals(realmClassName)) return shiroRealmBackAdmin;
        if (shiroRealmFrontAdmin.getClass().getSimpleName().equals(realmClassName)) return shiroRealmFrontAdmin;
        return shiroRealmBackAdmin;
    }

    public static UserInfo getCurrentUser() {
        return (UserInfo) SecurityUtils.getSubject().getPrincipal();
    }

    public static String getCurrentUserId() {
        return getCurrentUser().getUserId();
    }

    public static boolean isPermitted(String permission) {
        return SecurityUtils.getSubject().isPermitted(permission);
    }

    /**
     * 获取md5 hash+盐值加密后的值
     *
     * @param password 密码
     * @param salt     盐值
     * @return
     */
    public static String getMd5Hash(String password, String salt) {
        HashedCredentialsMatcher credentialsMatcher = (HashedCredentialsMatcher) getRealm().getCredentialsMatcher();
        SimpleHash simpleHash = new SimpleHash(credentialsMatcher.getHashAlgorithmName(), password, ByteSource.Util.bytes(salt),
                credentialsMatcher.getHashIterations());
//        String newCredentials = new Md5Hash(password, salt, credentialsMatcher.getHashIterations()).toBase64();
//        return simpleHash.toBase64();
        return simpleHash.toString();
    }

    public static void kickOutUser(String userCode) {
        if (StringUtils.isBlank(userCode)) throw new IllegalArgumentException("用户编码不能为空！");
        String curSessionId = SecurityUtils.getSubject().getSession().getId().toString();
        Collection<Session> activeSessions = sessionDao.getActiveSessions();
        for (Session session : activeSessions) {
            String sessionUserCode = String.valueOf(session.getAttribute(userCodeAttrKey));
            if (!userCode.equals(sessionUserCode) || curSessionId.equals(session.getId())) continue;
            session.setTimeout(0);
        }
    }

    public static List<String> getRoles(String userId) {
        List<UserRoleVo> userRoleList = userRoleSrv.queryListByUserId(userId);
        return userRoleList.stream().map(UserRoleVo::getRoleCode).distinct().collect(Collectors.toList());
    }

    public static List<String> getPermissions(String userId) {
        List<UserRoleVo> userRoleList = userRoleSrv.queryListByUserId(userId);
        List<String> roleIdList = userRoleList.stream().map(UserRoleVo::getRoleId).distinct().collect(Collectors.toList());
        List<RolePermissionVo> permissionList = rolePermissionSrv.queryRolePermission(roleIdList);
        return permissionList.stream().filter(item -> StringUtils.isNotBlank(item.getPermissionCode()))
                .map(RolePermissionVo::getPermissionCode).distinct().collect(Collectors.toList());
    }
}
