package com.zjy.web.controller;

import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.BaseResult;
import com.zjy.baseframework.enums.ResultStatus;
import com.zjy.bll.request.UserInfoRequest;
import com.zjy.bll.service.UserInfoService;
import com.zjy.entities.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author chahongjing
 * @create 2016-12-10 15:27
 */
@Controller
@RequestMapping("/userinfo")
public class UserInfoController {
    private Logger logger = LoggerFactory.getLogger(UserInfoController.class);
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/loginpage.do")
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        // 获取上一次的地址
        SavedRequest lastRequest = WebUtils.getSavedRequest(request);
        String url = null;
        if (lastRequest != null && "GET".equals(lastRequest.getMethod().toUpperCase()))
            url = WebUtils.getSavedRequest(request).getRequestUrl();
        mv.addObject("redirectUrl", url);
        return mv;
    }

    @RequestMapping("/login.do")
    public ResponseEntity<BaseResult<String>> login(UserInfo user) throws Exception {
        BaseResult<String> re = userInfoService.login(user);

        if (re.getStatus() != ResultStatus.OK) {
            return new ResponseEntity<>(re, HttpStatus.OK);
        }

        Subject subject = SecurityUtils.getSubject();
//        if (subject.isAuthenticated()) {
//            return null;
//        }
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserCode(), user.getPassword());
        subject.login(token); // 登录
        logger.info("用户{}登录", user.getUserCode());
        return new ResponseEntity<>(re, HttpStatus.OK);
    }


    @RequestMapping("/loginindex.do")
    public ModelAndView loginindex() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("list");

        List<UserInfo> list = userInfoService.query(new UserInfo());

        UserInfoRequest request = new UserInfoRequest();
        request.setOrderBy("UserCode DESC");
        PageInfo query = userInfoService.queryPage(request);

        mv.addObject("pageinfo", query);

        return mv;
    }

    @RequestMapping(value = "/logout.do")
    public ResponseEntity<BaseResult<String>> logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
        }

        BaseResult<String> re = BaseResult.OK();
        re.setMessage("注销成功！");
        System.out.println(re);
        return new ResponseEntity<>(re, HttpStatus.OK);
    }
}
