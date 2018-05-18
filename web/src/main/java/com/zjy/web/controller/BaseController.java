package com.zjy.web.controller;

import com.zjy.baseframework.LogHelper;
import com.zjy.bll.common.UserUtils;
import com.zjy.entities.UserInfo;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by Administrator on 2018/1/2.
 */
public class BaseController {
    protected Logger logger = LogHelper.getLogger(this.getClass());

    @Autowired
    protected UserUtils userUils;

    @ModelAttribute
    public void init(Model model) {
        UserInfo currentUser = userUils.getCurrentUser();
        model.addAttribute("user", currentUser);
    }
}
