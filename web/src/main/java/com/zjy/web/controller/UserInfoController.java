package com.zjy.web.controller;

import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.BaseResult;
import com.zjy.baseframework.LogHelper;
import com.zjy.bll.request.UserInfoRequest;
import com.zjy.bll.service.UserInfoService;
import com.zjy.bll.vo.UserInfoVo;
import com.zjy.entities.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
    @ResponseBody
    public BaseResult<String> login(UserInfo user) {
        return userInfoSrv.login(user);
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public BaseResult<String> logout() {
        return userInfoSrv.logout();
    }
    //endregion

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


    @RequestMapping("/queryPageList")
    @ResponseBody
    public BaseResult<PageInfo> queryPageList(UserInfoRequest userInfo) {
        PageInfo<UserInfo> pageInfo = userInfoSrv.queryPageList(userInfo);
        return BaseResult.OK(pageInfo);
    }
    // endregion
}
