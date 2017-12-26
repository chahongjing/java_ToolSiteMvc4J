package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import com.zjy.baseframework.CookieHelper;
import com.zjy.baseframework.DownloadHelper;
import com.zjy.baseframework.PartialViewHelper;
import com.zjy.bll.common.LoggingProxy;
import com.zjy.bll.service.TestService;
import com.zjy.bll.service.TestServiceImpl;
import com.zjy.entities.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import sun.plugin2.gluegen.runtime.BufferFactory;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;

/**
 * @author chahongjing
 * @date 2016-11-22 22:59
 */
@Controller
@RequestMapping("/test")
public class TestController implements ServletConfigAware {
    private Logger logger = LoggerFactory.getLogger(TestController.class);

    private ServletConfig servletConfig;

    @Autowired
    private TestService testSrv;

    @Override
    public void setServletConfig(ServletConfig servletConfig) {
        this.servletConfig = servletConfig;
    }
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
        mv.setViewName("redirect:/testP2");

        return mv;
    }
    //endregion

    @RequestMapping("/testangular")
    public String testangular() {
        return "testangular";
    }

    @RequestMapping("/vueDemo")
    public String vueDemo() {
        return "vueDemo";
    }

    @RequestMapping("/vueLearn")
    public String vueLearn() {
        return "vueLearn";
    }

    @RequestMapping("/testajax")
    @ResponseBody
    public BaseResult testajax(String a, String b, String c) {
        BaseResult<String> result = BaseResult.OK("后台返回数据");
//        int aa = 1, bb = 0;
//        int cc = aa / bb;
        return result;
    }

    @RequestMapping("/fileupload")
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

    @RequestMapping("/download")
    public void download(HttpServletResponse response) {
        // path是指欲下载的文件的路径。
        String path = "d:\\b.txt";
        DownloadHelper.download(path, response);
    }

    @RequestMapping("/jspLearn")
    public String jspLearn(Integer[] arr) {
        System.out.println(arr);
        return "jspLearn";
    }

    @RequestMapping("/javaLearn")
    public String javaLearn(Integer[] arr) {
        System.out.println(arr);
        return "javaLearn";
    }

    @RequestMapping("/filterLearn")
    public String FilterLearn(Integer[] arr) {
        System.out.println(arr);
        return "filterLearn";
    }

    @RequestMapping("/cookieLearn")
    public String cookieLearn(HttpServletRequest request, HttpServletResponse response) {
        String cookie = CookieHelper.getCookie(request, "zjy");
        CookieHelper.addCookie(response, "zjy", "曾军毅");
        return "cookieLearn";
    }

    @RequestMapping("/sessionLearn")
    public String sessionLearn() {
        return "sessionLearn";
    }

    @RequestMapping("/otherLearn")
    public String otherLearn(Integer[] arr) {
        System.out.println(arr);
        return "otherLearn";
    }

    @RequestMapping("/springAopLearn/{intVar}")
    public String springAopLearn(@PathVariable(required = true) int intVar) {
        System.out.println(intVar);
        return "springAopLearn";
    }

    @RequestMapping("/springBeanLearn/{intVar}")
    public String springBeanLearn(@PathVariable(required = true) int intVar) {
        System.out.println(intVar);
        return "springBeanLearn";
    }
    @RequestMapping("/springMVCLearn/{intVar}")
    public String springMVCLearn(@PathVariable(required = true) int intVar) {
        System.out.println(intVar);
        return "springMVCLearn";
    }
    @RequestMapping("/springTransactionLearn")
    public String springTransactionLearn() {
        return "springTransactionLearn";
    }
    @RequestMapping("/nioLearn")
    public String nioLearn() {
        String str = "abc";
        // 分配缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        System.out.println("allocate:");
        System.out.println(buf.position()); // 0
        System.out.println(buf.limit()); // 1024
        System.out.println(buf.capacity()); // 1024
        // 缓冲区存入数据 put()
        buf.put(str.getBytes());
        System.out.println("put:");
        System.out.println(buf.position()); // 3
        System.out.println(buf.limit()); // 1024
        System.out.println(buf.capacity()); // 1024
        // 切换为读模式, position:0, limit:3, capacity:1024
        buf.flip();
        System.out.println("flip:");
        System.out.println(buf.position()); // 0
        System.out.println(buf.limit()); // 3
        System.out.println(buf.capacity()); // 1024
        // 缓冲区读取数据 get()
        byte[] dest = new byte[buf.limit()];
        buf.get(dest);
        System.out.println("allocate:");
        System.out.println(buf.position()); // 3
        System.out.println(buf.limit()); // 3
        System.out.println(buf.capacity()); // 1024
        // rewind 可重复读
        buf.rewind();
        System.out.println("rewind:");
        System.out.println(buf.position()); // 3
        System.out.println(buf.limit()); // 3
        System.out.println(buf.capacity()); // 1024
        // 清空缓冲区，但数据还在，处于被遗忘状态
        buf.clear();
        System.out.println("clear:");
        System.out.println(buf.position()); // 3
        System.out.println(buf.limit()); // 3
        System.out.println(buf.capacity()); // 1024

        //
        ByteBuffer buf2 = ByteBuffer.allocate(1024);
        buf2.put(str.getBytes());
        buf2.flip();
        byte[] dest2 = new byte[buf2.limit()];
        buf2.get(dest2, 0, 1);
        System.out.println("get:");
        System.out.println(buf2.position()); // 1
        //
        buf2.mark();

        buf2.get(dest2, 1, 2);
        System.out.println("mark--get:");
        System.out.println(buf2.position()); // 1

        buf2.reset();
        System.out.println("reset:");
        System.out.println(buf2.position()); // 1

        if (buf2.hasRemaining()) {
            System.out.println("hasRemaining:");
            System.out.println(buf2.remaining());
        }
        return "nioLearn";
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

    @RequestMapping("/elLearn")
    public String elLearn() {
        return "elLearn";
    }


    @RequestMapping("/tomcatLearn")
    public String tomcatLearn() {
        return "tomcatLearn";
    }

    @RequestMapping("/mavenLearn")
    public String mavenLearn() {
        return "mavenLearn";
    }


    @RequestMapping("/servletLearn")
    public String servletLearn() {
        return "servletLearn";
    }

    @RequestMapping("/angulardemo")
    public String angulardemo() {
        return "angulardemo";
    }

    public static void main(String[] args) {
        UserInfo user1 = new UserInfo();
        user1.setUserName("曾军毅");
        UserInfo user2 = new UserInfo();
        user2.setUserName("曾军毅");
        System.out.println(user1.toString());
        System.out.println(user2.toString());
        test(user1, user1);

        String a = "a";
        String b = new String("a");
        teststring(a, b);

        int c = 1;
        int d = 1;
        testint(c, d);

        Integer e = new Integer(1);
        Integer f = new Integer(1);
        testintger(e, f);


    }
    private static void test(UserInfo user1, UserInfo user2) {
        user1.setUserCode("zjy");
        user1 = new UserInfo();
        user1.setUserName("第二个");
        System.out.println(user1.toString());
    }

    private static void teststring(String a, String b) {
        a = "c";
    }
    private static void testint(int a, int b) {
        a = 3;
    }
    private static void testintger(Integer a, Integer b) {
        a = 3;
    }
}
