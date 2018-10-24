package com.zjy.bll.common;

import com.zjy.baseframework.LogHelper;
import org.slf4j.Logger;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyCustomDateEditor extends PropertyEditorSupport {
    protected Logger logger = LogHelper.getLogger(this.getClass());
    private final static DateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
    private final static DateFormat dateTimeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static DateFormat utcSfd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    private final static DateFormat gmtSdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", Locale.US);
    private final List<DateFormat> sdf;

    static {
        utcSfd.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public MyCustomDateEditor() {
        super();
        sdf = new ArrayList<>();
        sdf.add(dateSdf);
        sdf.add(dateTimeSdf);
        sdf.add(utcSfd);
        sdf.add(gmtSdf);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Date date = null;
        for (DateFormat dateFormat : sdf) {
            try {
                date = dateFormat.parse(text);
                break;
            } catch (Exception e) {
            }
        }
        if (date == null) {
            logger.error("解析Date失败！", text);
        }
        setValue(date);
    }

    public static DateFormat getDateSdf() {
        return dateSdf;
    }

    public static DateFormat getDateTimeSdf() {
        return dateTimeSdf;
    }

    public static DateFormat getUtcSfd() {
        return utcSfd;
    }

    public static DateFormat getGmtSdf() {
        return gmtSdf;
    }
}
