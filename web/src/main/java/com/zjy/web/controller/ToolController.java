package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import com.zjy.bll.service.ToolService;
import com.zjy.entities.TableColumnInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2018/2/27.
 */
@Controller
@RequestMapping("/tool")
public class ToolController extends BaseController {

    @Autowired
    private ToolService toolSrv;
    //region 数据库表转类
    @RequestMapping("/tableToObject")
    public String tableToObject() {
        return "tableToObject";
    }

    @RequestMapping("/getTableInfo")
    @ResponseBody
    public BaseResult getTableInfo(String type, String url, String user, String password, String tableName) {
        String tableInfo = toolSrv.getTableInfo(type, url, user, password, tableName);
        return BaseResult.OK(tableInfo);
    }
}
