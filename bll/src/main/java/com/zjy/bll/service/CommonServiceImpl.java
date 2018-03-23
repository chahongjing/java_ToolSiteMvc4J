package com.zjy.bll.service;

import com.zjy.bll.dto.HierarchyBase;

import java.awt.*;
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
    public <T extends HierarchyBase> List<T> shiTiJieGouPaiXu(List<T> list) {
        list.sort(Comparator.comparing(T::getXuhao));
        List<T> parents = list.stream().filter(item -> item.getParentId() == null || item.getParentId() == 0).collect(Collectors.toList());
        List<T> result = new ArrayList<>();
        for (T parent : parents) {
            result.add(parent);
            result.addAll(getChildren(parent, list));
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
    private <T extends HierarchyBase> List<T> getChildren(T parent, List<T> list) {
        List<T> result = new ArrayList<>();
        for (T child : list) {
            if (parent.getId().equals(child.getParentId())) {
                result.add(child);
                result.addAll(getChildren(child, list));
            }
        }
        return result;
    }
}
