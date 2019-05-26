package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import com.zjy.bll.annotations.LogMessage;
import com.zjy.bll.basebean.PageBean;
import com.zjy.bll.request.OperLogRequest;
import com.zjy.bll.service.OperLogService;
import com.zjy.bll.vo.OperLogVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("operlog")
public class OperLogController extends BaseController {

    @Autowired
    private OperLogService operLogService;

    @RequestMapping("queryPageList")
    @RequiresPermissions("operLogList_enter")
    @LogMessage(doLog = false)
    public BaseResult<PageBean> queryPageList(OperLogRequest request) {
        PageBean<OperLogVo> pageBean = operLogService.queryPageList(request);
        return BaseResult.ok(pageBean);
    }

    @RequestMapping("getDetail")
    @RequiresPermissions("operLogEdit_enter")
    @LogMessage(doLog = false)
    public BaseResult<OperLogVo> getDetail(String id) {
        OperLogVo operLogVo = operLogService.get(id);
        return BaseResult.ok(operLogVo);
    }

    @RequestMapping("delete")
    @RequiresPermissions("operLogList_delete")
    @LogMessage(doLog = false)
    public BaseResult<OperLogVo> delete(String id) {
        operLogService.delete(id);
        return BaseResult.ok();
    }

    @RequestMapping("deleteAll")
    @LogMessage(doLog = false)
    public BaseResult<OperLogVo> deleteAll() {
        operLogService.deleteAll();
        return BaseResult.ok();
    }
}
