package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import com.zjy.baseframework.DownloadHelper;
import com.zjy.baseframework.PartialViewHelper;
import com.zjy.entities.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @author chahongjing
 * @date 2016-11-22 22:59
 */
@Controller
@RequestMapping("/test")
public class TestController {
    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @Value("${db.url}")
    private String url;

//    @Autowired
//    private ResourceBundleMessageSource messageSource;

    @RequestMapping("/test.do")
    public String test(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("测试日志方法{}", new Date());
        logger.info("从Properties读取配置信息：" + url);

//        Object[] arg = new Object[] { "Erica", Calendar.getInstance().getTime() };
//        messageSource.getMessage("username", arg, Locale.CHINA);

        HashMap<String, Object> map = new HashMap<>();
        map.put("username", "23ab33");
        String content = PartialViewHelper.renderTest("/index.jsp", request, response, map);

        return "common/ok";
    }

    //region 延迟和回调
    @RequestMapping("/testP1.do")
    public ResponseEntity<BaseResult<String>> testP1() {
        BaseResult<String> re = BaseResult.OK();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
        mv.setViewName("redirect:/testP2.do");

        return mv;
    }
    //endregion

    @RequestMapping("/testangular.do")
    public String testangular() {
        return "testangular";
    }

    @RequestMapping("/testajax.do")
    @ResponseBody
    public BaseResult testajax(String a, String b, String c) {
        BaseResult<String> result = BaseResult.OK("abc");
        int aa = 1, bb = 0;
        int cc = aa / bb;
        return result;
    }

    @RequestMapping("/fileupload.do")
    public ModelAndView fileUpload(MultipartHttpServletRequest request) {
        // @RequestParam("myfile") List<CommonsMultipartFile> myfile
//        try {
//            for(Part part : request.getParts()) {
//                //part.write("要保存的文件路径");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ServletException e) {
//            e.printStackTrace();
//        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("common/ok");
        Path path = Paths.get(request.getSession().getServletContext().getRealPath(""), "upload");
        File dir = path.toFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        for (MultipartFile file : request.getFiles("myfile")) {
            try {
                file.transferTo(Paths.get(path.toString(), file.getOriginalFilename()).toFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mv;
    }

    @RequestMapping("/download.do")
    public void download(HttpServletResponse response) {
        // path是指欲下载的文件的路径。
        String path = "d:\\b.txt";
        DownloadHelper.download(path, response);
    }


    @RequestMapping("/jspLearn.do")
    public String jspLearn(Integer[] arr) {
        System.out.println(arr);
        return "jspLearn";
    }

    @RequestMapping("/springLearn/{intVar}.do")
    public String springLearn(@PathVariable(required = true) int intVar) {
        System.out.println(intVar);
        return "springLearn";
    }

    @RequestMapping("/elLearn.do")
    public String elLearn() {
        return "elLearn";
    }

    @RequestMapping("/servletLearn.do")
    public String servletLearn() {
        return "servletLearn";
    }
}
