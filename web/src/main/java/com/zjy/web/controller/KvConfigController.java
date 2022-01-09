package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import com.zjy.bll.basebean.PageBean;
import com.zjy.bll.request.KvConfigRequest;
import com.zjy.bll.service.KvConfigService;
import com.zjy.entities.KvConfig;
import com.zjy.entities.UserInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    @RequiresPermissions("kvConfig_enter")
    public BaseResult<PageBean> queryPageList(KvConfigRequest request) {
        PageBean<KvConfig> pageBean = kvConfigSrv.queryPageList(request);
        return BaseResult.ok(pageBean);
    }

    @RequestMapping("getDetail")
    @RequiresPermissions("kvConfig_detail_enter")
    public BaseResult<KvConfig> getDetail(String id) {
        KvConfig kvConfig = kvConfigSrv.get(id);
        return BaseResult.ok(kvConfig);
    }

    @PostMapping("save")
    @RequiresPermissions("kvConfig_save")
    public BaseResult<String> save(KvConfig vo) {
        UserInfo currentUser = getCurrentUser();
        kvConfigSrv.save(vo, currentUser);
        return BaseResult.ok();
    }

    @RequestMapping("delete")
    @RequiresPermissions("kvConfig_delete")
    public BaseResult<String> delete(String id) {
        UserInfo currentUser = getCurrentUser();
        kvConfigSrv.delete(id, currentUser);
        return BaseResult.ok();
    }

    @RequestMapping("removeAllCache")
    @RequiresPermissions("kvConfig_clear_cache")
    public BaseResult<String> removeAllCache() {
        kvConfigSrv.removeAllCache();
        return BaseResult.ok();
    }
}
