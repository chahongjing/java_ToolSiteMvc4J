package com.zjy.baseframework;

import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.view.InternalResourceView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class PartialViewHelper {
    public static String render(String viewUrl, HttpServletRequest request, HashMap data) throws Exception {
        InternalResourceView view = new InternalResourceView(viewUrl, true);
        view.setContentType("text/html;charset=utf-8");
        MockHttpServletResponse mockResp = new MockHttpServletResponse();
        view.render(data, request, mockResp);
        return mockResp.getContentAsString();
    }
}
