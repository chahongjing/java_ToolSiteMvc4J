package com.zjy.bll.common;

import com.zjy.baseframework.SpringContextHolder;
import com.zjy.entities.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.util.ByteSource;

import java.util.Set;

public class ShiroRealmUtils {

    static String userCodeAttrKey = "userCode";
    static String realmAttrKey = "realm";

    private ShiroRealmUtils() {
    }

    private static ShiroRealmBackAdmin shiroRealmBackAdmin = SpringContextHolder.getBean("shiroRealmBackAdmin");
    private static ShiroRealmFrontAdmin shiroRealmFrontAdmin = SpringContextHolder.getBean("shiroRealmFrontAdmin");

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

    public static Set<String> getPermissions() {
        ShiroRealmBackAdmin realm = (ShiroRealmBackAdmin) getRealm();
        return realm.getPermissions();
    }
}
