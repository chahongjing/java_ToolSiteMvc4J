package com.zjy.bll.common;

import com.alibaba.fastjson.JSON;
import com.zjy.baseframework.BaseResult;
import com.zjy.baseframework.ServiceException;
import com.zjy.baseframework.enums.ResultStatus;
import com.zjy.bll.annotations.LogMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 全局异常处理
 *
 * @author chahongjing
 * @create 2016-12-10 17:45
 */
@ControllerAdvice
public class MyControllerAdvice {
    private Logger logger = LoggerFactory.getLogger(MyControllerAdvice.class);

    /**
     * 全局异常处理机制
     *
     * @param request
     * @param response
     * @param ex
     * @return
     */
    @ExceptionHandler
//    @ResponseBody
    public ModelAndView processException(NativeWebRequest request, HttpServletResponse response, Exception ex, HandlerMethod handler) {
        ModelAndView mv = new ModelAndView();

//        Method method = handler.getMethod();
//        LogMessage logMessage = method.getAnnotation(LogMessage.class);
//        Map<String, String> msg = handleMessage(request, method, logMessage, ex.getMessage());
//        String errorMsg = msg.get("errorMsg");
//        String errorMsgLog = msg.get("errorMsgLog");
//        String warningMsg = msg.get("warningMsg");
//        String warningMsgLog = msg.get("warningMsgLog");

        if (WebUtils.isAjax(request)) {
            if (ex instanceof UnauthorizedException) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return mv;
            }
            response.setStatus(HttpStatus.OK.value()); //设置状态码
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE); //设置ContentType
            response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache, must-revalidate");
            try {
                String message;
                if (ExceptionUtils.getRootCause(ex) != null) {
                    message = ExceptionUtils.getRootCause(ex).getMessage();
                } else {
                    message = ex.getMessage();
                }
                BaseResult<String> result = BaseResult.no(Objects.toString(message, StringUtils.EMPTY));
                if (!(ex instanceof ServiceException)) {
                    logger.error("系统错误！", ex);
                    result.setStatus(ResultStatus.ERROR);
                }
                response.getWriter().write(JSON.toJSONString(result));
            } catch (IOException e) {
                logger.error("处理异常信息失败!", e);
            }
        } else {
            logger.error("系统错误", ex);
            mv.setViewName("common/error");
            response.setStatus(HttpStatus.NOT_FOUND.value()); //设置状态码
        }
        return mv;
    }

    /**
     * 没有登录时处理，shiro框架已处理，在自定义授权中会自动返回未登录状态码，不会走这里
     *
     * @param request
     * @param response
     * @param ex
     * @return
     */
    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseStatus(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED)
    public ModelAndView processUnauthenticatedException(NativeWebRequest request, HttpServletResponse response, UnauthenticatedException ex) {
        return null;
    }

    /**
     * 没有权限时处理
     *
     * @param request
     * @param response
     * @param ex
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView processUnauthorizedException(NativeWebRequest request, HttpServletResponse response, UnauthorizedException ex) {
        return null;
    }

    /**
     * 渲染输出信息模板
     *
     * @param request
     * @param method
     * @param message
     * @param expMsg
     * @return
     */
    private Map<String, String> handleMessage(NativeWebRequest request, Method method, LogMessage message, String expMsg) {
        Map<String, String> map = new HashMap<>();
//        if (message == null) return map;
//        String errorMsg = StringUtils.isBlank(message.errorMsgStr()) ? MessageConstants.getMsg(message.errorMsg()) : message.errorMsgStr();
//        String errorMsgLog = StringUtils.isBlank(message.errorMsgLogStr()) ? MessageConstants.getMsg(message.errorMsgLog()) : message.errorMsgLogStr();
//        String warningMsg = StringUtils.isBlank(message.warningMsgStr()) ? MessageConstants.getMsg(message.warningMsg()) : message.warningMsgStr();
//        String warningMsgLog = StringUtils.isBlank(message.warningMsgLogStr()) ? MessageConstants.getMsg(message.warningMsgLog()) : message.warningMsgLogStr();
//        Dq_YongHu yongHuDto = YongHuUtils.getYongHu();
//        String dengluming = yongHuDto == null ? "-" : yongHuDto.getDengluming();
//        String methodInfo = method.getDeclaringClass().toString().replace("class ", StringUtils.EMPTY) + "." + method.getName();
//
//        errorMsg = replaceStr(request, errorMsg, dengluming, methodInfo, expMsg);
//        errorMsgLog = replaceStr(request, errorMsgLog, dengluming, methodInfo, expMsg);
//        warningMsg = replaceStr(request, warningMsg, dengluming, methodInfo, expMsg);
//        warningMsgLog = replaceStr(request, warningMsgLog, dengluming, methodInfo, expMsg);
//        request.removeAttribute(BaseController.LOG_PARAMETER, WebRequest.SCOPE_REQUEST);
//
//        map.put("errorMsg", errorMsg);
//        map.put("errorMsgLog", errorMsgLog);
//        map.put("warningMsg", warningMsg);
//        map.put("warningMsgLog", warningMsgLog);

        return map;
    }

    /**
     * 替换点位符
     *
     * @param request
     * @param str
     * @param user
     * @param method
     * @param expMsg
     * @return
     */
//    private String replaceStr(NativeWebRequest request, String str, String user, String method, String expMsg) {
//        if (!StringUtils.isNotBlank(str)) return str;
//        str = str.replaceAll(USER_EXP, user).replaceAll(METHOD_EXP, method).replaceAll(EXPMSG_EXP, expMsg);
//        Object t = request.getAttribute(BaseController.LOG_PARAMETER, WebRequest.SCOPE_REQUEST);
//        if (t != null) {
//            for (Map.Entry<String, String> entry : ((Map<String, String>) t).entrySet()) {
//                str = str.replace("{" + entry.getKey() + "}", entry.getValue());
//            }
//        }
//        return str;
//    }
}
