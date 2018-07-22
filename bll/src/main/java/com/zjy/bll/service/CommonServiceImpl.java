package com.zjy.bll.service;

import com.zjy.baseframework.interfaces.IHierarchyBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jyzeng on 2018/3/23.
 * 公共服务类
 */
public class CommonServiceImpl implements CommonService {

    /**
     * 层级关系排序
     * @param list 列表
     * @param <T> 类型
     * @return
     */
    public <T extends IHierarchyBase> List<T> getHierarchyList(List<T> list) {
        // 排序
        list.sort(Comparator.comparing(T::getSeq));
        // 取一级数据
        List<T> parents = list.stream().filter(item -> item.getPId() == 0).collect(Collectors.toList());
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
     * @param parent 父结点
     * @param list 列表
     * @param <T> 类型
     * @return
     */
    private <T extends IHierarchyBase> List<T> getHierarchyChildren(T parent, List<T> list) {
        List<T> result = new ArrayList<>();
        for (T child : list) {
            // 是节点子集
            if (parent.getId() == child.getPId()) {
                result.add(child);
                // 添加子集数据
                result.addAll(getHierarchyChildren(child, list));
            }
        }
        return result;
    }
}
