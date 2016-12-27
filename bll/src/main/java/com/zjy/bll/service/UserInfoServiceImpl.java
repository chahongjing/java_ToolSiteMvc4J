package com.zjy.bll.service;

import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.BaseResult;
import com.zjy.baseframework.enums.ResultStatus;
import com.zjy.bll.common.BaseService;
import com.zjy.bll.dao.UserInfoDao;
import com.zjy.bll.request.UserInfoRequest;
import com.zjy.entities.UserInfo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author chahongjing
 * @create 2016-12-05 22:16
 */
@Service
public class UserInfoServiceImpl extends BaseService<UserInfoDao, UserInfo> implements UserInfoService {

    public BaseResult login(UserInfo user) {
        BaseResult result = new BaseResult();
        UserInfo dbUser = super.get(user.getUserCode());

        if (dbUser == null) {
            result.setStatus(ResultStatus.NO);
            result.setMessage("用户不存在！");
            return result;
        } else if (!dbUser.getPassword().equals(user.getPassword())) {
            result.setStatus(ResultStatus.NO);
            result.setMessage("用户密码错误！");
            return result;
        }
        // 登录成功
        result.setStatus(ResultStatus.OK);
        return result;
    }

    public List<UserInfo> query(UserInfo entity){
        return super.query(entity);
    }

    public PageInfo<UserInfo> queryPage(UserInfoRequest request){
        HashMap<String, Object> query = new HashMap<>();
        return super.queryPage(request, query);
    }
}
