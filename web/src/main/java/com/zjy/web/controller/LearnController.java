package com.zjy.web.controller;

import com.zjy.baseframework.*;
import com.zjy.baseframework.enums.ResultStatus;
import com.zjy.bll.common.LoggingProxy;
import com.zjy.bll.service.TestService;
import com.zjy.bll.service.TestServiceImpl;
import com.zjy.entities.UserInfo;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chahongjing on 2018/1/21.
 */
@Controller
@RequestMapping("learn")
public class LearnController extends BaseController implements ServletConfigAware, EnvironmentAware, ApplicationContextAware {
    @Autowired
    private TestService testSrv;

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

    // region java

    /**
     * @param userName
     * @return
     */
    @ModelAttribute("mUserInfo")
    public UserInfo getUserInfo(@RequestParam(value = "userName", required = false) String userName, String arr) {
        if (servletConfig != null) {
            logger.debug("servletConfig is not null");
        }
        if (environment != null) {
            logger.debug("environment is not null");
        }
        if (applicationContext != null) {
            logger.debug("applicationContext is not null");
        }
        UserInfo user = new UserInfo();
        user.setUserName(userName);
        user.setUserCode(arr);
        return user;
    }

    @RequestMapping("jspLearn/{intVar}")
    public String jspLearn(Model model, @ModelAttribute("mUserInfo") UserInfo mUserInfo, @PathVariable(required = true) int intVar) {
        model.addAttribute("testAttr", mUserInfo.getUserCode());
        model.addAttribute("modelattributeUser", mUserInfo.getUserName());
        return "jspLearn";
    }

    @RequestMapping("testProxy")
    @ResponseBody
    public BaseResult testProxy() {
        BaseResult<Integer> result = BaseResult.ok();
        TestService testService = new TestServiceImpl();
        TestService proxy = new LoggingProxy(testService).getLoggingProxy();
        result.setValue(proxy.add(1, 3));
        result.setValue(proxy.sub(5, 3));
        return result;
    }

    @RequestMapping("testAspectJ")
    @ResponseBody
    public BaseResult testAspectJ() {
        BaseResult<Integer> result = BaseResult.ok();
        testSrv.add(1, 4);
        result.setValue(testSrv.sub(3, 1));
        return result;
    }
    // endregion

    //region ueditor
    @RequestMapping("ueditorLearn")
    public String ueditorLearn() {
        return "ueditorLearn";
    }

    @RequestMapping("ueditorPicUpload")
    public void ueditorPicUpload(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        UeditorUploader up = new UeditorUploader(request);
        up.setSavePath("upload");
        String[] fileType = {".gif", ".png", ".jpg", ".jpeg", ".bmp"};
        up.setAllowFiles(fileType);
        up.setMaxSize(10000); //单位KB
        String mes = "测试ueditor upload";
        try {
            up.upload();
        } catch (Exception e) {
            logger.debug(mes, e);
        }

        String callback = request.getParameter("callback");

        String result = "{\"name\":\"" + up.getFileName() + "\", \"originalName\": \"" + up.getOriginalName() + "\", \"size\": " + up.getSize() + ", \"state\": \"" + up.getState() + "\", \"type\": \"" + up.getType() + "\", \"url\": \"" + up.getUrl() + "\"}";

        result = result.replaceAll("\\\\", "\\\\");

        if (callback == null) {
            try {
                response.getWriter().print(result);
            } catch (IOException e) {
                logger.debug(mes, e);
            }
        } else {
            try {
                response.getWriter().print("<script>" + callback + "(" + result + ")</script>");
            } catch (IOException e) {
                logger.debug(mes, e);
            }
        }
    }
    //endregion

    // region 其它
    @PostMapping(value = "testPostWithFile")
    public ResponseEntity<BaseResult<UserInfo>> testPostWithFile(MultipartHttpServletRequest request,
                                                                 HttpServletResponse response,
                                                                 @RequestParam(required = false) Integer AGE,
                                                                 @RequestParam MultipartFile[] myfile,
                                                                 UserInfo users) {
        BaseResult<UserInfo> re = BaseResult.ok();
        UserInfo user = new UserInfo();
        user.setUserName(users.getUserName());
        user.setUserCode(users.getUserCode());
        user.setBirthday(users.getBirthday());
        StringBuilder fileName = new StringBuilder();
        Path path = Paths.get(Utils.getRootPath(), "Upload");
        if (!path.toFile().exists()) {
            path.toFile().mkdirs();
        }
        for (MultipartFile file : request.getFiles("myfile")) {
            fileName.append(file.getOriginalFilename() + ";");
//            try {
//                file.transferTo(Paths.get(path.toString(), file.getOriginalFilename()).toFile());
//            } catch (IOException e) {
//                logger.error("上传文件异常！", e);
//            }
        }
        user.setPhoto(fileName.toString());
        re.setValue(user);
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8099");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("ccess-Control-Expose-Headers", "Content-Disposition");
        return new ResponseEntity<>(re, HttpStatus.OK);
    }

    @RequestMapping("download")
    @ResponseBody
    public void download(HttpServletResponse response) {
        BaseResult result = BaseResult.ok();
        try {
            File file = Paths.get(Utils.getRootPath(), "favicon.ico").toFile();
            DownloadHelper.download(file, response);
        } catch (Exception e) {
            response.reset();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setStatus(ResultStatus.ERROR);
            result.setMessage(e.getMessage());
        }
    }

    @RequestMapping("redirect")
    public ModelAndView redirect(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/testP2");

        Map<String, Object> map = new HashMap<>();
        map.put("username", "23ab33");
        try {
            String content = PartialViewHelper.renderTest("/index.jsp", request, response, map);
            map.put("content", content);
        } catch (Exception e) {
            logger.error("执行视图错误！", e);
        }

        return mv;
    }

    @PostMapping(value = "testPostEntity", produces = "application/json;charset=UTF-8")
    public ResponseEntity<UserInfo> testPostEntity(UserInfo userForm) {
        UserInfo user = new UserInfo();
        user.setUserName(userForm.getUserName());
        user.setDepartmentName("testPostEntity");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping("testP1")
    @ResponseBody
    public BaseResult testP1() {
        // path是指欲下载的文件的路径。
        BaseResult result = BaseResult.ok();
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            logger.debug("测试promise", e);
        }
        return result;
    }

    @RequestMapping("testP2")
    @ResponseBody
    public BaseResult testP2() {
        // path是指欲下载的文件的路径。
        BaseResult result = BaseResult.ok();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            logger.debug("测试promise", e);
        }
        return result;
    }
    // endregion
}
