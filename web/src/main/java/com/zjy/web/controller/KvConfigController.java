package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import com.zjy.bll.basebean.PageBean;
import com.zjy.bll.request.KvConfigRequest;
import com.zjy.bll.service.KvConfigService;
import com.zjy.entities.KvConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chahongjing
 * @create 2016-12-10 15:27
 */
@RestController
@RequestMapping("kvConfig")
public class KvConfigController extends BaseController {

    //region 属性
    @Autowired
    private KvConfigService kvConfigSrv;
    //endregion

    @RequestMapping("queryPageList")
    public BaseResult<PageBean> queryPageList(KvConfigRequest request) {
        PageBean<KvConfig> pageBean = kvConfigSrv.queryPageList(request);
        return BaseResult.ok(pageBean);
    }

    @RequestMapping("getDetail")
    public BaseResult<KvConfig> getDetail(String id) {
        KvConfig kvConfig = kvConfigSrv.get(id);
        return BaseResult.ok(kvConfig);
    }

    @PostMapping("save")
    public BaseResult<String> save(KvConfig vo) {
        kvConfigSrv.save(vo);
        return BaseResult.ok();
    }

    @RequestMapping("delete")
    public BaseResult<String> delete(String id) {
        kvConfigSrv.delete(id);
        return BaseResult.ok();
    }

    @RequestMapping("removeAllCache")
    public BaseResult<String> removeAllCache() {
        kvConfigSrv.removeAllCache();
        return BaseResult.ok();
    }
}
