package com.zjy.bll.common;

import com.alibaba.fastjson.JSON;
import com.zjy.baseframework.BaseResult;
import com.zjy.baseframework.ServiceException;
import com.zjy.bll.annotations.LogMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 日志拦截，要在spring-mvc.xml中添加<aop:aspectj-autoproxy proxy-target-class="true"/>
 */
@Aspect
@Component
public class ControllerAspect {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    private static Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);
    protected static Logger operationLogger = LoggerFactory.getLogger("dbLogger");

    private static final String USER_EXP = "\\{user\\}";
    private static final String METHOD_EXP = "\\{method\\}";
    private static final String NEW_LINE = System.getProperty("line.separator", "\r\n");
    public static final String LOG_PARAMETER = "log_parameter";
    public static final String START_TIME = "__startTime";
    public static final String KEY_PARTTERN = "{%s}";

    /**
     * Controller层切点
     */
//    @Pointcut("@annotation(com.zjy.bll.annotations.LogMessage)")
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) || @annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void controllerAspect() {
    }

    /**
     * handle执行前置事件
     *
     * @param joinPoint
     */
    @Before("controllerAspect()")
    public void before(JoinPoint joinPoint) {
        request.setAttribute(START_TIME, System.currentTimeMillis());
    }

    /**
     * handle执行未抛异常后置事件
     *
     * @param joinPoint
     * @param ret
     */
    @AfterReturning(pointcut = "controllerAspect()", returning = "ret")
    public void afterReturning(JoinPoint joinPoint, Object ret) {
        Method method = getMethod(joinPoint);
        LogMessage annotation = method.getAnnotation(LogMessage.class);
        if(annotation == null || annotation.doLog()) {
            logRequest(request, response, getMethod(joinPoint), ret);
        }
    }

    /**
     * 抛出异常后处理事件
     *
     * @param joinPoint
     * @return
     */
//    @AfterThrowing(pointcut = "controllerAspect()", throwing = "ex")
//    public void AfterThrowing(JoinPoint joinPoint, Exception ex) {
//        logException(request, response, getMethod(joinPoint), ex);
//    }

    /**
     * 获取joinPoint拦截的方法
     *
     * @param joinPoint
     * @return
     */
    private Method getMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Method[] methods = joinPoint.getTarget().getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazz = method.getParameterTypes();
                if (clazz.length == arguments.length) {
                    return method;
                }
            }
        }
        return null;
    }

    /**
     * 记录异常日志
     *
     * @param request
     * @param response
     * @param method
     * @param ex
     */
    public static void logException(HttpServletRequest request, HttpServletResponse response, Method method, Exception ex) {
        response.reset();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache, must-revalidate");
        try {
            if (ex instanceof ServiceException) {
                Map<String, String> warnMsg = getWarnMsg(ex, request, method);
                response.getWriter().write(JSON.toJSONString(BaseResult.no(warnMsg.get("msg"))));
                String msg = warnMsg.get("msgLog") + "。" + NEW_LINE + "请求信息" + NEW_LINE + getRequestInfoStr(request, method);
                operationLogger.warn(msg, method);
                logger.warn(msg, ex);
            } else if (ex instanceof UnauthorizedException) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            } else if (ex instanceof UnauthenticatedException) {
                response.setStatus(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.value());
            } else {
                Map<String, String> warnMsg = getErrorMsg(ex, request, method);
                response.getWriter().write(JSON.toJSONString(BaseResult.error(warnMsg.get("msg"))));
                String msg = warnMsg.get("msgLog") + "。" + NEW_LINE + "请求信息" + NEW_LINE + getRequestInfoStr(request, method);
                operationLogger.error(msg, method);
                logger.error(msg, ex);
            }
        } catch (IOException e) {
            logger.error("系统错误", e);
        }
    }

    /**
     * 记录请求日志，不包括异常
     *
     * @param request
     * @param response
     * @param method
     * @param result
     */
    protected void logRequest(HttpServletRequest request, HttpServletResponse response, Method method, Object result) {
        StringBuilder sb = new StringBuilder(200);
        sb.append(NEW_LINE + getRequestInfoStr(request, method));
        if (result != null) {
            String msg = (result instanceof String) ? (String) result : JSON.toJSONString(result);
            sb.append("return: ").append(msg);
        }
        operationLogger.info(sb.toString(), method);
        logger.info(sb.toString());
    }

    /**
     * 获取请求相关信息String，如url, 参数
     *
     * @param request
     * @param method
     * @return
     */
    private static String getRequestInfoStr(HttpServletRequest request, Method method) {
        Map<String, Object> requestInfo = getRequestInfo(request, method);
        StringBuilder sb = new StringBuilder(200);
        for (Map.Entry<String, Object> entry : requestInfo.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(Objects.toString(entry.getValue(), StringUtils.EMPTY)).append(NEW_LINE);
        }
        return sb.toString();
    }

    /**
     * 获取请求相关信息map，如url, 参数
     *
     * @param request
     * @param method
     * @return
     */
    private static Map<String, Object> getRequestInfo(HttpServletRequest request, Method method) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("URI", request.getRequestURI());
        map.put("method", method.getDeclaringClass().getName() + "." + method.getName());
        map.put("params", getParamString(request.getParameterMap()));
        long duration = 0L;
        if (request.getAttribute(START_TIME) != null) {
            duration = System.currentTimeMillis() - ((long) request.getAttribute(START_TIME));
            DecimalFormat df = new DecimalFormat("###,##0");
            map.put("duration", duration == 0 ? "-" : df.format(duration) + " ms");
        }
        return map;
    }

    /**
     * 获取请求参数信息
     *
     * @param map
     * @return
     */
    public static String getParamString(Map<String, String[]> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String[]> e : map.entrySet()) {
            sb.append(e.getKey()).append("=");
            String[] value = e.getValue();
            if (value != null && value.length == 1) {
                sb.append(value[0]).append("\t");
            } else {
                sb.append(Arrays.toString(value)).append("\t");
            }
        }
        return sb.toString();
    }

    /**
     * 获取异常message
     *
     * @param ex
     * @return
     */
    private static String getExceptionDefaultMsg(Exception ex) {
        String message;
        if (ExceptionUtils.getRootCause(ex) != null) {
            message = ExceptionUtils.getRootCause(ex).getMessage();
        } else {
            message = ex.getMessage();
        }
        return StringUtils.defaultIfBlank(message, ex.toString());
    }

    /**
     * 获取serviceexception信息
     *
     * @param ex
     * @param request
     * @param method
     * @return
     */
    private static Map<String, String> getWarnMsg(Exception ex, HttpServletRequest request, Method method) {
        Map<String, String> msgMap = new HashMap<>();
        String defaultMsg = getExceptionDefaultMsg(ex);
        String msg = StringUtils.EMPTY;
        String msgLog = StringUtils.EMPTY;
        if (StringUtils.isBlank(msg)) msg = defaultMsg;
        if (StringUtils.isBlank(msgLog)) msgLog = defaultMsg;
        msgMap.put("msg", msg);
        msgMap.put("msgLog", msgLog);
        return msgMap;
    }

    /**
     * 获取error信息
     *
     * @param ex
     * @param request
     * @param method
     * @return
     */
    private static Map<String, String> getErrorMsg(Exception ex, HttpServletRequest request, Method method) {
        Map<String, String> msgMap = new HashMap<>();
        String defaultMsg = getExceptionDefaultMsg(ex);
        String msg = StringUtils.EMPTY;
        String msgLog = StringUtils.EMPTY;
        if (StringUtils.isBlank(msg)) msg = defaultMsg;
        if (StringUtils.isBlank(msgLog)) msgLog = defaultMsg;
        msgMap.put("msg", msg);
        msgMap.put("msgLog", msgLog);
        return msgMap;
    }

    /**
     * 替换占位字符
     *
     * @param str
     * @param user
     * @param method
     * @return
     */
    private String replaceStr(String str, String user, String method) {
        if (!StringUtils.isNotBlank(str)) return str;
        str = str.replaceAll(USER_EXP, user).replaceAll(METHOD_EXP, method);
        Object t = request.getAttribute(LOG_PARAMETER);
        if (t != null) {
            for (Map.Entry<String, String> entry : ((Map<String, String>) t).entrySet()) {
                str = str.replace(String.format(KEY_PARTTERN, entry.getKey()), entry.getValue());
            }
        }
        return str;
    }

    /**
     * 设置记录日志相关替换参数
     *
     * @param key
     * @param value
     */
    protected void setLogParameter(String key, String value) {
        Map<String, String> map = (Map<String, String>) request.getAttribute(LOG_PARAMETER);
        if (map == null) {
            map = new HashMap<>();
            request.setAttribute(LOG_PARAMETER, map);
        }
        map.put(key, value);
    }
}
