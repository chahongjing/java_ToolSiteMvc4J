package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import com.zjy.baseframework.CacheFromRedis;
import com.zjy.bll.enums.SwitchEnum;
import com.zjy.entities.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 开关管理
 */
@Slf4j
@RestController
@RequestMapping("/switch")
public class SwitchController extends BaseController {

    Map<String, String> switchMap = new HashMap<>();

    CacheFromRedis cache;

    /**
     * 开关列表
     * @return
     */
    @GetMapping("/queryAllSwitchList")
    public BaseResult<List<Map<String, String>>> getSwitchList() {
        Map<String, String> switchs = new HashMap<>();
        for (SwitchEnum value : SwitchEnum.values()) {
            switchs.put(value.toString(), SwitchEnum.SWITCH_CLOSE);
        }
//        Map<String, String> switchList = cache.hGetAll(SwitchEnum.SWITCH_KEY);
        Map<String, String> switchList = switchMap;
        if(MapUtils.isNotEmpty(switchList)) {
            for (Map.Entry<String, String> entry : switchs.entrySet()) {
                String o = switchList.get(entry.getKey());
                if(SwitchEnum.SWITCH_OPEN.equals(o)) {
                    entry.setValue(o);
                }
            }
        }
        List<Map<String, String>> result = new ArrayList<>();
        for (Map.Entry<String, String> entry : switchs.entrySet()) {
            Map<String, String> temp = new HashMap<>();
            temp.put("key", entry.getKey());
            temp.put("value", entry.getValue());
            temp.put("name", SwitchEnum.valueOf(entry.getKey()).getName());
            result.add(temp);
        }
        return BaseResult.ok(result);
    }

    /**
     * 打开关闭开关
     * @param key
     * @param value
     * @return
     */
    @GetMapping("/updateSwitch")
    public BaseResult<String> updateSwitch(String key, String value) {
        if(StringUtils.isBlank(key)) {
            return BaseResult.no("开关不能为空！");
        }
        String op;
        if(SwitchEnum.SWITCH_OPEN.equals(value)) {
            switchMap.put(key, value);
//            cache.hSet(SwitchEnum.SWITCH_KEY, key, value);
            op = "打开";
        } else {
            switchMap.remove(key);
//            cache.hDelete(SwitchEnum.SWITCH_KEY, key);
            op = "关闭";
        }
//        Login userInfo = getLogin();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("1");
        log.info("{} {} 开关:{}", userInfo.getUserId(), op, SwitchEnum.valueOf(key).getName());
        return BaseResult.ok(String.format("%s 开关 %s 成功！", op, SwitchEnum.valueOf(key).getName()));
    }
}
