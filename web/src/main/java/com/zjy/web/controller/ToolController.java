package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import com.zjy.baseframework.enums.DbType;
import com.zjy.bll.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2018/2/27.
 */
@Controller
@RequestMapping("/tool")
public class ToolController extends BaseController {
    @Value("${db.url}")
    private String dbUrl;
    @Value("${db.userName}")
    private String dbUser;
    @Value("${db.password}")
    private String dbPassword;

    @Autowired
    private ToolService toolSrv;
    //region 数据库表转类
    @RequestMapping("/tableToObject")
    public String tableToObject(Model model) {
        model.addAttribute("dbUrl", Objects.toString(dbUrl, "jdbc:oracle:thin:@127.0.0.1:1521:orcl"));
        model.addAttribute("dbUser", Objects.toString(dbUser, "zjy"));
        model.addAttribute("dbPassword", Objects.toString(dbPassword, "1024"));
        List<String> list = new ArrayList<>();
        list.add("oracle：jdbc:oracle:thin:@127.0.0.1:1521:orcl");
        list.add("mysql：jdbc:mysql://localhost/toolsitemvc4j");
        list.add("sqlserver：jdbc:sqlserver://PC201404190064\\\\MSSQL; DatabaseName=ToolSiteMvc4J");
        list.add("sqlite：jdbc:sqlite://d://app.db");
        model.addAttribute("dbUrlList", list);
        return "tools/tableToObject";
    }

    @RequestMapping("/getTableInfo")
    @ResponseBody
    public BaseResult getTableInfo(String type, String url, String user, String password, String tableName) {
        DbType dbType = DbType.getDbTypeByName(type);
        String tableInfo = toolSrv.getTableInfo(dbType, url, user, password, tableName);
        return BaseResult.OK(tableInfo);
    }
    @RequestMapping("/sqlGenerate")
    public String sqlGenerate() {
        return "tools/sqlGenerate";
    }
}
