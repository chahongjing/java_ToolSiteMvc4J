package com.zjy.bll.service;

import com.alibaba.fastjson.JSON;
import com.zjy.bll.basebean.PageBean;
import com.zjy.bll.common.BaseService;
import com.zjy.bll.dao.UpgradeLogDao;
import com.zjy.bll.request.UpgradeLogRequest;
import com.zjy.bll.vo.UpgradeLogItem;
import com.zjy.bll.vo.UpgradeLogVo;
import com.zjy.entities.UpgradeLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UpgradeLogServiceImpl extends BaseService<UpgradeLogDao, UpgradeLog>  implements UpgradeLogService {
    @Override
    @Transactional
    public int add(UpgradeLog config) {
        return super.add(config);
    }

    /**
     * 修改用户
     *
     * @param config
     * @return
     */
    @Override
    @Transactional
    public int update(UpgradeLog config) {
        return super.update(config);
    }

    @Override
    public UpgradeLog get(String id) {
        UpgradeLog upgradeLog = super.get(id);
        return entityToVo(upgradeLog);
    }

    @Override
    @Transactional
    public int delete(String id) {
        return super.delete(id);
    }

    /**
     * 保存用户
     *
     * @param config
     */
    @Transactional
    public void save(UpgradeLog config) {
        UpgradeLog voDb = this.get(config.getId());
//        beforeCheck(config);
        // 处理密码
        if (voDb != null) {
            update(config);
        } else {
            config.setCreateTime(new Date());
            add(config);
        }
    }

    @Override
    public PageBean<UpgradeLogVo> queryPageList(UpgradeLogRequest request) {
        UpgradeLog po = new UpgradeLog();
        po.setTitle(request.getTitle());
        PageBean<UpgradeLogVo> upgradeLogPageBean = (PageBean<UpgradeLogVo>) super.queryPageList(request, po);
        for (UpgradeLogVo upgradeLogVo : upgradeLogPageBean.getList()) {
            convertContent(upgradeLogVo);
        }
        return upgradeLogPageBean;
    }

    @Override
    public List<UpgradeLogVo> queryList(UpgradeLog log) {
        List<UpgradeLogVo> upgradeLogVos = (List<UpgradeLogVo>) super.queryList(log);
        for (UpgradeLogVo upgradeLogVo : upgradeLogVos) {
            convertContent(upgradeLogVo);
        }
        return upgradeLogVos;
    }

    private UpgradeLogVo entityToVo(UpgradeLog log) {
        if(log == null) return null;
        UpgradeLogVo vo = new UpgradeLogVo();
        vo.setId(log.getId());
        vo.setUpgradeTime(log.getUpgradeTime());
        vo.setCreateTime(log.getCreateTime());
        vo.setTitle(log.getTitle());
        vo.setContent(log.getContent());
        vo.setContentList(convertToLogItem(vo.getContent()));
        return vo;
    }

    private List<UpgradeLogItem> convertToLogItem(String content) {
        if (StringUtils.isNotBlank(content)) {
            return JSON.parseArray(content, UpgradeLogItem.class);
        }
        return new ArrayList<>();
    }

    private UpgradeLogVo convertContent(UpgradeLogVo vo) {
        vo.setContentList(convertToLogItem(vo.getContent()));
        return vo;
    }
}
