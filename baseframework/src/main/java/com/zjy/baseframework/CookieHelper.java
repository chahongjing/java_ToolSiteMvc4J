package com.zjy.baseframework;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Created by chahongjing on 2017/10/12.
 */
public class CookieHelper {
    private CookieHelper() {}
    public static boolean addCookie(HttpServletResponse response, String key, String value) {
        return addCookie(response, key, value, null);
    }

    public static boolean addCookie(HttpServletResponse response, String key, String value, String path) {
        try {
            value = URLEncoder.encode(value, StandardCharsets.UTF_8.name());
            Cookie cookie = new Cookie(key, value);
            if(StringUtils.isNoneBlank(path)) cookie.setPath(path.trim());
            response.addCookie(cookie);
            return true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getCookie(HttpServletRequest request, String key) {
        String value = StringUtils.EMPTY;
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(key)) {
                        value = cookie.getValue();
                        if (value != null) {
                            value = URLDecoder.decode(value, StandardCharsets.UTF_8.name());
                        }
                    }
                }
            }
            return value;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String key) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    cookie.setValue(null);
                    // 0: 设置cookie过期，正数：过期时间（秒），负数：保存在内存中，浏览器关闭cookie过期
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }
}
