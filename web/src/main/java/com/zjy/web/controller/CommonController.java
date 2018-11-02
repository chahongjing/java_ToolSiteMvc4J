package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import com.zjy.bll.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
@RequestMapping("/comm")
public class CommonController extends BaseController {

    @Autowired
    private CommonService commonSrv;

    @RequestMapping("/getId")
    @ResponseBody
    public BaseResult<String> userList() {
        return BaseResult.OK(UUID.randomUUID().toString());
    }

    /**
     * 返回所有枚举对象
     * @return
     */
    @RequestMapping(value = "/getEnums", produces="application/javascript;charset=UTF-8")
    @ResponseBody
    public String getEnums() {
        return commonSrv.getEnums();
    }
}
