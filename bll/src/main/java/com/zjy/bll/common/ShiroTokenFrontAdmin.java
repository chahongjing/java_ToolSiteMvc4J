package com.zjy.bll.common;

import org.apache.shiro.authc.UsernamePasswordToken;

public class ShiroTokenFrontAdmin extends UsernamePasswordToken {
    public ShiroTokenFrontAdmin(String username, String password) {
        super(username, password);
    }
}
