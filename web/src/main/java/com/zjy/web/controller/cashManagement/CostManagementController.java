package com.zjy.web.controller.cashManagement;

import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.BaseResult;
import com.zjy.bll.common.ShiroRealm;
import com.zjy.bll.request.cashManagement.CostRequest;
import com.zjy.bll.service.cashManagement.CostService;
import com.zjy.entities.UserInfo;
import com.zjy.entities.cashManagement.Cost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chahongjing on 2017/6/10.
 */
@Controller
@RequestMapping("/cost")
public class CostManagementController {

    @Autowired
    private CostService costSvc;

    @Autowired
    private ShiroRealm shiroRealm;

    @RequestMapping("/")
    public String index() {
        return "cashManagement/cost/index";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public BaseResult getList(HttpServletRequest request) {
        BaseResult<PageInfo<Cost>> result = BaseResult.OK();
        UserInfo user = shiroRealm.getCurrentUser();
        CostRequest costRequest = new CostRequest();
        costRequest.setOrderBy("CreatedOn DESC");
        costRequest.setUserId(user.getUserGuid());
        result.setValue(costSvc.getList(costRequest));
        return result;
    }
}
