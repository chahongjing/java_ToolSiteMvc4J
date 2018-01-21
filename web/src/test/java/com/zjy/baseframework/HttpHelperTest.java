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

    @Test
    public void testGet() {
        String url = "http://localhost:8080/ToolSiteMvc4J/test/testGet.do";
        Map<String, String> map = new HashMap<>();
        map.put("age", "28");
        try {
            String result = HttpHelper.doGetToString(url, map);
            logger.info("testGet结果：{}", result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPost() {
        String url = "http://localhost:8080/ToolSiteMvc4J/test/testPost.do";
        Map<String, String> map = new HashMap<>();
        map.put("age", "28");
        try {
            String result = HttpHelper.doPostToString(url, map);
            logger.info("testPost结果：{}", result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPostWithFile() {
        String url = "http://localhost:8080/ToolSiteMvc4J/test/testPostWithFile.do";
        Map<String, String> map = new HashMap<>();
        Map<String, String> fileList = new HashMap<>();
        map.put("age", "28");
        fileList.put("myfile", "d:\\a.xls");
        try {
            String result = HttpHelper.doPostToString(url, map, fileList);
            logger.info("testPostWithFile结果：{}", result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}