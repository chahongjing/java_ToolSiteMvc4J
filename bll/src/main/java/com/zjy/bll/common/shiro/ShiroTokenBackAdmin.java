package com.zjy.bll.common.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class ShiroTokenBackAdmin extends UsernamePasswordToken {
    public ShiroTokenBackAdmin(String username, String password) {
        super(username, password);
    }
}
