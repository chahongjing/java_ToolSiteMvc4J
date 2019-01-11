package com.zjy.bll.common;

import com.zjy.baseframework.LogHelper;
import org.slf4j.Logger;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyCustomDateEditor extends PropertyEditorSupport {
    public final static String DATEFORMAT = "yyyy-MM-dd";
    public final static String DATETIMEFORMAT = "yyyy-MM-dd HH:mm:ss";

    protected Logger logger = LogHelper.getLogger(this.getClass());
    private static final DateFormat dateSdf = new SimpleDateFormat(DATEFORMAT);
    private static final DateFormat dateTimeSdf = new SimpleDateFormat(DATETIMEFORMAT);
    private static final DateFormat utcSfd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    private static final DateFormat gmtSdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", Locale.US);
    private static final List<DateFormat> sdf;

    static {
        utcSfd.setTimeZone(TimeZone.getTimeZone("UTC"));
        sdf = new ArrayList<>();
        sdf.add(dateSdf);
        sdf.add(dateTimeSdf);
        sdf.add(utcSfd);
        sdf.add(gmtSdf);
    }

    public MyCustomDateEditor() {
        super();
    }

    @Override
    public void setAsText(String text) {
        Date date = parse(text);
        if (date == null) {
            logger.error("解析Date失败！", text);
        }
        setValue(date);
    }

    public static Date parse(String text) {
        Date date = null;
        for (DateFormat dateFormat : sdf) {
            try {
                date = dateFormat.parse(text);
                break;
            } catch (Exception e) {
            }
        }
        return date;
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
