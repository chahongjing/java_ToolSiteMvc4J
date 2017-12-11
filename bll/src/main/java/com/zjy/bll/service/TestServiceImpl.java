package com.zjy.bll.service;

import org.springframework.stereotype.Service;

/**
 * Created by chahongjing on 2017/12/11.
 */
@Service
public class TestServiceImpl implements TestService{
    @Override
    public int add(int a, int b) {
        return a + b;
    }
    @Override
    public int sub(int a, int b) {
        return a - b;
    }
}
