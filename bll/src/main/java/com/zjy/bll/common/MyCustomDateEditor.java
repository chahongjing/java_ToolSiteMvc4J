package com.zjy.bll.common;

import com.zjy.baseframework.LogHelper;
import org.slf4j.Logger;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyCustomDateEditor extends PropertyEditorSupport {
    protected Logger logger = LogHelper.getLogger(this.getClass());
    private DateFormat dateSdf;
    private DateFormat dateTimeSdf;
    private DateFormat utcSfd;
    private DateFormat gmtSdf;
    private List<DateFormat> sdf;

    public MyCustomDateEditor() {
        super();
        sdf = new ArrayList<>();
        dateSdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.add(dateSdf);
        dateTimeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.add(dateTimeSdf);
        utcSfd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        utcSfd.setTimeZone(TimeZone.getTimeZone("UTC"));
        sdf.add(utcSfd);
        gmtSdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", Locale.US);
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
        if(date == null) {
            logger.error("解析Date失败！", text);
        }
        setValue(date);
    }
}
