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
        String jsessionid = request.getParameter(SHIRO_SESSIONID_COOKIE_NAME);
        //logger.info("SessionManager::getSessionId::getParameter:sid={}", sid);
        HttpServletRequest rq = (HttpServletRequest) request;
        HttpServletResponse rs = (HttpServletResponse) response;

        Cookie[] cookies = rq.getCookies();
        if (cookies != null) {
            for (int a = 0; a < cookies.length; a++) {
                Cookie c = cookies[a];
                if (c.getName().equals(SHIRO_SESSIONID_COOKIE_NAME)) {
                    jsessionid = c.getValue();
                }
            }
        }

        if (StringUtils.isBlank(jsessionid)) {
            jsessionid = rq.getHeader(SHIRO_SESSIONID_COOKIE_NAME);
        }

        if (StringUtils.isBlank(jsessionid)) {
            jsessionid = rq.getParameter(SHIRO_SESSIONID_COOKIE_NAME);
            if (StringUtils.isBlank(jsessionid)) {
                return super.getSessionId(request, response);
            }
        }

        Cookie cookie = new Cookie(SHIRO_SESSIONID_COOKIE_NAME, jsessionid);
        rs.addCookie(cookie);
        // 设置当前session状态
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, ShiroHttpServletRequest.URL_SESSION_ID_SOURCE); // session来源与url
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, jsessionid);
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, true);
        return jsessionid;
    }
}