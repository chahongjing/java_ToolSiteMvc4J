package com.zjy.web.learn;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by chahongjing on 2017/5/21.
 */
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 转发 /表示项目根目录，不需要再添加contextPath
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/servletLearn.jsp");
        //dispatcher.forward(req, resp);
        //this.getServletContext().getResource()
        // 重定向 /表示站点根目录，需要添加contextPath
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
        //super.doPost(req, resp);
    }
}
