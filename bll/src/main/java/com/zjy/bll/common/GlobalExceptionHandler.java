package com.zjy.bll.common;

import com.alibaba.fastjson.JSON;
import com.zjy.baseframework.BaseResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author chahongjing
 * @create 2016-12-10 17:45
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler()
    public ModelAndView processException(NativeWebRequest request, HttpServletResponse response, Exception ex) {
        ModelAndView mv = new ModelAndView();
        if (WebUtils.isAjax((HttpServletRequest) request) && false) {
            response.setStatus(HttpStatus.OK.value()); //设置状态码
            response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType
            response.setHeader("Cache-Control", "no-cache, must-revalidate");
            try {
                String message;
                if (ExceptionUtils.getRootCause(ex) != null) {
                    message = ExceptionUtils.getRootCause(ex).getMessage();
                } else {
                    message = ex.getMessage();
                }
                BaseResult<String> result = BaseResult.ERROR(Objects.toString(message, StringUtils.EMPTY));
                response.getWriter().write(JSON.toJSONString(result));
            } catch (IOException e) {
            }
        } else {
            logger.error("系统错误", ex);
            ex.printStackTrace();
            mv.setViewName("common/error");
            response.setStatus(HttpStatus.NOT_FOUND.value()); //设置状态码
        }
        return mv;
    }
}
