package com.zjy.baseframework;

import com.zjy.bll.common.BaseTestCase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/3/15.
 */
public class SecurityHelperTest extends BaseTestCase {
    @Test
    public void encode() throws Exception {
        String abc = "曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅" +
                "曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅" +
                "曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅" +
                "曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅" +
                "曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅" +
                "曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅" +
                "曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅曾军毅";
        System.out.println(SecurityHelper.encode(abc));
    }

}