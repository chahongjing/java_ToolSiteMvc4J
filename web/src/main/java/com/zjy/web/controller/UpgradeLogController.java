package com.zjy.web.controller;

import com.alibaba.fastjson.JSON;
import com.zjy.baseframework.BaseResult;
import com.zjy.bll.basebean.PageBean;
import com.zjy.bll.request.KvConfigRequest;
import com.zjy.bll.request.UpgradeLogRequest;
import com.zjy.bll.service.KvConfigService;
import com.zjy.bll.service.UpgradeLogService;
import com.zjy.bll.vo.UpgradeLogVo;
import com.zjy.entities.KvConfig;
import com.zjy.entities.UpgradeLog;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chahongjing
 * @create 2016-12-10 15:27
 */
@RestController
@RequestMapping("upgradeLog")
public class UpgradeLogController extends BaseController {

    //region 属性
    @Autowired
    private UpgradeLogService upgradeLogService;
    //endregion

    @RequestMapping("queryPageList")
    public BaseResult<PageBean> queryPageList(UpgradeLogRequest request) {
        PageBean<UpgradeLogVo> pageBean = upgradeLogService.queryPageList(request);
        return BaseResult.ok(pageBean);
    }

    @RequestMapping("queryList")
    public BaseResult<List<UpgradeLogVo>> queryList(UpgradeLog log) {
        List<UpgradeLogVo> list = upgradeLogService.queryList(log);
        return BaseResult.ok(list);
    }

    @RequestMapping("getDetail")
    public BaseResult<UpgradeLog> getDetail(String id) {
        UpgradeLog kvConfig = upgradeLogService.get(id);
        return BaseResult.ok(kvConfig);
    }

    @PostMapping("save")
    public BaseResult<String> save(UpgradeLogVo vo) {
        if(CollectionUtils.isNotEmpty(vo.getContentList())) {
            vo.setContent(JSON.toJSONString(vo.getContentList()));
        }
        upgradeLogService.save(vo);
        return BaseResult.ok();
    }

    @RequestMapping("delete")
    public BaseResult<String> delete(String id) {
        upgradeLogService.delete(id);
        return BaseResult.ok();
    }
}
