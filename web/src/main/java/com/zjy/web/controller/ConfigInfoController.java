package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import com.zjy.bll.baseBean.PageBean;
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
    public BaseResult<PageBean> queryPageList(ConfigInfoRequest request) {
        PageBean<ConfigInfoVo> pageBean = (PageBean<ConfigInfoVo>) configInfoSrv.queryPageList(request);
        return BaseResult.OK(pageBean);
    }

    @RequestMapping("/getDetail")
    @ResponseBody
    public BaseResult<ConfigInfoVo> getDetail(String id) {
        ConfigInfoVo userInfo = configInfoSrv.getVo(id);
        return BaseResult.OK(userInfo);
    }

    @RequestMapping("/save")
    @ResponseBody
    public BaseResult<String> save(ConfigInfoVo vo) {
        configInfoSrv.save(vo);
        return BaseResult.OK("");
    }

    @RequestMapping("/delete")
    @ResponseBody
    public BaseResult<String> delete(String id) {
        configInfoSrv.delete(id);
        return BaseResult.OK("");
    }
}
