package com.zjy.bll.service;

import com.zjy.bll.common.SnowFlakeIdWorker;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2019/1/2.
 */
public class SnowFlakeServiceImpl<T extends Long> implements IdProviderService {

    private int workerId;

    private int dataCenterId;

    private SnowFlakeIdWorker sequence;

    @PostConstruct
    public void initSequence() {
        sequence = new SnowFlakeIdWorker(workerId, dataCenterId);
    }

    @Override
    public long getLong() {
        return sequence.nextId();
    }

    @Override
    public String getString() {
        return String.valueOf(getLong());
    }

    @Override
    public List<Long> getList(int num) {
        List<Long> list = new ArrayList<>();
        num = num == 0 ? 1 : num;
        for (int i = 0; i < num; i++) {
            list.add(getLong());
        }
        return list;
    }

    @Override
    public List<String> getStringList(int num) {
        List<String> list = new ArrayList<>();
        num = num == 0 ? 1 : num;
        for (int i = 0; i < num; i++) {
            list.add(getString());
        }
        return list;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public int getDataCenterId() {
        return dataCenterId;
    }

    public void setDataCenterId(int dataCenterId) {
        this.dataCenterId = dataCenterId;
    }

    public SnowFlakeIdWorker getSequence() {
        return sequence;
    }

    public void setSequence(SnowFlakeIdWorker sequence) {
        this.sequence = sequence;
    }

    @Override
    public UUID getUUID() {
        throw new UnsupportedOperationException("不支持的操作！");
    }
}
