package com.zjy.bll.common;

import org.apache.shiro.authc.UsernamePasswordToken;

public class FrontAdminUsernamePasswordToken extends UsernamePasswordToken {
    public FrontAdminUsernamePasswordToken(String username, String password) {
        super(username, password);
    }
}
