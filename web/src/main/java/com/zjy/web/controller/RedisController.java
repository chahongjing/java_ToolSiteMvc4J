package com.zjy.web.controller;

import com.zjy.baseframework.BaseResult;
import com.zjy.baseframework.JedisUtil;
import com.zjy.bll.enums.RedisDataType;
import com.zjy.bll.enums.RedisOpType;
import com.zjy.entities.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisController {
//    @Autowired
    private JedisUtil jedisUtil = new JedisUtil();

    /**
     * 操作redis
     * @param dataType
     * @param opType
     * @param key
     * @param field
     * @param value
     * @return
     */
    @PostMapping("/optRedis")
    public BaseResult<Object> optRedis(RedisDataType dataType, RedisOpType opType, String key, String field, String value, Double score) {
        UserInfo user = new UserInfo();
        user.setUserId("1");
        List<String> canOperaterList = Arrays.asList("1", "1269590795");
        if(opType != RedisOpType.GET && !canOperaterList.contains(user.getUserId())) {
            return BaseResult.error("没有操作权限");
        }
        if(dataType == null || opType == null || StringUtils.isBlank(key)) {
            return BaseResult.error("参数不能为空");
        }
        if(dataType == RedisDataType.HASH && StringUtils.isBlank(field) && opType != RedisOpType.DEL) {
            return BaseResult.error("参数不能为空");
        }
        if(dataType == RedisDataType.ZSET && score == null && (opType == RedisOpType.SET || opType == RedisOpType.ADD_ITEM)) {
            return BaseResult.error("参数不能为空");
        }
        log.warn("{} optRedis.dataType:{},opType:{},key:{},field:{},value:{}", user.getUserId(), dataType, opType, key, field, value);
        if(opType == RedisOpType.DEL) {
            if (jedisUtil.getKEYS().exists(key)) {
                jedisUtil.getKEYS().del(key);
                return BaseResult.ok();
            } else {
                return BaseResult.error("键不存在！");
            }
        }
        Map<String, Object> result = null;
        switch (dataType) {
            // string
            case STRING: result = opString(opType, key, value); break;
            // list
            case LIST: result = opList(opType, key, value); break;
                // set
            case SET: result = opSet(opType, key, value); break;
            // zset
            case ZSET: result = opZset(opType, key, value, score); break;
            // hash
            case HASH: result = opHash(opType, key, field, value); break;
            default: break;
        }
        return BaseResult.ok(result);
    }

    private Map<String, Object> opString(RedisOpType opType, String key, String value) {
        Map<String, Object> map = new HashMap<>();
        if(opType == RedisOpType.GET) {
            map.put("result", jedisUtil.getSTRINGS().get(key));
            map.put("ttl", jedisUtil.getKEYS().ttl(key));
        } else if (opType == RedisOpType.SET) {
            jedisUtil.getSTRINGS().set(key, value);
        }
        return map;
    }

    private Map<String, Object> opList(RedisOpType opType, String key, String value) {
        Map<String, Object> map = new HashMap<>();
        if(opType == RedisOpType.GET) {
            map.put("result", jedisUtil.getLISTS().lrange(key, 0, -1));
            map.put("ttl", jedisUtil.getKEYS().ttl(key));
        } else if (opType == RedisOpType.SET) {
            jedisUtil.getKEYS().del(key);
            if(StringUtils.isBlank(value)) return map;
            List<String> list = Arrays.stream(value.split("\\|")).filter(StringUtils::isNotBlank).collect(Collectors.toList());
            for (String item : list) {
                jedisUtil.getLISTS().lpush(key, item);
            }
        } else if(opType == RedisOpType.ADD_ITEM) {
            jedisUtil.getLISTS().lpush(key, value);
        } else if(opType == RedisOpType.DEL_ITEM) {
            jedisUtil.getLISTS().lrem(key, 0, value);
        }
        return map;
    }

    private Map<String, Object> opSet(RedisOpType opType, String key, String value) {
        Map<String, Object> map = new HashMap<>();
        if(opType == RedisOpType.GET) {
            map.put("result", jedisUtil.getSETS().smembers(key));
            map.put("ttl", jedisUtil.getKEYS().ttl(key));
        } else if (opType == RedisOpType.SET) {
            jedisUtil.getKEYS().del(key);
            if(StringUtils.isBlank(value)) return map;
            List<String> list = Arrays.stream(value.split("\\|")).filter(StringUtils::isNotBlank).collect(Collectors.toList());
            for (String item : list) {
                jedisUtil.getSETS().sadd(key, item);
            }
        } else if(opType == RedisOpType.ADD_ITEM) {
            jedisUtil.getSETS().sadd(key, value);
        } else if(opType == RedisOpType.DEL_ITEM) {
            jedisUtil.getSETS().srem(key, value);
        }
        return map;
    }

    private Map<String, Object> opZset(RedisOpType opType, String key, String value, Double score) {
        Map<String, Object> map = new HashMap<>();
        if(opType == RedisOpType.GET) {
            map.put("result", jedisUtil.getSORTSET().zrangeWithScore(key, 0, -1));
            map.put("ttl", jedisUtil.getKEYS().ttl(key));
        } else if (opType == RedisOpType.SET) {
            jedisUtil.getKEYS().del(key);
            if(StringUtils.isBlank(value)) return map;
            List<String> list = Arrays.stream(value.split("\\|")).filter(StringUtils::isNotBlank).collect(Collectors.toList());
            for (String item : list) {
                jedisUtil.getSORTSET().zadd(key, score, item);
            }
        } else if(opType == RedisOpType.ADD_ITEM) {
            jedisUtil.getSORTSET().zadd(key, score, value);
        } else if(opType == RedisOpType.DEL_ITEM) {
            jedisUtil.getSORTSET().zrem(key, value);
        }
        return map;
    }

    /**
     *
     * @param opType
     * @param key
     * @param field
     * @param value
     * @return
     */
    private Map<String, Object> opHash(RedisOpType opType, String key, String field, String value) {
        Map<String, Object> map = new HashMap<>();
        if(opType == RedisOpType.GET) {
            map.put("result", jedisUtil.getHASH().hget(key, field));
            map.put("ttl", jedisUtil.getKEYS().ttl(key));
        } else if (opType == RedisOpType.SET) {
            jedisUtil.getHASH().hset(key, field, value);
        }
        return map;
    }
}
