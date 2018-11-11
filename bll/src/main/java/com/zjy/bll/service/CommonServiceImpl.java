package com.zjy.bll.service;

import com.alibaba.fastjson.JSON;
import com.zjy.baseframework.EnumHelper;
import com.zjy.baseframework.beans.EnumBean;
import com.zjy.baseframework.interfaces.IHierarchyBase;
import com.zjy.baseframework.mybatis.IBaseEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jyzeng on 2018/3/23.
 * 公共服务类
 */
@Service
public class CommonServiceImpl implements CommonService {

    /**
     * 层级关系排序
     *
     * @param list 列表
     * @param <T>  类型
     * @return
     */
    public <T extends IHierarchyBase> List<T> getHierarchyList(List<T> list) {
        // 排序
        list.sort(Comparator.comparing(T::getSeq));
        // 取一级数据
        List<T> parents = list.stream().filter(item -> StringUtils.isBlank(item.getPId())).collect(Collectors.toList());
        List<T> result = new ArrayList<>();
        for (T parent : parents) {
            result.add(parent);
            // 添加子集数据
            result.addAll(getHierarchyChildren(parent, list));
        }
        return result;
    }

    /**
     * 获取子集数据
     *
     * @param parent 父结点
     * @param list   列表
     * @param <T>    类型
     * @return
     */
    private <T extends IHierarchyBase> List<T> getHierarchyChildren(T parent, List<T> list) {
        List<T> result = new ArrayList<>();
        for (T child : list) {
            // 是节点子集
            if (parent.getId().equals(child.getPId())) {
                result.add(child);
                // 添加子集数据
                result.addAll(getHierarchyChildren(child, list));
            }
        }
        return result;
    }

    public String getEnums() {
        Map<Class<IBaseEnum>, Map<String, EnumBean>> enumBeanList = EnumHelper.getEnumBeanList();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Class<IBaseEnum>, Map<String, EnumBean>> classMapEntry : enumBeanList.entrySet()) {
            sb.append(String.format("window.%s=%s;\r\n", classMapEntry.getKey().getSimpleName(), JSON.toJSONString(classMapEntry.getValue())));
        }
        return sb.toString();
    }
}
