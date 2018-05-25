package com.zjy.bll.common;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                return executeLogin(request, response);
            } else {
                // 放行 allow them to see the login page ;)
                return true;
            }
        } else {
            HttpServletRequest httpRequest = WebUtils.toHttp(request);
            if (com.zjy.bll.common.WebUtils.isAjax(httpRequest)) {
                HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
                httpServletResponse.sendError(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.value());
                return false;
            } else {
                saveRequestAndRedirectToLogin(request, response);
            }

            return false;
        }
    }
}