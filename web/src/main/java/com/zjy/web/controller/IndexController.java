package com.zjy.web.controller;

import com.zjy.baseframework.ZipHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chahongjing
 * @date 2016-11-22 22:59
 */
@Controller
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/index.do")
    public String test(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ZipHelper.zip("D:\\a.xml");
        ZipHelper.zip("D:\\a.zip", "D:\\a\\b\\c");
        return "OK";
    }
}
