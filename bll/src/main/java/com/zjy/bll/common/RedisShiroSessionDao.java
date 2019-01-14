package com.zjy.bll.common;

import com.zjy.baseframework.CacheHelper;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

public class RedisShiroSessionDao extends AbstractSessionDAO {

    @Autowired
    private CacheHelper cacheHelper;

    Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void update(Session session) {
        try {
            cacheHelper.set(session.getId().toString(), session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Session session) {
        try {
            cacheHelper.delete(session.getId().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Collection<Session> getActiveSessions() {
        return Collections.emptySet();
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        try {
            cacheHelper.set(sessionId.toString(), session);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = null;
        try {
            session = (Session) cacheHelper.get(sessionId.toString());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return session;
    }
}
