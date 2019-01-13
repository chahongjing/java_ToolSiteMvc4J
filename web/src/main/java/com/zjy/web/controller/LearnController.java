package com.zjy.web.controller;

import com.zjy.baseframework.*;
import com.zjy.baseframework.enums.ResultStatus;
import com.zjy.bll.common.LoggingProxy;
import com.zjy.bll.service.TestService;
import com.zjy.bll.service.TestServiceImpl;
import com.zjy.entities.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chahongjing on 2018/1/21.
 */
@Controller
@RequestMapping("/learn")
public class LearnController extends BaseController {
    @Autowired
    private TestService testSrv;

    // region java
    /**
     * @param userName
     * @return
     */
    @ModelAttribute("mUserInfo")
    public UserInfo getUserInfo(@RequestParam(value = "userName", required = false) String userName, String arr) {
        UserInfo user = new UserInfo();
        user.setUserName(userName);
        user.setUserCode(arr);
        return user;
    }

    @RequestMapping("/jspLearn/{intVar}")
    public String jspLearn(Model model, @ModelAttribute("mUserInfo") UserInfo mUserInfo, @PathVariable(required = true) int intVar) {
        model.addAttribute("testAttr", mUserInfo.getUserCode());
        model.addAttribute("modelattributeUser", mUserInfo.getUserName());
        return "jspLearn";
    }

    @RequestMapping("/testProxy")
    @ResponseBody
    public BaseResult testProxy() {
        BaseResult<Integer> result = BaseResult.OK();
        TestService testService = new TestServiceImpl();
        TestService proxy = new LoggingProxy(testService).getLoggingProxy();
        result.setValue(proxy.add(1, 3));
        result.setValue(proxy.sub(5, 3));
        return result;
    }

    @RequestMapping("/testAspectJ")
    @ResponseBody
    public BaseResult testAspectJ() {
        BaseResult<Integer> result = BaseResult.OK();
        testSrv.add(1, 4);
        result.setValue(testSrv.sub(3, 1));
        return result;
    }
    // endregion

    //region ueditor
    @RequestMapping("/ueditorLearn")
    public String ueditorLearn() {
        return "ueditorLearn";
    }

    @RequestMapping("/ueditorPicUpload")
    public void ueditorPicUpload(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
        }
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        UeditorUploader up = new UeditorUploader(request);
        up.setSavePath("upload");
        String[] fileType = {".gif", ".png", ".jpg", ".jpeg", ".bmp"};
        up.setAllowFiles(fileType);
        up.setMaxSize(10000); //单位KB
        try {
            up.upload();
        } catch (Exception e) {
        }

        String callback = request.getParameter("callback");

        String result = "{\"name\":\"" + up.getFileName() + "\", \"originalName\": \"" + up.getOriginalName() + "\", \"size\": " + up.getSize() + ", \"state\": \"" + up.getState() + "\", \"type\": \"" + up.getType() + "\", \"url\": \"" + up.getUrl() + "\"}";

        result = result.replaceAll("\\\\", "\\\\");

        if (callback == null) {
            try {
                response.getWriter().print(result);
            } catch (IOException e) {
            }
        } else {
            try {
                response.getWriter().print("<script>" + callback + "(" + result + ")</script>");
            } catch (IOException e) {
            }
        }
    }
    //endregion

    // region 其它
    @PostMapping(value = "/testPostWithFile")
    public ResponseEntity<BaseResult<UserInfo>> testPostWithFile(MultipartHttpServletRequest request,
                                                                 @RequestParam(required = false) Integer age,
                                                                 @RequestParam MultipartFile[] myfile,
                                                                 UserInfo users) {
        BaseResult<UserInfo> re = BaseResult.OK();
        UserInfo user = new UserInfo();
        user.setUserName(users.getUserName());
        user.setUserCode(users.getUserCode());
        user.setBirthday(users.getBirthday());
        StringBuilder fileName = new StringBuilder();
        Path path = Paths.get(Utils.getRootPath(), "Upload");
        if(!path.toFile().exists()) {
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
        return new ResponseEntity<>(re, HttpStatus.OK);
    }

    @RequestMapping("/download")
    @ResponseBody
    public BaseResult download(HttpServletResponse response) {
        BaseResult result = BaseResult.OK();
        try {
            File file = Paths.get(Utils.getRootPath(), "favicon.ico").toFile();
            if (!file.exists()) throw new FileNotFoundException("未找到文件：" + file);
            DownloadHelper.download(file.getAbsolutePath(), response);
            return null;
        } catch (Exception e) {
            response.reset();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setStatus(ResultStatus.ERROR);
            result.setMessage(e.getMessage());
        }
        return result;
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

    @PostMapping(value = "/testPostEntity", produces = "application/json;charset=UTF-8")
    public ResponseEntity<UserInfo> testPostEntity(UserInfo userForm) {
        UserInfo user = new UserInfo();
        user.setUserName(userForm.getUserName());
        user.setDepartmentName("testPostEntity");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping("/testP1")
    @ResponseBody
    public BaseResult testP1() {
        // path是指欲下载的文件的路径。
        BaseResult result = BaseResult.OK();
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        return result;
    }

    @RequestMapping("/testP2")
    @ResponseBody
    public BaseResult testP2() {
        // path是指欲下载的文件的路径。
        BaseResult result = BaseResult.OK();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        return result;
    }
    // endregion
}
