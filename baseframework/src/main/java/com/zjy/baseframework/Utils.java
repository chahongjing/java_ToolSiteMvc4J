package com.zjy.baseframework;

import java.nio.file.Paths;

/**
 * Created by chahongjing on 2017/3/14.
 */
public class Utils {
    private Utils() {
    }

    public static String getRootPath() {
        //return ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
//        return Paths.get(StringUtils.removeStart(Utils.class.getClassLoader().getResource("/").getFile(), "/")).getParent().getParent().toString();
        return Paths.get(Utils.class.getClassLoader().getResource("/").getFile()).getParent().getParent().toString();
    }
}
