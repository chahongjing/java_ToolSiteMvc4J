package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import com.zjy.bll.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comm")
public class CommonController extends BaseController {

    @Autowired
    private CommonService commonSrv;

    @RequestMapping("/getNewId")
    public BaseResult<String> getNewId() {
        return BaseResult.OK(commonSrv.getNewId());
    }

    @RequestMapping("/getNewIdList")
    public BaseResult<List<String>> getNewIdList(int num) {
        return BaseResult.OK(commonSrv.getNewIdList(num));
    }

    /**
     * 返回所有枚举对象
     *
     * @return
     */
    @RequestMapping(value = "/getEnums", produces = "application/javascript;charset=UTF-8")
    public String getEnums() {
        return commonSrv.getEnums();
    }
}
