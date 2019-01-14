package com.zjy.bll.common;

import com.zjy.baseframework.CookieHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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

    private static String shiroSessionIdCookieName = "JSESSIONID";

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String jsessionId = null;
        HttpServletRequest rq = WebUtils.toHttp(request);
        HttpServletResponse rs = WebUtils.toHttp(response);

        jsessionId = rq.getHeader(shiroSessionIdCookieName);
        if(StringUtils.isBlank(jsessionId)) {
            jsessionId = CookieHelper.getCookie(rq, shiroSessionIdCookieName);
        }
        if(StringUtils.isBlank(jsessionId)) {
            jsessionId = CookieHelper.getCookie(rq, shiroSessionIdCookieName);
        }
        if(StringUtils.isBlank(jsessionId)) {
            jsessionId = rq.getParameter(shiroSessionIdCookieName);
        }

        if (StringUtils.isNotBlank(jsessionId)) {
            // 设置当前session状态
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, ShiroHttpServletRequest.URL_SESSION_ID_SOURCE); // session来源与url
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, jsessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, true);
            return jsessionId;
        } else {
            return super.getSessionId(request, response);
        }
    }
}