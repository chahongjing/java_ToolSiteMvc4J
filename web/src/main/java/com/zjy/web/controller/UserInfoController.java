package com.zjy.web.controller;

import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.BaseResult;
import com.zjy.baseframework.enums.ResultStatus;
import com.zjy.bll.common.UserUtils;
import com.zjy.bll.request.UserInfoRequest;
import com.zjy.bll.service.UserInfoService;
import com.zjy.entities.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
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
public class UserInfoController extends BaseController {

    //region 属性

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserUtils userUtils;
    //endregion

    //region 登录登出
    @RequestMapping("/loginpage")
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        // 获取上一次的地址
        SavedRequest lastRequest = WebUtils.getSavedRequest(request);
        String url = null;
        if (lastRequest != null && "GET".equalsIgnoreCase(lastRequest.getMethod())) {
            url = WebUtils.getSavedRequest(request).getRequestUrl();
        }
        if(StringUtils.isBlank(url)) {
            url = request.getRequestURL().toString().replace(request.getRequestURI(), "") + request.getContextPath() + "/";
        }
        mv.addObject("redirectUrl", url);
        return mv;
    }

    @RequestMapping("/login")
    public ResponseEntity<BaseResult<String>> login(HttpServletRequest request, UserInfo user) {
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

    @RequestMapping(value = "/logout")
    public ResponseEntity<BaseResult<String>> logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
            // session 会销毁，在SessionListener监听session销毁，清理权限缓存
            userUtils.logout();
        }

        BaseResult<String> re = BaseResult.OK();
        re.setMessage("注销成功！");
        logger.info("注销{}", re);
        return new ResponseEntity<>(re, HttpStatus.OK);
    }
    //endregion

    @RequestMapping("/loginindex")
    public ModelAndView loginindex(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("list");

        UserInfo b = new UserInfo();
        b.setUserCode("b");
        b.setUserName("曾军毅从controller获取数据");
        List<UserInfo> test = userInfoService.test("a", b);
        List<UserInfo> list = userInfoService.query(new UserInfo());

        UserInfoRequest uRequest = new UserInfoRequest();
        uRequest.setOrderBy("UserCode DESC");
        PageInfo query = userInfoService.queryPage(uRequest);
        request.getSession().setAttribute("userTest", b);

        mv.addObject("pageinfo", query);

        return mv;
    }
}
