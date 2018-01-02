package com.zjy.baseframework;

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
    public static boolean addCookie(HttpServletResponse response, String key, String value) {
        try {
            value = URLEncoder.encode(value, StandardCharsets.UTF_8.name());
            Cookie cookie = new Cookie(key, value);
            response.addCookie(cookie);
            return true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getCookie(HttpServletRequest request, String key) {
        String value = "";
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
