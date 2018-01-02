package com.zjy.web.controller;

import com.zjy.baseframework.ExcelHelper;
import com.zjy.bll.dao.UserInfoForHibernateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chahongjing
 * @date 2016-11-22 22:59
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private UserInfoForHibernateDao userInfoForHibernateDao;

    /**
     * 若没有welcome-file-list中的文件或action,则/会进入到此请求中
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/")
    public String test(HttpServletRequest request, HttpServletResponse response) {
        ExcelHelper<String> e = new ExcelHelper<>();
        return "common/ok";
    }

    public static void main(String[] args) {


        Set<String> s = new LinkedHashSet<>();
        s.add("b");
        s.add("a");
        s.add("d");
        s.add("c");
        s.add("d");
        System.out.println(s);

        Set<String> ts = new TreeSet<>();
        ts.add("b");
        ts.add("a");
        ts.add("d");
        ts.add("c");
        ts.add("d");
        System.out.println(ts);

        Map<String, Integer> lhm = new LinkedHashMap<>();
        lhm.put("b", 1);
        lhm.put("a", 2);
        lhm.put("d", 3);
        lhm.put("c", 4);
        lhm.put("d", 5);
        System.out.println(lhm);

        Map<String, Integer> tm = new TreeMap<>();
        tm.put("b", 1);
        tm.put("a", 2);
        tm.put("d", 3);
        tm.put("c", 4);
        tm.put("d", 5);
        System.out.println(tm);

        ExecutorService executorService = Executors.newCachedThreadPool();
    }
}
