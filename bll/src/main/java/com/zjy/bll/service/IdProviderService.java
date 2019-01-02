package com.zjy.bll.service;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2019/1/2.
 */
public interface IdProviderService<T> {

    String getString();

    List<String> getStringList(int num);

    long getLong();

    UUID getUUID();

    List<T> getList(int num);
}
