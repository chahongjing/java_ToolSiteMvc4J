package com.zjy.web.controller;

import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.BaseResult;
import com.zjy.bll.request.PermissionRequest;
import com.zjy.bll.service.FunctionInfoService;
import com.zjy.bll.service.PermissionService;
import com.zjy.bll.vo.FunctionInfoVo;
import com.zjy.bll.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController {

    @Autowired
    private PermissionService permissionSrv;

    @Autowired
    private FunctionInfoService functionInfoSrv;

        @RequestMapping("/list")
        public String list(String menuId, Model model) {
            model.addAttribute("menuId", menuId);
            return "sys/permission";
        }

        @RequestMapping("/permissionEdit")
        public String editPermission(String permissionId, String menuId, Model model) {
            model.addAttribute("permissionId", permissionId);
            model.addAttribute("menuId", menuId);
            return "sys/permissionEdit";
        }

        @RequestMapping("/queryPageList")
        @ResponseBody
        public BaseResult<PageInfo<PermissionVo>> queryPageList(PermissionRequest request) {
            PageInfo<PermissionVo> pageInfo = (PageInfo<PermissionVo>)permissionSrv.queryPageList(request);
            return BaseResult.OK(pageInfo);
        }

        @RequestMapping("/getDetail")
        @ResponseBody
        public BaseResult<PermissionVo> getDetail(String id, String functionId) {
            PermissionVo permissionVo = permissionSrv.getVo(id);
            if(!permissionVo.getIsSave()) {
                permissionVo.setFunctionId(functionId);
                FunctionInfoVo functionInfo = functionInfoSrv.getVo(functionId);
                permissionVo.setFunctionName(functionInfo.getName());
            }
            return BaseResult.OK(permissionVo);
        }

        @RequestMapping("/save")
        @ResponseBody
        public BaseResult<String> save(PermissionVo vo) {
            permissionSrv.save(vo);
            return BaseResult.OK("");
        }

    @RequestMapping("/delete")
    @ResponseBody
    public BaseResult<String> delete(String id) {
        permissionSrv.delete(id);
        return BaseResult.OK("");
    }
}
