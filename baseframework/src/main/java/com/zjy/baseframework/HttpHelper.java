package com.zjy.baseframework;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.URIUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chahongjing on 2017/2/6.
 */
public class HttpHelper {
    /**
     * 发送get请求
     *
     * @param url         请求地址
     * @param queryString 查询参数
     * @return
     */
    public static String doGet(String url, String queryString) {
        return doGet(url, queryString, Charset.forName("UTF-8"));
    }

    /**
     * 发送get请求
     *
     * @param url         请求地址
     * @param queryString 查询参数
     * @param charset     字符集
     * @return
     */
    public static String doGet(String url, String queryString, Charset charset) {
        StringBuffer response = new StringBuffer();
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(url);
        try {
            if (queryString != null && queryString.length() > 0)
                method.setQueryString(URIUtil.encodeQuery(queryString));
            client.executeMethod(method);
            if (method.getStatusCode() == HttpStatus.SC_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), charset));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
            }
        } catch (URIException e) {
        } catch (IOException e) {
        } finally {
            method.releaseConnection();
        }
        return response.toString();
    }

    /**
     * 发送post请求
     *
     * @param url     请求地址
     * @param params  请求参数
     * @return
     */
    public static String doPost(String url, Map<String, String> params) {
        return doPost(url, params, Charset.forName("UTF-8"));
    }

    /**
     * 发送post请求
     *
     * @param url     请求地址
     * @param params  请求参数
     * @param charset 字符集
     * @return
     */
    public static String doPost(String url, Map<String, String> params, Charset charset) {
        return doPost(url, params, Collections.EMPTY_MAP, charset);
    }

    /**
     * 发送post请求
     *
     * @param url      请求地址
     * @param params   请求参数
     * @param fileList 发送文件
     * @return
     */
    public static String doPost(String url, Map<String, String> params, Map<String, String> fileList) {
        return doPost(url, params, fileList, Charset.forName("UTF-8"));
    }

    /**
     * 发送post请求
     *
     * @param url      请求地址
     * @param params   请求参数
     * @param fileList 发送文件
     * @param charset  字符集
     * @return
     */
    public static String doPost(String url, Map<String, String> params, Map<String, String> fileList, Charset charset) {
        StringBuffer response = new StringBuffer();
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);

        // 文件post
        if (fileList != null && fileList.size() > 0) {
            List<Part> parts = new ArrayList<>();
            for (Map.Entry<String, String> entry : fileList.entrySet()) {
                FilePart fp = null;
                try {
                    // parts.add(new StringPart(entry.getKey(), entry.getValue(), charset));
                    parts.add(new FilePart(entry.getKey(), new File(entry.getValue())));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            HttpMethodParams par = new HttpMethodParams();
            par.setContentCharset(charset.name());
            if (params != null && params.size() > 0) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    par.setParameter(entry.getKey(), entry.getValue());
                    parts.add(new StringPart(entry.getKey(), entry.getValue(), charset.name()));
                }
            }
            MultipartRequestEntity entity = new MultipartRequestEntity(parts.toArray(new Part[parts.size()]),
                    new HttpMethodParams());
            method.setRequestEntity(entity);
            // 没有文件的post
        } else {
            // 设置参数
            if (params != null && params.size() > 0) {
                method.getParams().setContentCharset(charset.name());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    method.addParameter(entry.getKey(), entry.getValue());
                }
            }
        }

        try {
            client.executeMethod(method);
            if (method.getStatusCode() == HttpStatus.SC_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), charset));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
            }
        } catch (IOException e) {
        } finally {
            method.releaseConnection();
        }
        return response.toString();
    }
}
