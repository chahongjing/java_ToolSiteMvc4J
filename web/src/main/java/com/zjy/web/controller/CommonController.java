package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
@RequestMapping("/comm")
public class CommonController extends BaseController {

    @RequestMapping("/getId")
    @ResponseBody
    public BaseResult<String> userList() {
        return BaseResult.OK(UUID.randomUUID().toString());
    }
}
