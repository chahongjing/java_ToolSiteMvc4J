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

    public static String getFileExtension(String fileName) {
        if(StringUtils.isBlank(fileName) || fileName.indexOf('.') == -1) return StringUtils.EMPTY;
        return fileName.substring(fileName.lastIndexOf('.'));
    }
}
