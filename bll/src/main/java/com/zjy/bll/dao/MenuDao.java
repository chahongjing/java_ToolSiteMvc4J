package com.zjy.bll.dao;

import com.zjy.bll.common.BaseDao;
import com.zjy.bll.vo.MenuVo;
import com.zjy.entities.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface MenuDao extends BaseDao<Menu> {
    Map<String, BigDecimal> queryRepeatCount(@Param("menuId") String menuId, @Param("code") String code);
    List<MenuVo> queryParentList();
}
