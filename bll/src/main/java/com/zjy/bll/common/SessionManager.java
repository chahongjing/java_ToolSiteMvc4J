package com.zjy.bll.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Arrays;

/**
 * <b>类 名：</b>SessionManager<br/>
 * <b>类描述：</b>自定义WEB会话管理类<br/>
 * <b>创建人：</b>mailto:haoshen3@iflytek.com<br/>
 * <b>创建时间：</b>2016年8月31日 下午6:30:28<br/>
 * <b>修改人：</b>mailto:haoshen3@iflytek.com<br/>
 * <b>修改时间：</b>2016年8月31日 下午6:30:28<br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0<br />
 */
public class SessionManager extends DefaultWebSessionManager {

    static String SHIRO_SESSIONID_COOKIE_NAME = "JSESSIONID";

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        // 如果header中包含“”参数，则使用此sid会话
        String jsessionId = null;
        //logger.info("SessionManager::getSessionId::getParameter:sid={}", sid);
        HttpServletRequest rq = (HttpServletRequest) request;
        HttpServletResponse rs = (HttpServletResponse) response;

        Cookie[] cookies = rq.getCookies();
        if (cookies != null) {
            Cookie cookie = Arrays.stream(cookies).filter(item -> item.getName().equalsIgnoreCase(SHIRO_SESSIONID_COOKIE_NAME)).findFirst().orElse(null);
            if(cookie != null) jsessionId = cookie.getValue();
        }

        if (StringUtils.isNotBlank(jsessionId)) {
            return super.getSessionId(request, response);
        }
        jsessionId = rq.getHeader(SHIRO_SESSIONID_COOKIE_NAME);

        if (StringUtils.isBlank(jsessionId)) {
            jsessionId = rq.getParameter(SHIRO_SESSIONID_COOKIE_NAME);
            if (StringUtils.isBlank(jsessionId)) {
                return super.getSessionId(request, response);
            }
        }

        Cookie cookie = new Cookie(SHIRO_SESSIONID_COOKIE_NAME, jsessionId);
        rs.addCookie(cookie);
        // 设置当前session状态
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, ShiroHttpServletRequest.URL_SESSION_ID_SOURCE); // session来源与url
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, jsessionId);
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, true);
        return jsessionId;
    }
}