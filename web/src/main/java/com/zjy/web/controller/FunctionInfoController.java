package com.zjy.web.controller;

import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.BaseResult;
import com.zjy.bll.request.FunctionInfoRequest;
import com.zjy.bll.service.FunctionInfoService;
import com.zjy.bll.service.MenuService;
import com.zjy.bll.vo.FunctionInfoVo;
import com.zjy.bll.vo.MenuVo;
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
    public BaseResult<PageInfo<FunctionInfoVo>> queryPageList(FunctionInfoRequest request) {
        PageInfo<FunctionInfoVo> pageInfo = (PageInfo<FunctionInfoVo>) functionInfoSrv.queryPageList(request);
        return BaseResult.OK(pageInfo);
    }

    @RequestMapping("/getDetail")
    @ResponseBody
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
    public BaseResult<String> save(FunctionInfoVo vo) {
        functionInfoSrv.save(vo);
        return BaseResult.OK("");
    }

    @RequestMapping("/delete")
    @ResponseBody
    public BaseResult<String> delete(String id) {
        functionInfoSrv.delete(id);
        return BaseResult.OK("");
    }

    @RequestMapping("/queryFunctionList")
    @ResponseBody
    public BaseResult<List<FunctionInfoVo>> queryFunctionList() {
        List<FunctionInfoVo> list = functionInfoSrv.queryFunctionList();
        return BaseResult.OK(list);
    }
}
