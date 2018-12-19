package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import com.zjy.bll.baseBean.PageBean;
import com.zjy.bll.request.FunctionInfoRequest;
import com.zjy.bll.service.FunctionInfoService;
import com.zjy.bll.service.MenuService;
import com.zjy.bll.vo.FunctionInfoVo;
import com.zjy.bll.vo.MenuVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/function")
public class FunctionInfoController extends BaseController {

    @Autowired
    private FunctionInfoService functionInfoSrv;

    @Autowired
    private MenuService menuSrv;

    @RequestMapping("/queryPageList")
    @ResponseBody
    @RequiresPermissions("functionList_enter")
    public BaseResult<PageBean<FunctionInfoVo>> queryPageList(FunctionInfoRequest request) {
        PageBean<FunctionInfoVo> pageBean = (PageBean<FunctionInfoVo>) functionInfoSrv.queryPageList(request);
        return BaseResult.OK(pageBean);
    }

    @RequestMapping("/getDetail")
    @ResponseBody
    @RequiresPermissions("functionEdit_enter")
    public BaseResult<FunctionInfoVo> getDetail(String id, String menuId) {
        FunctionInfoVo functionInfoVo = functionInfoSrv.getVo(id);
        if (!functionInfoVo.getIsSave()) {
            functionInfoVo.setMenuId(menuId);
            MenuVo menu = menuSrv.getVo(menuId);
            functionInfoVo.setMenuName(menu.getName());
        }
        return BaseResult.OK(functionInfoVo);
    }

    @RequestMapping("/save")
    @ResponseBody
    @RequiresPermissions("functionEdit_save")
    public BaseResult<String> save(FunctionInfoVo vo) {
        functionInfoSrv.save(vo);
        return BaseResult.OK("");
    }

    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions("functionList_delete")
    public BaseResult<String> delete(String id) {
        functionInfoSrv.delete(id);
        return BaseResult.OK("");
    }

    @RequestMapping("/queryFunctionList")
    @ResponseBody
    @RequiresPermissions("functionEdit_enter")
    public BaseResult<List<FunctionInfoVo>> queryFunctionList() {
        List<FunctionInfoVo> list = functionInfoSrv.queryFunctionList();
        return BaseResult.OK(list);
    }
}
