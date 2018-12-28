package com.zjy.baseframework;

/**
 * Created by Administrator on 2018/12/22.
 */
public final class KeyHelper {

    private KeyHelper() {}
    private static final String sys = "TSM";

    public static final String UserRoleListKey = "URL_%s";

    public static final String UserPermissionListKey = "UPL_%s";

    public static String getTsmKey(String busKey, String... param) {
        return String.format("%s_%s", sys, String.format(busKey, param));
    }
}
