package com.zjy.baseframework;

/**
 * 环境助手
 *
 * @author junyi.zeng@dmall.com
 * @date 2019-07-31 10:44:11
 */
public class EnvironmentHelper {
    public static final String LINE_SEPARATOR = "line.separator";
    public static final String FILE_SEPARATOR = "file.separator";
    public static final String PATH_SEPARATOR = "path.separator";

    /**
     * 获取换行符
     * @param:
     * @return: java.lang.String
     * @author: junyi.zeng@dmall.com
     * @date: 2019-07-31 10:52:20
     */
    public static String getSysNewLine() {
        return System.getProperty(LINE_SEPARATOR, "\r\n");
    }
    public static String getEnv(String key) {
//        key = "USERPROFILE";
        return System.getenv(key);
    }
    /**
     * 文件和目录中的分隔符
     * @param:
     * @return: java.lang.String
     * @author: junyi.zeng@dmall.com
     * @date: 2019-07-31 10:52:09
     */
    public static String getFileSeparator() {
        return System.getProperty(FILE_SEPARATOR, "/");
    }
    /**
     * 环境变量中路径之间的分隔符
     * @param:
     * @return: java.lang.String
     * @author: junyi.zeng@dmall.com
     * @date: 2019-07-31 10:51:55
     */
    public static String getPathSeparator() {
        return System.getProperty(PATH_SEPARATOR, ";");
    }
}
