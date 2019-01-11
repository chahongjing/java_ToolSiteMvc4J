package com.zjy.bll.common;

import com.alibaba.fastjson.JSON;
import com.zjy.baseframework.BaseResult;
import com.zjy.baseframework.ServiceException;
import com.zjy.baseframework.enums.ResultStatus;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public ModelAndView processException(NativeWebRequest request, HttpServletResponse response, Exception ex) {
        ModelAndView mv = new ModelAndView();
        if (WebUtils.isAjax(request)) {
            if (ex instanceof UnauthorizedException) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return mv;
            }
            response.setStatus(HttpStatus.OK.value()); //设置状态码
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE); //设置ContentType
            response.setHeader("Cache-Control", "no-cache, must-revalidate");
            try {
                String message;
                if (ExceptionUtils.getRootCause(ex) != null) {
                    message = ExceptionUtils.getRootCause(ex).getMessage();
                } else {
                    message = ex.getMessage();
                }
                BaseResult<String> result = BaseResult.NO(Objects.toString(message, StringUtils.EMPTY));
                if(ex instanceof ServiceException) {
                    result.setStatus(ResultStatus.ERROR);
                }
                response.getWriter().write(JSON.toJSONString(result));
            } catch (IOException e) {
                logger.error("处理异常信息失败", e);
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
}
