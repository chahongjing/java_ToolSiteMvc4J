package com.zjy.baseframework;

import com.zjy.bll.common.BaseTestCase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/1/11.
 */
public class JedisHelperTest extends BaseTestCase {
    @Test
    public void get() throws Exception {
        JedisHelper.set("key", "myvalue");
        String abc = JedisHelper.get("key");
        Long a = JedisHelper.delete("key");
    }
}