package com.iflytek.core.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * <b>类 名：</b>SessionManager<br/>
 * <b>类描述：</b>自定义WEB会话管理类<br/>
 * <b>创建人：</b>mailto:haoshen3@iflytek.com<br/>
 * <b>创建时间：</b>2016年8月31日 下午6:30:28<br/>
 * <b>修改人：</b>mailto:haoshen3@iflytek.com<br/>
 * <b>修改时间：</b>2016年8月31日 下午6:30:28<br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0<br/>
 */
public class SessionManager extends DefaultWebSessionManager {

    static String SHIRO_SESSIONID_COOKIE_NAME = "jsessionid";
    private Logger logger = LoggerFactory.getLogger(getClass());

    public SessionManager() {
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        // 如果header中包含“libra_sid”参数，则使用此sid会话
        String sid = request.getParameter(SHIRO_SESSIONID_COOKIE_NAME);
        //logger.info("SessionManager::getSessionId::getParameter:sid={}", sid);
        HttpServletRequest rq = (HttpServletRequest) request;
        HttpServletResponse rs = (HttpServletResponse) response;

        Cookie[] cookies = rq.getCookies();
        if (cookies != null) {
            for (int a = 0; a < cookies.length; a++) {
//                logger.info("SessionManager::getSessionId::Cookie:name={}，value={}", cookies[a].getName(), cookies[a].getValue());
                Cookie c = cookies[a];
                if (c.getName().equals(SHIRO_SESSIONID_COOKIE_NAME)) {
                    sid = c.getValue();
                }
            }
        }

        if (StringUtils.isBlank(sid)) {
            sid = rq.getHeader(SHIRO_SESSIONID_COOKIE_NAME);
            //logger.info("SessionManager::getSessionId::getHeader:sid={}", sid);
            if (StringUtils.isBlank(sid)) {
                //logger.info("SessionManager::getSessionId::sid为空");
                return super.getSessionId(request, response);
            }
        }

        if (StringUtils.isBlank(sid)) {
            sid = rq.getParameter(SHIRO_SESSIONID_COOKIE_NAME);
            //logger.info("SessionManager::getSessionId::getHeader:sid={}", sid);
            if (StringUtils.isBlank(sid)) {
                //logger.info("SessionManager::getSessionId::sid为空");
                return super.getSessionId(request, response);
            }
        }

        Cookie cookie = new Cookie(SHIRO_SESSIONID_COOKIE_NAME, sid);
        rs.addCookie(cookie);
        // 设置当前session状态
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
                ShiroHttpServletRequest.URL_SESSION_ID_SOURCE); // session来源与url
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sid);
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
        return sid;
    }

    @Override
    public void validateSessions() {
        super.validateSessions();
    }

    protected Session retrieveSession(SessionKey sessionKey) {
        try {
            return super.retrieveSession(sessionKey);
        } catch (UnknownSessionException e) {
            // 获取不到SESSION不抛出异常
            return null;
        }
    }

    public Date getStartTimestamp(SessionKey key) {
        try {
            return super.getStartTimestamp(key);
        } catch (InvalidSessionException e) {
            // 获取不到SESSION不抛出异常
            return null;
        }
    }

    public Date getLastAccessTime(SessionKey key) {
        try {
            return super.getLastAccessTime(key);
        } catch (InvalidSessionException e) {
            // 获取不到SESSION不抛出异常
            return null;
        }
    }

    public long getTimeout(SessionKey key) {
        try {
            return super.getTimeout(key);
        } catch (InvalidSessionException e) {
            // 获取不到SESSION不抛出异常
            return 0;
        }
    }

    public void setTimeout(SessionKey key, long maxIdleTimeInMillis) {
        try {
            super.setTimeout(key, maxIdleTimeInMillis);
        } catch (InvalidSessionException e) {
            // 获取不到SESSION不抛出异常
        }
    }

    public void touch(SessionKey key) {
        try {
            super.touch(key);
        } catch (InvalidSessionException e) {
            // 获取不到SESSION不抛出异常
        }
    }

    public String getHost(SessionKey key) {
        try {
            return super.getHost(key);
        } catch (InvalidSessionException e) {
            // 获取不到SESSION不抛出异常
            return null;
        }
    }

    public Collection<Object> getAttributeKeys(SessionKey key) {
        try {
            return super.getAttributeKeys(key);
        } catch (InvalidSessionException e) {
            // 获取不到SESSION不抛出异常
            return null;
        }
    }

    public Object getAttribute(SessionKey sessionKey, Object attributeKey) {
        try {
            return super.getAttribute(sessionKey, attributeKey);
        } catch (InvalidSessionException e) {
            // 获取不到SESSION不抛出异常
            return null;
        }
    }

    public void setAttribute(SessionKey sessionKey, Object attributeKey, Object value) {
        try {
            super.setAttribute(sessionKey, attributeKey, value);
        } catch (InvalidSessionException e) {
            // 获取不到SESSION不抛出异常
        }
    }

    public Object removeAttribute(SessionKey sessionKey, Object attributeKey) {
        try {
            return super.removeAttribute(sessionKey, attributeKey);
        } catch (InvalidSessionException e) {
            // 获取不到SESSION不抛出异常
            return null;
        }
    }

    public void stop(SessionKey key) {
        try {
            super.stop(key);
        } catch (InvalidSessionException e) {
            // 获取不到SESSION不抛出异常
        }
    }

    public void checkValid(SessionKey key) {
        try {
            super.checkValid(key);
        } catch (InvalidSessionException e) {
            // 获取不到SESSION不抛出异常
        }
    }

    @Override
    protected Session doCreateSession(SessionContext context) {
        try {
            return super.doCreateSession(context);
        } catch (IllegalStateException e) {
            return null;
        }
    }

    @Override
    protected Session newSessionInstance(SessionContext context) {
        Session session = super.newSessionInstance(context);
        session.setTimeout(getGlobalSessionTimeout());
        return session;
    }

    @Override
    public Session start(SessionContext context) {
        try {
            return super.start(context);
        } catch (NullPointerException e) {
            SimpleSession session = new SimpleSession();
            session.setId(0);
            return session;
        }
    }
}