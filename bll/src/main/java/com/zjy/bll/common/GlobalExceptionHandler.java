package com.zjy.bll.common;

import com.alibaba.fastjson.JSON;
import com.zjy.baseframework.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        boolean isAjax = "XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"));

        if (isAjax && false) {
            response.setStatus(HttpStatus.OK.value()); //设置状态码
            response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType
            response.setHeader("Cache-Control", "no-cache, must-revalidate");
            try {
                BaseResult<String> result = BaseResult.ERROR(ex.getMessage());
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
