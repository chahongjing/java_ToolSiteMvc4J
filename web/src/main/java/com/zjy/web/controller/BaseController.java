package com.zjy.web.controller;

import com.zjy.baseframework.LogHelper;
import com.zjy.bll.common.MyCustomDateEditor;
import com.zjy.bll.common.MyCustomZonedDateEditor;
import com.zjy.bll.common.ShiroRealm;
import com.zjy.entities.UserInfo;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Created by Administrator on 2018/1/2.
 */
public class BaseController {
    protected Logger logger = LogHelper.getLogger(this.getClass());

    @Autowired
    protected ShiroRealm shiroRealm;

    @ModelAttribute
    public void init(Model model) {
        UserInfo currentUser = shiroRealm.getCurrentUser();
        model.addAttribute("user", currentUser);
    }

    @InitBinder
    public void bindingPreparation(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new MyCustomDateEditor());
        binder.registerCustomEditor(ZonedDateTime.class, new MyCustomZonedDateEditor());
    }
}
