package com.zjy.bll.common;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {

    public WebApplicationContext getWebApplicationContext() {
        return ContextLoader.getCurrentWebApplicationContext();
    }

    public static String getWebApplicationPath() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (request.getRequestURL().toString().replace(request.getRequestURI().toString(), "") + request.getContextPath());
    }

    public static boolean isAjax(WebRequest request) {
        return request!= null && isAjax(request.getHeader("X-Requested-With"));
    }

    public static boolean isAjax(HttpServletRequest request) {
        return request!= null && isAjax(request.getHeader("X-Requested-With"));
    }

    public static boolean isAjax(String ajaxHeader) {
        return "XMLHttpRequest".equalsIgnoreCase(ajaxHeader);
    }
}
