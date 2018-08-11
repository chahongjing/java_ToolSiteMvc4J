package com.zjy.bll.common;

import com.zjy.baseframework.LogHelper;
import org.slf4j.Logger;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MyCustomDateEditor extends PropertyEditorSupport {
    protected Logger logger = LogHelper.getLogger(this.getClass());
    private DateFormat datesdf;
    private DateFormat utcsfd;
    private DateFormat gmtsdf;

    public MyCustomDateEditor() {
        super();
        datesdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        utcsfd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        utcsfd.setTimeZone(TimeZone.getTimeZone("UTC"));
        gmtsdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", Locale.US);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Date date = null;
        try {
            date = utcsfd.parse(text);
        } catch (Exception e) {
            try {
                date = datesdf.parse(text);
            } catch (Exception e2) {
                try {
                    date = gmtsdf.parse(text);
                } catch (Exception e3) {
                    logger.error("解析Date失败！", e3);
                }
            }
        }
        setValue(date);
    }
}
