package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import com.zjy.bll.basebean.PageBean;
import com.zjy.bll.request.ConfigInfoRequest;
import com.zjy.bll.service.ConfigInfoService;
import com.zjy.bll.vo.ConfigInfoVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/11/1.
 */
@RestController
@RequestMapping("/configInfo")
public class ConfigInfoController extends BaseController {
    @Autowired
    private ConfigInfoService configInfoSrv;

    @RequestMapping("/queryPageList")
    @RequiresPermissions("configInfoList_enter")
    public BaseResult<PageBean> queryPageList(ConfigInfoRequest request) {
        PageBean<ConfigInfoVo> pageBean = (PageBean<ConfigInfoVo>) configInfoSrv.queryPageList(request);
        return BaseResult.ok(pageBean);
    }

    @RequestMapping("/getDetail")
    @RequiresPermissions("configInfoEdit_enter")
    public BaseResult<ConfigInfoVo> getDetail(String id) {
        ConfigInfoVo userInfo = configInfoSrv.getVo(id);
        return BaseResult.ok(userInfo);
    }

    @PostMapping("/save")
    @RequiresPermissions("configInfo_save")
    public BaseResult<String> save(ConfigInfoVo vo) {
        configInfoSrv.save(vo);
        return BaseResult.ok();
    }

    @RequestMapping("/delete")
    @RequiresPermissions("configInfoList_delete")
    public BaseResult<String> delete(String id) {
        configInfoSrv.delete(id);
        return BaseResult.ok();
    }
}
