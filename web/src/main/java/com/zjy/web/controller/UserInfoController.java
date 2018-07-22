package com.zjy.web.controller;

import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.BaseResult;
import com.zjy.baseframework.LogHelper;
import com.zjy.baseframework.enums.ResultStatus;
import com.zjy.bll.common.ShiroRealm;
import com.zjy.bll.request.UserInfoRequest;
import com.zjy.bll.service.UserInfoService;
import com.zjy.bll.vo.UserInfoVo;
import com.zjy.entities.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    protected Logger logger = LogHelper.getLogger(this.getClass());

    //region 属性

    @Autowired
    private UserInfoService userInfoSrv;

    @Autowired
    private ShiroRealm shiroRealm;
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
        if (StringUtils.isBlank(url)) {
            url = request.getRequestURL().toString().replace(request.getRequestURI(), "") + request.getContextPath() + "/";
        }
        mv.addObject("redirectUrl", url);
        return mv;
    }

    @RequestMapping("/login")
    public ResponseEntity<BaseResult<String>> login(HttpServletRequest request, UserInfo user) {
        BaseResult<String> re = BaseResult.OK();// userInfoService.login(user);
//        if (re.getStatus() != ResultStatus.OK) {
//            return new ResponseEntity<>(re, HttpStatus.OK);
//        }

        Subject subject = SecurityUtils.getSubject();
//        if (subject.isAuthenticated()) {
//            return null;
//        }
        //String password = userUtils.getMd5Hash(user.getUserCode(), user.getPassword());

        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserCode(), user.getPassword());
        try {
            subject.login(token); // 登录
        } catch (Exception ex) {
            ex.printStackTrace();
            re.setStatus(ResultStatus.NO);
            re.setMessage("用户名或密码错误！");
            return new ResponseEntity<>(re, HttpStatus.OK);
        }
        logger.info("用户{}登录", user.getUserCode());
        return new ResponseEntity<>(re, HttpStatus.OK);
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public ResponseEntity<BaseResult<String>> logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
            // session 会销毁，在SessionListener监听session销毁，清理权限缓存
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
        List<UserInfo> test = userInfoSrv.test("a", b);
        List<UserInfo> list = userInfoSrv.query(new UserInfo());

        UserInfoRequest uRequest = new UserInfoRequest();
        uRequest.setOrderBy("UserCode DESC");
        PageInfo<UserInfo> query = userInfoSrv.queryPage(uRequest);
        List<UserInfo> list1 = query.getList();
        for (UserInfo userInfo : list1) {

        }
        PageInfo<UserInfoVo> queryVo = userInfoSrv.queryPage(uRequest);
        List<UserInfoVo> list2 = queryVo.getList();
        for (UserInfoVo userInfoVo : list2) {

        }
        request.getSession().setAttribute("userTest", b);

        mv.addObject("pageinfo", query);

        return mv;
    }

    // region 用户管理


    @RequestMapping("/user")
    public String userList() {
        return "sys/user";
    }

    @RequestMapping("/userEdit")
    public String editUser(String userGuid, Model model) {
        model.addAttribute("userGuid", userGuid);
        return "sys/userEdit";
    }

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public BaseResult<UserInfoVo> getUserInfo(String userGuid) {
        UserInfoVo userInfo = userInfoSrv.getVo(userGuid);
        return BaseResult.OK(userInfo);
    }

    @RequestMapping("/saveUser")
    @ResponseBody
    public BaseResult<String> saveUser(UserInfoVo userInfo) {
        userInfoSrv.saveUser(userInfo);
        return BaseResult.OK("");
    }

    // endregion
}
