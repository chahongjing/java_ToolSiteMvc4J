package com.zjy.baseframework;

/**
 * Created by Administrator on 2018/12/22.
 */
public final class KeyHelper {

    private KeyHelper() {}
    private static final String SYS = "TSM";

    public static final String USER_ROLE_LIST_KEY = "URL_%s";

    public static final String USER_PERMISSION_LIST_KEY = "UPL_%s";

    public static String getTsmKey(String busKey, String param) {
        return String.format("%s_%s", SYS, String.format(busKey, param));
    }
}
