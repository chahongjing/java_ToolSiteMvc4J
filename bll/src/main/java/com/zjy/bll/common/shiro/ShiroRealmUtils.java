package com.zjy.bll.common.shiro;

import com.zjy.baseframework.SecurityHelper;
import com.zjy.baseframework.SpringContextHolder;
import com.zjy.entities.UserInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.ConfigurableHashService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

public class ShiroRealmUtils {
    private ShiroRealmUtils() {
    }

    private static ShiroRealmBackAdmin shiroRealmBackAdmin = SpringContextHolder.getBean("shiroRealmBackAdmin");
    private static ShiroRealmFrontAdmin shiroRealmFrontAdmin = SpringContextHolder.getBean("shiroRealmFrontAdmin");

    public static AuthorizingRealm getRealm() {
        Set<String> realmNames = SecurityUtils.getSubject().getPrincipals().getRealmNames();
        if (CollectionUtils.isEmpty(realmNames)) return null;
        if (realmNames.contains(shiroRealmBackAdmin.getName())) {
            return shiroRealmBackAdmin;
        } else if (realmNames.contains(shiroRealmFrontAdmin.getName())) {
            return shiroRealmFrontAdmin;
        }
        return null;
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
        AuthorizingRealm realm = getRealm();
        if (realm == null) throw new UnsupportedOperationException("无法获取加密信息！");
        HashedCredentialsMatcher credentialsMatcher = (HashedCredentialsMatcher) realm.getCredentialsMatcher();
        SimpleHash simpleHash = new SimpleHash(credentialsMatcher.getHashAlgorithmName(), password, ByteSource.Util.bytes(salt),
                credentialsMatcher.getHashIterations());
//        String newCredentials = new Md5Hash(password, salt, credentialsMatcher.getHashIterations()).toBase64();
//        return simpleHash.toBase64();
        return simpleHash.toString();
    }

    public static String getSSOPassword(String password, String salt) {
        ConfigurableHashService hashService = new DefaultHashService();
        // 静态盐值
        hashService.setPrivateSalt(ByteSource.Util.bytes("."));
        // md5hash
        hashService.setHashAlgorithmName(SecurityHelper.MD5);
        // 加密2次
        hashService.setHashIterations(2);
        HashRequest request = new HashRequest.Builder()
                .setSalt(salt)
                .setSource(password)
                .build();
        String res =  hashService.computeHash(request).toHex();
        System.out.println(res);
        return res;
    }

    public static Set<String> getPermissions() {
        ShiroRealmBackAdmin realm = (ShiroRealmBackAdmin) getRealm();
        return realm == null ? new HashSet<>() : realm.getPermissions();
    }
}
