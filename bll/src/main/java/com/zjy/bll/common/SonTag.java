package com.zjy.bll.common;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by chahongjing on 2017/9/14.
 */
public class SonTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        ReadFileTag parent = (ReadFileTag) getParent();
        System.out.println(parent.getSrc());
        getJspContext().getOut().println(parent.getSrc());
        super.doTag();
    }
}
