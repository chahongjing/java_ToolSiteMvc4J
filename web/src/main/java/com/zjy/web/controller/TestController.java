package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import com.zjy.baseframework.PartialViewHelper;
import com.zjy.entities.Goods;
import com.zjy.entities.UserInfo;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chahongjing
 * @date 2016-11-22 22:59
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController implements ServletConfigAware, EnvironmentAware, ApplicationContextAware {
//    @Autowired
//    private ResourceBundleMessageSource messageSource;

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

    @RequestMapping("/test.do")
    public String test(HttpServletRequest request, HttpServletResponse response) {
        logger.info("测试日志方法{}", new Date());
        logger.info("从Properties读取配置信息：" + url);

//        Object[] arg = new Object[] { "Erica", Calendar.getInstance().getTime() };
//        messageSource.getMessage("username", arg, Locale.CHINA);

        Map<String, Object> map = new HashMap<>();
        map.put("username", "23ab33");
        try {
            String content = PartialViewHelper.renderTest("/index.jsp", request, response, map);
        } catch (Exception e) {
            logger.error("执行视图错误！", e);
        }

        return "common/ok";
    }

    //region 延迟和回调
    @RequestMapping("/testP1.do")
    public ResponseEntity<BaseResult<String>> testP1() {
        BaseResult<String> re = BaseResult.OK();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.error("测试sleep异常！", e);
        }
        return new ResponseEntity<>(re, HttpStatus.OK);
    }

    @RequestMapping("/testP2.do")
    public ResponseEntity<BaseResult<String>> testP2() {
        BaseResult<String> re = BaseResult.OK();

        return new ResponseEntity<>(re, HttpStatus.OK);
    }

    @RequestMapping("/redirect.do")
    public ModelAndView redirect() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/testP2");

        return mv;
    }
    //endregion

    // region 测试get,post,postWithFile请求
    @RequestMapping(value = "/testGet.do", method = RequestMethod.GET)
    public ResponseEntity<BaseResult<UserInfo>> testGet(Integer age) {
        BaseResult<UserInfo> re = BaseResult.OK();
        UserInfo user = new UserInfo();
        user.setUserName("曾军毅get");
        user.setAge(age);
        re.setValue(user);
        return new ResponseEntity<>(re, HttpStatus.OK);
    }

    @RequestMapping(value = "/testPost.do", method = RequestMethod.POST)
    public ResponseEntity<BaseResult<UserInfo>> testPost(Integer age) {
        BaseResult<UserInfo> re = BaseResult.OK();
        UserInfo user = new UserInfo();
        user.setUserName("曾军毅post");
        user.setAge(age);
        re.setValue(user);
        return new ResponseEntity<>(re, HttpStatus.OK);
    }

    @RequestMapping(value = "/testPostWithFile.do", method = RequestMethod.POST)
    public ResponseEntity<BaseResult<UserInfo>> testPostWithFile(MultipartHttpServletRequest request, @RequestParam(required = false) Integer age,
                                                                 @RequestParam MultipartFile[] myfile, UserInfo users) {
        BaseResult<UserInfo> re = BaseResult.OK();
        UserInfo user = new UserInfo();
        user.setUserName("曾军毅postWithFile");
        for (MultipartFile file : request.getFiles("myfile")) {

        }
        user.setAge(age + users.getAge());
        re.setValue(user);
        return new ResponseEntity<>(re, HttpStatus.OK);
    }

    @RequestMapping(value = "/testGetEntity.do", produces = "application/json;charset=UTF-8")
    public ResponseEntity<UserInfo> testGetEntity(Goods goods) {
        UserInfo user = new UserInfo();
        user.setUserName(goods.getName());
        user.setDepartmentName("testGetEntity");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/testPostEntity.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<UserInfo> testPostEntity(Goods goods) {
        UserInfo user = new UserInfo();
        user.setUserName(goods.getName());
        user.setDepartmentName("testPostEntity");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    // endregion
}
