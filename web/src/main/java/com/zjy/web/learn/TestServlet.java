package com.zjy.web.learn;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by chahongjing on 2017/9/26.
 */
public class TestServlet implements Servlet {

    private ServletConfig servletConfig;
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.servletConfig;
    }

    /**
     * 暂未实现
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

    /**
     * 未实现
     * @return
     */
    @Override
    public String getServletInfo() {
        return null;
    }

    /**
     * 未实现
     */
    @Override
    public void destroy() {

    }
}
