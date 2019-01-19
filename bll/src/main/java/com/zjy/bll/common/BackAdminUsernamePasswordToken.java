package com.zjy.bll.common;

import org.apache.shiro.authc.UsernamePasswordToken;

public class BackAdminUsernamePasswordToken extends UsernamePasswordToken {
    public BackAdminUsernamePasswordToken(String username, String password) {
        super(username, password);
    }
}
