package com.zjy.bll.common;

import com.zjy.baseframework.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2018/7/31.
 */

public class CorsFilter implements Filter {

    private String allowOrigin;
    private String allowMethods;
    private String allowCredentials;
    private String allowHeaders;
    private String exposeHeaders;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowOrigin = filterConfig.getInitParameter("allowOrigin");
        allowMethods = filterConfig.getInitParameter("allowMethods");
        allowCredentials = filterConfig.getInitParameter("allowCredentials");
        allowHeaders = filterConfig.getInitParameter("allowHeaders");
        exposeHeaders = filterConfig.getInitParameter("exposeHeaders");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        if (StringUtils.isNotEmpty(allowOrigin)) {
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, allowOrigin);
        }
        if (StringUtils.isNotEmpty(allowMethods)) {
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, allowMethods);
        }
        if (StringUtils.isNotEmpty(allowCredentials)) {
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, allowCredentials);
        }
        if (StringUtils.isNotEmpty(allowHeaders)) {
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, allowHeaders);
        }
        if (StringUtils.isNotEmpty(exposeHeaders)) {
            response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, exposeHeaders);
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        throw new ServiceException(StringUtils.EMPTY);
    }
}