package com.zjy.baseframework;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by chahongjing on 2017/3/14.
 */
public class SecurityHelper {
    public static final String MD5 = Md5Hash.ALGORITHM_NAME;

    private SecurityHelper() {
    }

    public static String md5Str(String str) {
        if (str == null) throw new IllegalArgumentException("加密的参数不能为null!");
        // 生成一个MD5加密计算摘要
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(MD5);
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

    public static String base64Decode(String value) throws Exception {
        return new String(Base64.getDecoder().decode(value), StandardCharsets.UTF_8.name());
        // return new String(Base64.decodeBase64(value.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    }

    public static String base64Encode(String value) throws Exception {
        return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8.name()));
        // new String(Base64.encodeBase64(value.getBytes()))
        //return Base64.encodeBase64String(value.getBytes(StandardCharsets.UTF_8));
    }
}
