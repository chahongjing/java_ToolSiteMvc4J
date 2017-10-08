package com.zjy.bll.common;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by chahongjing on 2017/9/7.
 */
public class ReadFileTag extends SimpleTagSupport {
    private String src;

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSrc() {
        return src;
    }

    /**
     * @throws JspException
     * @throws IOException
     */
    @Override
    public void doTag() throws JspException, IOException {
        PageContext pc = (PageContext)super.getJspContext();
        String pSrc = pc.getRequest().getParameter("src");
        pc.getOut().println(src);

        JspFragment jspFragment = super.getJspBody();

        jspFragment.invoke(null);
    }
}
