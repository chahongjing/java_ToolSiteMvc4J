package com.zjy.baseframework;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
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
import java.io.*;
import java.net.URLDecoder;
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
    /**
     * 发送get请求
     *
     * @param url         请求地址
     * @param queryString 查询参数
     * @return
     */
    public static String doGet(String url, String queryString) {
        return doGet(url, queryString, StandardCharsets.UTF_8);
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
                Header contentHead = method.getResponseHeader(HttpHeaders.CONTENT_DISPOSITION);
                //开始解析文件头信息，这里使用的是HeaderElement对象作为文件头的基础信息
                HeaderElement[] elements = contentHead.getElements();
                String filerName = null;
                for (HeaderElement el : elements) {
                    //遍历，获取filename。filename信息对应的就是下载文件的文件名称。
                    org.apache.commons.httpclient.NameValuePair pair = el.getParameterByName("filename");
                    if (pair != null) {
                        System.out.println(pair.getName() + ":" + pair.getValue());
                        filerName = pair.getValue();
                    }
                }

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
     * @param url    请求地址
     * @param params 请求参数
     * @return
     */
    public static String doPost(String url, Map<String, String> params) {
        return doPost(url, params, StandardCharsets.UTF_8);
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
        return doPost(url, params, fileList, StandardCharsets.UTF_8);
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

    public static String doPostNew() {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("");
            FileBody fileBody = new FileBody(new File(""));
            HttpEntity entity = MultipartEntityBuilder.create()
                    .addPart("file", fileBody)
                    .addTextBody("files", "", ContentType.TEXT_PLAIN.withCharset(StandardCharsets.UTF_8))
                    .build();
            httpPost.setEntity(entity);

            List<NameValuePair> nvps = new ArrayList <>();
            nvps.add(new BasicNameValuePair("username", "vip"));
            nvps.add(new BasicNameValuePair("password", "secret"));
            // httpPost.setEntity(new UrlEncodedFormEntity(nvps));

            CloseableHttpResponse response = client.execute(httpPost);
            // 服务器返回码
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_NOT_MODIFIED) {
                // 服务器返回内容
                entity = response.getEntity();
                if (null != entity) {
                    String respStr = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                    respStr = URLDecoder.decode(respStr, StandardCharsets.UTF_8.displayName());
                    // 释放资源
                    EntityUtils.consume(entity);
                    return respStr;
                }
            } else {
                return "失败！";
            }
        } catch (IOException ex) {
            return "异常，请稍后重试！";
        }
        return "";
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

    public static Object post(Class<?> clazz) {
        Client client = ClientBuilder.newClient();
        //Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class).register(MultiPartFeature.class);// 注册json 支持
        String path = "http://localhost:8080/api/rest";
        WebTarget target = client.target(path + "/hello/returnentity");
        Entity<Object> entity = Entity.entity(new Object(), MediaType.APPLICATION_JSON);
        Response response = target.request(MediaType.APPLICATION_JSON).post(entity);
        Object user = response.readEntity(clazz);
        response.close();
        return user;
    }
}
