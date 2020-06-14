package com.galaxy.mecury.service.impl;

import com.galaxy.mecury.service.RedisService;
import io.lettuce.core.internal.LettuceLists;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;

/**
 * @Auther: peter
 * @Date: 2020/6/13 15:35
 * @Description:
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean set(String key, Object value) {
        boolean result = false;
        ValueOperations<Serializable, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
        result = true;

        return result;
    }

    @Override
    public boolean set(String key, Object value, Long expireTime) {
        return false;
    }

    @Override
    public void remove(String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    @Override
    public void removePattern(String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    @Override
    public void remove(String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    @Override
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public Object get(String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    @Override
    public void hmSet(String key, Object hashKey, Object value) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key,hashKey, value);
    }

    @Override
    public Object hmGet(String key, Object hashKey) {
        HashOperations<String, Object, Object>  hash = redisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }

    @Override
    public void lPush(String k, Object v) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(k, v);
    }

    @Override
    public List<Object> lRange(String k, long left, long right) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(k, left, right);
    }

    @Override
    public void add(String key, Object value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key,value);
    }

    @Override
    public Set<Object> setMembers(String key) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    @Override
    public void zAdd(String key, Object value, double scoure) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key,value,scoure);
    }

    @Override
    public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }

    @Override
    public boolean lock(String key, long expire) {
        Boolean result = (Boolean) redisTemplate.execute(new RedisCallback() {
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.set(key.getBytes(), "".getBytes(),  Expiration.seconds(expire), RedisStringCommands.SetOption.ifAbsent());
            }
        });

        return result;
    }

    @Override
    public void unlock(String key) {
        remove(key);
    }

    @Override
    public void decrement(String key) {
        redisTemplate.opsForValue().decrement(key);
    }

    @Override
    public void increment(String key) {
        redisTemplate.opsForValue().increment(key);
    }

    @Override
    public boolean decrementAtomic(String key) {
        return decrementAtomic(key, 1);
    }

    @Override
    public boolean decrementAtomicByNum(String key, long num) {
        return decrementAtomic(key, num);
    }

    private boolean decrementAtomic(String key, long num) {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/decrby_if_gt_value.lua")));
        redisScript.setResultType(Long.class);
        List<String> keys = new ArrayList<>();
        keys.add(key);
        Long result = (Long) redisTemplate.execute(redisScript, new StringRedisSerializer(),new StringRedisSerializer(), keys, String.valueOf(num));
        if (result > 0) {
            return true;
        }
        return false;
    }
}
