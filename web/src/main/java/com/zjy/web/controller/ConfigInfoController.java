package com.zjy.web.controller;

import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.BaseResult;
import com.zjy.bll.request.ConfigInfoRequest;
import com.zjy.bll.service.ConfigInfoService;
import com.zjy.bll.vo.ConfigInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/11/1.
 */
@Controller
@RequestMapping("/configInfo")
public class ConfigInfoController extends BaseController {
    @Autowired
    private ConfigInfoService configInfoSrv;

    @RequestMapping("/queryPageList")
    @ResponseBody
    public BaseResult<PageInfo> queryPageList(ConfigInfoRequest userInfo) {
        PageInfo<ConfigInfoVo> pageInfo = (PageInfo<ConfigInfoVo>) configInfoSrv.queryPageList(userInfo);
        return BaseResult.OK(pageInfo);
    }

    @RequestMapping("/getConfigInfo")
    @ResponseBody
    public BaseResult<ConfigInfoVo> getConfigInfo(String id) {
        ConfigInfoVo userInfo = configInfoSrv.getVo(id);
        return BaseResult.OK(userInfo);
    }

    @RequestMapping("/saveConfigInfo")
    @ResponseBody
    public BaseResult<String> saveConfigInfo(ConfigInfoVo configInfo) {
        configInfoSrv.saveConfigInfo(configInfo);
        return BaseResult.OK("");
    }

    @RequestMapping("/delete")
    @ResponseBody
    public BaseResult<String> delete(String configInfoId) {
        configInfoSrv.delete(configInfoId);
        return BaseResult.OK("");
    }
}
