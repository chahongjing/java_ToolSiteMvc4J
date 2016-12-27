package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import com.zjy.baseframework.DbHelper;
import com.zjy.bll.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author chahongjing
 * @create 2016-11-22 22:59
 */
@Controller
public class IndexController {
    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserInfoService userInfoService;
    @Value("${db.url}")
    private String url;

    @RequestMapping("/index.do")
    public String test() throws Exception {
        logger.info("测试日志方法{}", new Date());
        logger.info("从Properties读取配置信息：" + url);
        DbHelper.Test();
        return "OK";
    }

    @RequestMapping("/testP1.do")
    public ResponseEntity testP1() {
        BaseResult<String> re = BaseResult.OK();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(re, HttpStatus.OK);
    }

    @RequestMapping("/testP2.do")
    public ResponseEntity testP2() {
        BaseResult<String> re = BaseResult.OK();

        return new ResponseEntity(re, HttpStatus.OK);
    }

    @RequestMapping("/redirect.do")
    public ModelAndView redirect() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/testP2.do");

        return mv;
    }

    @RequestMapping("/fileupload.do")
    public ModelAndView fileUpload(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("OK");
        // 转型为MultipartHttpRequest：
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Path path = Paths.get(request.getSession().getServletContext().getRealPath("/"), "upload");
        File dir = path.toFile();
        if (!dir.exists()) {
            dir.mkdir();
        }
        for(Map.Entry<String, MultipartFile> kv: multipartRequest.getFileMap().entrySet()) {
            MultipartFile file = kv.getValue();
            try {
                file.transferTo(new File(path + File.separator + file.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mv;
    }
}
