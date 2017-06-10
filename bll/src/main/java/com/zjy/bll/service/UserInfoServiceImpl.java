package com.zjy.bll.service;

import com.github.pagehelper.PageInfo;
import com.zjy.baseframework.BaseResult;
import com.zjy.baseframework.DbHelper;
import com.zjy.baseframework.enums.ResultStatus;
import com.zjy.bll.common.BaseService;
import com.zjy.bll.dao.UserInfoDao;
import com.zjy.bll.request.UserInfoRequest;
import com.zjy.entities.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author chahongjing
 * @create 2016-12-05 22:16
 */
@Service
public class UserInfoServiceImpl extends BaseService<UserInfoDao, UserInfo> implements UserInfoService {

    public BaseResult<String> login(UserInfo user) {
        BaseResult<String> result = new BaseResult<>();
        UserInfo dbUser = dao.getByUserCode(user.getUserCode());

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

    public List<UserInfo> query(UserInfo entity) {
        return super.query(entity);
    }

    public PageInfo<UserInfo> queryPage(UserInfoRequest request) {
        HashMap<String, Object> query = new HashMap<>();
        return super.queryPage(request, query);
    }

    @Override
    @Transactional
    public int add(UserInfo entity) {
        return dao.add(entity);
    }

    @Override
    @Transactional
    public int delete(String id) {
        return dao.delete(id);
    }

    @Override
    @Transactional
    public void testtr() {
        UserInfo user = new UserInfo();
        user.setUserGuid("D8E6B877-3645-4063-A25C-495606B95349");
        user.setUserCode("testuser");
        user.setUserName("测试数据");
        user.setPassword("1");
        user.setSex(true);
        user.setBirthday(new Date());
        user.setIsSystem(true);

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(sdf.format(user.getBirthday()));

        dao.add(user);
        int a = 1 / 0;
        dao.delete("D8E6B877-3645-4063-A25C-495606B95349");
    }

    @Override
    public List<UserInfo> test(String aa, UserInfo bb) {
        return dao.test(aa, bb);
    }

    @Override
    public UserInfo getByUserCode(String userCode){
        return dao.getByUserCode(userCode);
    }
}
