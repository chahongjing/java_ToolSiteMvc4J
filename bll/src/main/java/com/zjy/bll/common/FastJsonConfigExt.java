package com.zjy.bll.common;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by Administrator on 2018/11/15.
 */
public class FastJsonConfigExt extends FastJsonConfig {
    public FastJsonConfigExt() {
        super();
        SerializeConfig serializeConfig = SerializeConfig.globalInstance;
        serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
        serializeConfig.put(Long.class, ToStringSerializer.instance);
        serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
        serializeConfig.addFilter(Date.class, new DateFormaterFilter());
        this.setSerializeConfig(serializeConfig);
    }
}