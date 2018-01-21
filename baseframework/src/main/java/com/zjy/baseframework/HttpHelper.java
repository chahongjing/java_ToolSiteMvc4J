package com.zjy.baseframework;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by chahongjing on 2017/2/6.
 */
public class HttpHelper {

    // region get请求

    /**
     * 发送get请求
     *
     * @param url    请求地址
     * @param params 查询参数
     * @return
     */
    public static String doGetToString(String url, Map<String, String> params) throws IOException {
        return doGetToString(url, params, StandardCharsets.UTF_8);
    }

    public static byte[] doGetToByte(String url, Map<String, String> params) throws IOException {
        return doGetToByte(url, params, StandardCharsets.UTF_8);
    }

    public static String doGetToString(String url, Map<String, String> params, Charset charset) throws IOException {
        return new String(doGetToByte(url, params, charset), charset);
    }

    /**
     * 发送get请求
     *
     * @param url     请求地址
     * @param params  查询参数
     * @param charset 字符集
     * @return
     */
    public static byte[] doGetToByte(String url, Map<String, String> params, Charset charset) throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            if (params != null && !params.isEmpty()) {
                List<String> list = new ArrayList<>();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    list.add(URLEncoder.encode(entry.getKey(), charset.displayName()) + "=" + URLEncoder.encode(entry.getValue(), charset.displayName()));
                }
                url += url.indexOf("?") > -1 ? "&" : "?";
                url += StringUtils.join(list, "&");
            }
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = client.execute(httpGet);
            // 服务器返回码
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_NOT_MODIFIED) {
                org.apache.http.Header firstHeader = response.getFirstHeader(HttpHeaders.CONTENT_DISPOSITION);
                // 开始解析文件头信息，这里使用的是HeaderElement对象作为文件头的基础信息
                org.apache.http.HeaderElement[] elements1 = firstHeader.getElements();
                String filerName = null;
                for (org.apache.http.HeaderElement headerElement : elements1) {
                    //遍历，获取filename。filename信息对应的就是下载文件的文件名称。
                    org.apache.http.NameValuePair pair = headerElement.getParameterByName("filename");
                    if (pair != null) {
                        System.out.println(pair.getName() + ":" + pair.getValue());
                        filerName = pair.getValue();
                    }
                }
                // 服务器返回内容
                HttpEntity respEntity = response.getEntity();
                byte[] bytes = EntityUtils.toByteArray(respEntity);
                // 释放资源
                EntityUtils.consume(respEntity);
                return bytes;
            } else {
                return "服务器异常！".getBytes(charset);
            }
        } catch (IOException ex) {
            throw ex;
        }
    }
    // endregion

    // region post请求

    /**
     * 发送post请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return
     */
    public static String doPostToString(String url, Map<String, String> params) throws IOException {
        return doPostToString(url, params, StandardCharsets.UTF_8);
    }


    public static byte[] doPostToByte(String url, Map<String, String> params) throws IOException {
        return doPostToByte(url, params, StandardCharsets.UTF_8);
    }

    /**
     * 发送post请求
     *
     * @param url     请求地址
     * @param params  请求参数
     * @param charset 字符集
     * @return
     */
    public static String doPostToString(String url, Map<String, String> params, Charset charset) throws IOException {
        return doPostToString(url, params, Collections.EMPTY_MAP, charset);
    }


    public static byte[] doPostToByte(String url, Map<String, String> params, Charset charset) throws IOException {
        return doPostToByte(url, params, Collections.EMPTY_MAP, charset);
    }

    // endregion

    // region post请求带文件

    /**
     * 发送post请求
     *
     * @param url      请求地址
     * @param params   请求参数
     * @param fileList 发送文件
     * @return
     */
    public static String doPostToString(String url, Map<String, String> params, Map<String, String> fileList) throws IOException {
        return doPostToString(url, params, fileList, StandardCharsets.UTF_8);
    }


    public static byte[] doPostToByte(String url, Map<String, String> params, Map<String, String> fileList) throws IOException {
        return doPostToByte(url, params, fileList, StandardCharsets.UTF_8);
    }


    public static String doPostToString(String url, Map<String, String> params, Map<String, String> fileList, Charset charset) throws IOException {
        return new String(doPostToByte(url, params, fileList, charset), charset);
    }


    public static byte[] doPostToByte(String url, Map<String, String> params, Map<String, String> fileList, Charset charset) throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            MultipartEntityBuilder entity = MultipartEntityBuilder.create();
            // 文件
            if (fileList != null && !fileList.isEmpty()) {
                for (Map.Entry<String, String> entry : fileList.entrySet()) {
                    entity.addPart(entry.getKey(), new FileBody(new File(entry.getValue())));
                }
                if (params != null) {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        //entity.addPart(entry.getKey(), new StringBody(entry.getValue(), charset));
                        entity.addTextBody(entry.getKey(), entry.getValue(), ContentType.TEXT_PLAIN.withCharset(charset));
                    }
                }
                httpPost.setEntity(entity.build());
            } else {
                // 非文件
                List<BasicNameValuePair> nvps = new ArrayList<>();
                if (params != null) {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                    }
                }
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, charset));
            }
            CloseableHttpResponse response = client.execute(httpPost);
            // 服务器返回码
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_NOT_MODIFIED) {
                // 服务器返回内容
                HttpEntity respEntity = response.getEntity();
                byte[] bytes = EntityUtils.toByteArray(respEntity);
                // 释放资源
                EntityUtils.consume(respEntity);
                return bytes;
            } else {
                return "失败！".getBytes(charset);
            }
        } catch (IOException ex) {
            throw ex;
        }
    }
    // endregion

    // region 返回实体
    public static <T> T get(String url, Map<String, String> params, Class<T> clazz) {
        Client client = ClientBuilder.newClient();
        // 注册json 支持
        //Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class).register(MultiPartFeature.class);
        if (params != null && !params.isEmpty()) {
            List<String> list = new ArrayList<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                try {
                    list.add(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.name()) + "="
                            + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.name()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            url += url.indexOf("?") > -1 ? "&" : "?";
            url += String.join("&", list);
        }
        WebTarget target = client.target(url);
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        T user = response.readEntity(clazz);
        response.close();
        return user;
    }

    public static <T> T post(String url, Object params, Class<T> clazz) {
        Client client = ClientBuilder.newClient();
        //Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class).register(MultiPartFeature.class);// 注册json 支持
        WebTarget target = client.target(url);
        Entity<Object> entity = Entity.entity(params, MediaType.APPLICATION_JSON);
        Response response = target.request(MediaType.APPLICATION_JSON).post(entity);
        T user = response.readEntity(clazz);
        response.close();
        return user;
    }

    public static Object get(Class<?> clazz) {
        Client client = ClientBuilder.newClient();
        //Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class).register(MultiPartFeature.class);// 注册json 支持
        String path = "http://localhost:8080/api/rest";
        WebTarget target = client.target(path + "/hello/returnentity");
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        Object user = response.readEntity(clazz);
        response.close();
        return user;
    }
    // endregion
}

