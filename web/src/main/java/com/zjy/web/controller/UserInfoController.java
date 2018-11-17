package com.zjy.web.controller;

import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.BaseResult;
import com.zjy.baseframework.LogHelper;
import com.zjy.bll.request.UserInfoRequest;
import com.zjy.bll.service.UserInfoService;
import com.zjy.bll.vo.UserInfoVo;
import com.zjy.entities.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
public class UserInfoController extends BaseController {
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
    public BaseResult<UserInfo> login(UserInfo user) {
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
    public String editUser(String userId, Model model) {
        model.addAttribute("userId", userId);
        return "sys/userEdit";
    }

    @RequestMapping("/getDetail")
    @ResponseBody
    public BaseResult<UserInfoVo> getDetail(String id) {
        UserInfoVo userInfo = userInfoSrv.getVo(id);
        return BaseResult.OK(userInfo);
    }

    @RequestMapping("/save")
    @ResponseBody
    @RequiresPermissions(value = {"admin:userEdit_enter"}, logical = Logical.OR)
    public BaseResult<String> save(UserInfoVo vo) {
        userInfoSrv.save(vo);
        return BaseResult.OK("");
    }

    @RequestMapping("/delete")
    @ResponseBody
    public BaseResult<String> delete(String id) {
        userInfoSrv.delete(id);
        return BaseResult.OK("");
    }

    @RequestMapping("/queryPageList")
    @ResponseBody
    public BaseResult<PageInfo> queryPageList(UserInfoRequest request) {
        PageInfo<UserInfoVo> pageInfo = (PageInfo<UserInfoVo>)userInfoSrv.queryPageList(request);
        return BaseResult.OK(pageInfo);
    }
    // endregion
}
