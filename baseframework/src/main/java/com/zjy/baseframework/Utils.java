package com.zjy.baseframework;

import org.apache.commons.lang3.StringUtils;

import java.nio.file.Paths;

/**
 * Created by chahongjing on 2017/3/14.
 */
public class Utils {
    public static String getRootPath() {
        //return ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
        return Paths.get(StringUtils.removeStart(Utils.class.getClassLoader().getResource("/").getFile(), "/")).getParent().getParent().toAbsolutePath().toString();
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
