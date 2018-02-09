package com.zjy.web.controller;

import com.zjy.baseframework.ExcelHelper;
import com.zjy.bll.dao.UserInfoForHibernateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chahongjing
 * @date 2016-11-22 22:59
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {
    /**
     * 若没有welcome-file-list中的文件或action,则/会进入到此请求中
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/")
    public String test(HttpServletRequest request, HttpServletResponse response) {
        ExcelHelper<String> e = new ExcelHelper<>();
        return "common/ok";
    }
}
