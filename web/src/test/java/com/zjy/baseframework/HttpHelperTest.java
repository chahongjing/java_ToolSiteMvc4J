package com.zjy.baseframework;

import com.zjy.bll.common.BaseTestCase;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chahongjing on 2018/1/21.
 */
public class HttpHelperTest extends BaseTestCase {

    private String baseUrl = "http://localhost:8080/ToolSiteMvc4J/";

    @Test
    public void testGet() {
        String url = baseUrl + "/test/testGet";
        Map<String, String> map = new HashMap<>();
        map.put("age", "28");
//        try {
//            String result = HttpHelper.doGetToString(url, map);
//            logger.info("testGet结果：{}", result);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testPost() {
        String url = baseUrl + "/test/testPost";
        Map<String, String> map = new HashMap<>();
        map.put("age", "28");
//        try {
//            String result = HttpHelper.doPostToString(url, map);
//            logger.info("testPost结果：{}", result);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testPostWithFile() {
        String url = baseUrl + "/test/testPostWithFile";
        Map<String, String> map = new HashMap<>();
        Map<String, String> fileList = new HashMap<>();
        map.put("age", "28");
        fileList.put("myfile", "d:\\a.xls");
//        try {
//            String result = HttpHelper.doPostToString(url, map, fileList);
//            logger.info("testPostWithFile结果：{}", result);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testGetEntity() {
        String url = baseUrl + "/test/testGetEntity";
        Map<String, String> params = new HashMap<>();
        params.put("name", "java从入门到精通");
        //UserInfo userInfo = HttpHelper.get(url, params, UserInfo.class);
    }

    @Test
    public void testPostEntity() {
        String url = baseUrl + "/test/testPostEntity";
        Map<String, String> params = new HashMap<>();
        params.put("name", "java从入门到精通");
        //UserInfo userInfo = HttpHelper.get(url, params, UserInfo.class);
    }
}