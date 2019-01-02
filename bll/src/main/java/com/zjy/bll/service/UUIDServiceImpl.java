package com.zjy.bll.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2019/1/2.
 */
@Service("uuidService")
public class UUIDServiceImpl<T extends UUID> implements IdProviderService {
    @Override
    public long getLong() {
        throw new UnsupportedOperationException("不支持的操作！");
    }

    @Override
    public UUID getUUID() {
        return UUID.randomUUID();
    }

    @Override
    public List<UUID> getList(int num) {
        List<UUID> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(getUUID());
        }
        return list;
    }

    @Override
    public String getString() {
        return getUUID().toString().replace("-", StringUtils.EMPTY);
    }

    @Override
    public List<String> getStringList(int num) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(getString());
        }
        return list;
    }
}
