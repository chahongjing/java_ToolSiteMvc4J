package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import com.zjy.bll.baseBean.PageBean;
import com.zjy.bll.request.UserInfoRequest;
import com.zjy.bll.service.UserInfoService;
import com.zjy.bll.vo.UserInfoVo;
import com.zjy.entities.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HttpMethod;

/**
 * @author chahongjing
 * @create 2016-12-10 15:27
 */
@Controller
@RequestMapping("/user")
public class UserInfoController extends BaseController implements ServletConfigAware, EnvironmentAware, ApplicationContextAware {

    //region 属性
    @Autowired
    private UserInfoService userInfoSrv;
    //endregion

    // region servlet相关
    private ServletConfig servletConfig;
    private Environment environment;
    private ApplicationContext applicationContext;

    @Value("${db.url}")
    private String url;

    @Override
    public void setServletConfig(ServletConfig servletConfig) {
        this.servletConfig = servletConfig;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    // endregion

    //region 登录登出
    @RequestMapping("/loginpage")
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        // 获取上一次的地址
        SavedRequest lastRequest = WebUtils.getSavedRequest(request);
        String lastUrl = null;
        if (lastRequest != null && HttpMethod.GET.equalsIgnoreCase(lastRequest.getMethod())) {
            lastUrl = lastRequest.getRequestUrl();
        }
        if (StringUtils.isBlank(lastUrl)) {
            lastUrl = request.getRequestURL().toString().replace(request.getRequestURI(), StringUtils.EMPTY) + request.getContextPath() + "/";
        }
        mv.addObject("redirectUrl", lastUrl);
        return mv;
    }

    @RequestMapping("/userListPage")
    public String userListPage() {
        return "/sys/user";
    }

    @RequestMapping("/userEditPage")
    public String userEditPage(String id) {
        return "/sys/userEdit";
    }

    @RequestMapping("/login")
    @ResponseBody
    public BaseResult<UserInfoVo> login(UserInfo user) {
        return userInfoSrv.login(user);
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public BaseResult<String> logout() {
        return userInfoSrv.logout();
    }
    //endregion

    // region 用户管理
    @RequestMapping("/userEdit")
    @RequiresPermissions("userEdit_enter")
    public String editUser(String userId, Model model) {
        model.addAttribute("userId", userId);
        return "sys/userEdit";
    }

    @RequestMapping("/getDetail")
    @ResponseBody
    @RequiresPermissions("userEdit_enter")
    public BaseResult<UserInfoVo> getDetail(String id) {
        UserInfoVo userInfo = userInfoSrv.getVo(id);
        return BaseResult.OK(userInfo);
    }

    @PostMapping("/save")
    @ResponseBody
    public BaseResult<String> save(UserInfoVo vo) {
        UserInfo currentUser = shiroRealm.getCurrentUser();
        if (!(shiroRealm.isPermitted("userEdit_save") || (currentUser != null && currentUser.getUserCode().equals(vo.getUserCode())))) {
            throw new UnauthorizedException();
        }
        userInfoSrv.save(vo);
        return BaseResult.OK();
    }

    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions(value = {"userList_delete"}, logical = Logical.OR)
    public BaseResult<String> delete(String id) {
        userInfoSrv.delete(id);
        return BaseResult.OK();
    }

    @RequestMapping("/changePassword")
    @ResponseBody
    public BaseResult<String> changePassword(String userCode, String oldPassword, String newPassword) {
        userInfoSrv.changePassword(userCode, oldPassword, newPassword);
        return BaseResult.OK();
    }

    @RequestMapping("/resetPassword")
    @ResponseBody
    @RequiresPermissions(value = {"userList_resetPassword"})
    public BaseResult<String> resetPassword(String userCode, String password) {
        userInfoSrv.resetPassword(userCode, password);
        return BaseResult.OK();
    }

    @RequestMapping("/queryPageList")
    @ResponseBody
    @RequiresPermissions("userList_enter")
    public BaseResult<PageBean> queryPageList(UserInfoRequest request) {
        PageBean<UserInfoVo> pageBean = (PageBean<UserInfoVo>) userInfoSrv.queryPageList(request);
        return BaseResult.OK(pageBean);
    }
    // endregion
}
