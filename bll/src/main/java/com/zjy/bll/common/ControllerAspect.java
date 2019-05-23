package com.zjy.bll.common;

import com.zjy.bll.annotations.LogLevel;
import com.zjy.bll.annotations.LogMessage;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志拦截，要在spring-mvc.xml中添加<aop:aspectj-autoproxy proxy-target-class="true"/>
 */
@Aspect
@Component
public class ControllerAspect {
    public static final String LOG_PARAMETER = "log_parameter";

    @Autowired
    private HttpServletRequest request;

    protected Logger oprationLogger = LoggerFactory.getLogger("OPRATION");

    private static final String USER_EXP = "\\{user\\}";
    private static final String METHOD_EXP = "\\{method\\}";

    //Controller层切点
    @Pointcut("@annotation(com.zjy.bll.annotations.LogMessage)")
    public void controllerAspect() {
    }

    @AfterReturning(pointcut = "controllerAspect()")
    public void afterReturning(JoinPoint joinPoint) {
        Method method = getMethod(joinPoint);
        if (method == null) return;
        LogMessage annotation = method.getAnnotation(LogMessage.class);
        if (annotation == null) return;
        if (StringUtils.isBlank(annotation.successMsg())) return;
        LogLevel level = method.getAnnotation(LogLevel.class);
        if (level == null) {
            level = method.getDeclaringClass().getAnnotation(LogLevel.class);
        }
        String moKuai;
        if (level == null) {
            moKuai = StringUtils.EMPTY;
        } else {
            moKuai = level.value();
        }
//        Dq_YongHu yongHuDto = YongHuUtils.getYongHu();
//        String dengluming = yongHuDto == null ? "-" : yongHuDto.getDengluming();
        String dengluming = "";
        String msg = replaceStr(annotation.successMsg(), dengluming, method.getName());
        oprationLogger.info(msg, method, moKuai);
    }

    public static Method getMethod(JoinPoint joinPoint) {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass;
        try {
            targetClass = Class.forName(targetName);
        } catch (ClassNotFoundException e) {
            return null;
        }
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    return method;
                }
            }
        }
        return null;
    }

    private String replaceStr(String str, String user, String method) {
        if (!StringUtils.isNotBlank(str)) return str;
        str = str.replaceAll(USER_EXP, user).replaceAll(METHOD_EXP, method);
        Object t = request.getAttribute("log_parameter");
        if (t != null) {
            for (Map.Entry<String, String> entry : ((Map<String, String>) t).entrySet()) {
                str = str.replace("{" + entry.getKey() + "}", entry.getValue());
            }
        }
        return str;
    }

    protected void setLogParameter(String key, String value) {
        Map<String, String> map = (Map<String, String>) request.getAttribute(LOG_PARAMETER);
        if (map == null) {
            map = new HashMap<>();
            request.setAttribute(LOG_PARAMETER, map);
        }
        map.put(key, value);
    }
}
