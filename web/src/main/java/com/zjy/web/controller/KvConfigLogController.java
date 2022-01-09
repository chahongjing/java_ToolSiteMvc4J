package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import com.zjy.bll.basebean.PageBean;
import com.zjy.bll.request.KvConfigLogRequest;
import com.zjy.bll.request.KvConfigRequest;
import com.zjy.bll.service.KvConfigLogService;
import com.zjy.bll.service.KvConfigService;
import com.zjy.bll.vo.KvConfigLogVo;
import com.zjy.entities.KvConfig;
import com.zjy.entities.KvConfigLog;
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
@RequestMapping("kvConfigLog")
public class KvConfigLogController extends BaseController {

    //region 属性
    @Autowired
    private KvConfigLogService kvConfigLogSrv;
    //endregion

    @RequestMapping("queryPageList")
    public BaseResult<PageBean> queryPageList(KvConfigLogRequest request) {
        PageBean<KvConfigLogVo> pageBean = kvConfigLogSrv.queryPageById(request);
        return BaseResult.ok(pageBean);
    }
}
