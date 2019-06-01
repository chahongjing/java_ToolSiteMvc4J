package com.zjy.bll.common;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 记录信息:</br> 访问时间</br>Controller路径</br>对应方法名</br>请求参数信息</br>请求相对路径</br>请求处理时长
 * HandlerMethodReturnValueHandler
 *
 * @author Administrator
 */
public class ControllerInterceptor implements HandlerInterceptor {
    /**
     * before the actual handler will be executed
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    /**
     * after the handler is executed with no exception
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        long startTime = (long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        if (handler instanceof HandlerMethod) {
            StringBuilder sb = new StringBuilder(1000);
            HandlerMethod h = (HandlerMethod) handler;
            sb.append("URI      : ").append(request.getRequestURI()).append("\n");
            sb.append("Method   : ").append(h.getBeanType().getName()).append(".").append(h.getMethod().getName()).append("\n");
            sb.append("Params   : ").append(ControllerAspect.getParamString(request.getParameterMap())).append("\n");
            sb.append("CostTime : ").append(executeTime).append("ms").append("\n");
            sb.append("-------------------------------------------------------------------------------");
//            System.out.println(sb.toString());
        }
    }

    /**
     * after the handler is executed no matter whether there is exception
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 有异常
//        if(ex != null || (request.getAttribute("isHandleException") != null && (boolean)request.getAttribute("isHandleException"))) {
////            handException(request, response, handler, ex);
//            return;
//        }
//        System.out.println("正常返回");
        // 正常返回
    }
}