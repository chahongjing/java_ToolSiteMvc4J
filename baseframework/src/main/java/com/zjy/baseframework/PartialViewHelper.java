package com.zjy.baseframework;

import org.springframework.web.servlet.view.InternalResourceView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.PrintWriter;
import java.util.HashMap;

public class PartialViewHelper {
//    public static String render(String viewUrl, HttpServletRequest request, HashMap data) throws Exception {
//        InternalResourceView view = new InternalResourceView(viewUrl, true);
//        view.setContentType("text/html;charset=utf-8");
//        MockHttpServletResponse mockResp = new MockHttpServletResponse();
//        view.render(data, request, mockResp);
//        return mockResp.getContentAsString();
//    }
    public static String renderTest(String viewUrl, HttpServletRequest request, HttpServletResponse response, HashMap data) throws Exception {
        InternalResourceView view = new InternalResourceView(viewUrl, true);
        view.setContentType("text/html;charset=utf-8");
        ResponseWrapper rw = new PartialViewHelper().new ResponseWrapper(response);
        view.render(data, request, rw);
        return rw.getResult();
    }
    /**
     * 自定义一个响应结果包装器，将在这里提供一个基于内存的输出器来存储所有
     * 返回给客户端的原始HTML代码。
     *
     * @author 铁木箱子
     *
     */
    private class ResponseWrapper extends HttpServletResponseWrapper {
        private PrintWriter cachedWriter;
        private CharArrayWriter bufferedWriter;

        public ResponseWrapper(HttpServletResponse response) {
            super(response);
            // 这个是我们保存返回结果的地方
            bufferedWriter = new CharArrayWriter();
            // 这个是包装PrintWriter的，让所有结果通过这个PrintWriter写入到bufferedWriter中
            cachedWriter = new PrintWriter(bufferedWriter);
        }

        @Override
        public PrintWriter getWriter() {
            return cachedWriter;
        }

        /**
         * 获取原始的HTML页面内容。
         * @return
         */
        public String getResult() {
            return bufferedWriter.toString();
        }
    }
}
