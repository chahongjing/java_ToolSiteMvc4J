package com.zjy.baseframework;

import java.util.regex.Pattern;

public class CommonReg {
    // 中文，字母，数字，/和-
    public static Pattern aa = Pattern.compile("^[\\u4e00-\\u9fa5_a-zA-Z0-9/\\-]*$");
}
