package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import com.zjy.baseframework.CookieHelper;
import com.zjy.baseframework.DownloadHelper;
import com.zjy.bll.common.LoggingProxy;
import com.zjy.bll.service.TestService;
import com.zjy.bll.service.TestServiceImpl;
import com.zjy.entities.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by chahongjing on 2018/1/21.
 */
@Controller
@RequestMapping("/learn")
public class LearnController extends BaseController {
    @Autowired
    private TestService testSrv;

    // region java
    @RequestMapping("/reflectLearn")
    public String reflectLearn() {
        return "reflectLearn";
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
                logger.error("上传文件异常！", e);
            }
        }
        return mv;
    }

    @RequestMapping("/download")
    public void download(HttpServletResponse response) throws Exception {
        // path是指欲下载的文件的路径。
        String path = "d:\\a.txt";
        File f = new File(path);
        if(!f.exists()) throw new Exception("未找到文件：" + path);
        DownloadHelper.download(path, response);
    }

    /**
     *
     * @param userName
     * @return
     */
    @ModelAttribute("mUserInfo")
    public UserInfo getUserInfo(@RequestParam("userName") String userName, String arr) {
        UserInfo user = new UserInfo();
        user.setUserName(userName);
        user.setUserCode(arr);
        return user;
    }

    @RequestMapping("/jspLearn")
    public String jspLearn(Model model, @ModelAttribute("mUserInfo") UserInfo mUserInfo) {
        model.addAttribute("testAttr", mUserInfo.getUserCode());
        model.addAttribute("modelattributeUser", mUserInfo.getUserName());
        return "jspLearn";
    }

    @RequestMapping("/servletLearn")
    public String servletLearn() {
        return "servletLearn";
    }

    @RequestMapping("/javaLearn")
    public String javaLearn() {
        return "javaLearn";
    }

    @RequestMapping("/filterLearn")
    public String filterLearn() {
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

    @RequestMapping("/shiroLearn")
    public String shiroLearn() {
        return "shiroLearn";
    }
    // endregion

    // region 服务器
    @RequestMapping("/tomcatLearn")
    public String tomcatLearn() {
        return "tomcatLearn";
    }
    // endregion

    // region maven
    @RequestMapping("/mavenLearn")
    public String mavenLearn() {
        return "mavenLearn";
    }
    // endregion

    // region echart

    // endregion

    // region angular
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

    @RequestMapping("/angulardemo")
    public String angulardemo() {
        return "angulardemo";
    }
    // endregion

    // region vue

    // endregion

    // region 其它
    @RequestMapping("/testajax")
    @ResponseBody
    public BaseResult testajax(String a, String b, String c) {
//        int aa = 1, bb = 0;
//        int cc = aa / bb;
        return BaseResult.OK("后台返回数据");
    }

    @RequestMapping("/otherLearn")
    public String otherLearn() {
        return "otherLearn";
    }

    @RequestMapping("/gitLearn")
    public String gitLearn() {
        return "gitLearn";
    }
    // endregion
}
