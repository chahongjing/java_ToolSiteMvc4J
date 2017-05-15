package com.zjy.web.controller;

import com.zjy.baseframework.ExcelHelper;
import com.zjy.bll.dao.UserInfoForHibernateDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserInfoForHibernateDao userInfoForHibernateDao;

    @RequestMapping("/index.do")
    public String test(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        TreeMap<String, Integer> tm = new TreeMap<>();
        tm.put("b", 1);
        tm.put("a", 2);
        tm.put("d", 3);
        tm.put("c", 4);
        tm.put("d", 5);
        System.out.println(tm);

        ExecutorService executorService = Executors.newCachedThreadPool();
    }
}
