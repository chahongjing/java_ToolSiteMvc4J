package com.zjy.bll.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 *
 * @author chahongjing
 * @create 2016-12-10 17:45
 */
@Deprecated
@ControllerAdvice
public class MyControllerAdvice {

    /**
     * 全局异常处理
     *
     * @param request
     * @param response
     * @param ex
     * @return
     */
    @ExceptionHandler
//    @ResponseBody
    public ModelAndView processException(HttpServletRequest request, HttpServletResponse response, Exception ex, HandlerMethod handler) {
        ModelAndView mv = new ModelAndView();
        if (WebUtils.isAjax(request)) {
            ControllerAspect.logException(request, response, handler.getMethod(), ex);
        } else {
            mv.setViewName("common/error");
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
//    @ExceptionHandler(UnauthenticatedException.class)
//    @ResponseStatus(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED)
//    public ModelAndView processUnauthenticatedException(HttpServletRequest request, HttpServletResponse response, UnauthenticatedException ex) {
//        return null;
//    }

    /**
     * 没有权限时处理
     *
     * @param request
     * @param response
     * @param ex
     * @return
     */
//    @ExceptionHandler(UnauthorizedException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public ModelAndView processUnauthorizedException(HttpServletRequest request, HttpServletResponse response, UnauthorizedException ex) {
//        return null;
//    }
}
