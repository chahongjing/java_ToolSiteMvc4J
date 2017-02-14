package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import com.zjy.baseframework.PartialViewHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

        return "OK";
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

    @RequestMapping("/fileupload.do")
    public ModelAndView fileUpload(MultipartHttpServletRequest request) {
        // @RequestParam("myfile") List<CommonsMultipartFile> myfile
        ModelAndView mv = new ModelAndView();
        mv.setViewName("OK");
        Path path = Paths.get(request.getSession().getServletContext().getRealPath(File.separator), "upload");
        File dir = path.toFile();
        if (!dir.exists()) {
            dir.mkdir();
        }
        for(MultipartFile file: request.getFiles("myfile")) {
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
        try {
            // path是指欲下载的文件的路径。
            String path = "d:\\b.txt";
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
