package com.zjy.web.controller;

import com.zjy.baseframework.ExcelHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chahongjing
 * @date 2016-11-22 22:59
 */
@Controller
public class IndexController extends BaseController {
    /**
     * 若没有welcome-file-list中的文件或action,则/会进入到此请求中
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/")
    public String index() {
        boolean hasRole = shiroRealm.hasRole("admin");
        boolean permitted = shiroRealm.isPermitted("admin:testPermission");
        return "index";
    }
    @RequestMapping("/ok")
    public String ok(HttpServletRequest request, HttpServletResponse response) {
        ExcelHelper<String> e = new ExcelHelper<>();
        return "common/ok";
    }
}
