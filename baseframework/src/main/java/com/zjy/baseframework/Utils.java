package com.zjy.baseframework;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Paths;

/**
 * Created by chahongjing on 2017/3/14.
 */
public class Utils {
    public static String getRootPath() {
        //return ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
        return Paths.get(StringUtils.removeStart(Utils.class.getClassLoader().getResource("/").getFile(), "/")).getParent().getParent().toAbsolutePath().toString();
    }

    public static String getWebApplicationPath() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (request.getRequestURL().toString().replace(request.getRequestURI().toString(), "") +  request.getContextPath());
    }

    /**
     * 获取文件后缀(包括点号)
     * @param fileName 文件名
     * @return
     */
    public static String getExtension(String fileName) {
        if(StringUtils.isBlank(fileName) || fileName.indexOf('.') == -1) return StringUtils.EMPTY;
        return fileName.substring(fileName.lastIndexOf('.'));
    }
}
