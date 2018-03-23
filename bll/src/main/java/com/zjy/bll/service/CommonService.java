package com.zjy.bll.service;

import com.zjy.bll.dto.HierarchyBase;

import java.util.List;

/**
 * Created by jyzeng on 2018/3/23.
 * 公共服务类
 */
public interface CommonService {
    /**
     * 层级关系排序
     * @param list 列表
     * @param <T> 类型
     * @return
     */
    <T extends HierarchyBase> List<T> shiTiJieGouPaiXu(List<T> list);
}
