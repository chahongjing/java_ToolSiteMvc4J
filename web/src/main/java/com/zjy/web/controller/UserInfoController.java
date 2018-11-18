package com.zjy.web.controller;

import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.BaseResult;
import com.zjy.baseframework.PartialViewHelper;
import com.zjy.bll.request.UserInfoRequest;
import com.zjy.bll.service.UserInfoService;
import com.zjy.bll.vo.UserInfoVo;
import com.zjy.entities.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chahongjing
 * @create 2016-12-10 15:27
 */
@Controller
@RequestMapping("/userinfo")
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
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
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
    @RequiresPermissions(value = {"userEdit_enter"}, logical = Logical.OR)
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
        PageInfo<UserInfoVo> pageInfo = (PageInfo<UserInfoVo>) userInfoSrv.queryPageList(request);
        return BaseResult.OK(pageInfo);
    }
    // endregion

    // region 学习方法
    @RequestMapping(value = "/testPostWithFile", method = RequestMethod.POST)
    public ResponseEntity<BaseResult<UserInfo>> testPostWithFile(MultipartHttpServletRequest request,
                                                                 @RequestParam(required = false) Integer age,
                                                                 @RequestParam MultipartFile[] myfile,
                                                                 UserInfo users) {
        BaseResult<UserInfo> re = BaseResult.OK();
        UserInfo user = new UserInfo();
        user.setUserName("曾军毅postWithFile");
        for (MultipartFile file : request.getFiles("myfile")) {

        }
        re.setValue(user);
        return new ResponseEntity<>(re, HttpStatus.OK);
    }
    @RequestMapping("/redirect")
    public ModelAndView redirect(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/testP2");

        Map<String, Object> map = new HashMap<>();
        map.put("username", "23ab33");
        try {
            String content = PartialViewHelper.renderTest("/index.jsp", request, response, map);
        } catch (Exception e) {
            logger.error("执行视图错误！", e);
        }

        return mv;
    }

    @RequestMapping(value = "/testPostEntity", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<UserInfo> testPostEntity(UserInfo userForm) {
        UserInfo user = new UserInfo();
        user.setUserName(userForm.getUserName());
        user.setDepartmentName("testPostEntity");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    // endregion
}
