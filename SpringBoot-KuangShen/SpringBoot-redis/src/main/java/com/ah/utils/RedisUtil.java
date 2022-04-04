package com.ah.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/2016:41
 */
@Component
public class RedisUtil {
    private final RedisTemplate<String, Object> redisTemplate;


    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  key
     * @param time 时间
     * @return 是否
     */
    public boolean expire(String key, long time) {
        try {
            if (time < 0) {
                throw new RuntimeException("时间必须大于0");
            }
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断是否存在这个key
     *
     * @param key key
     * @return nick
     */
    public boolean hasKey(String key) {
        try {
            return Boolean.TRUE.equals(redisTemplate.hasKey(key));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除key
     *
     * @param key key
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 普通缓存获取
     *
     * @param key key
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存存入
     *
     * @param key   key
     * @param value 值
     * @return 是否
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增
     *
     * @param key   key
     * @param delta 要增大几
     * @return nick
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key   key
     * @param delta 要减少几
     * @return nick
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().decrement(key, delta);
    }

    /**
     * hashGet
     *
     * @param key  键
     * @param item 项
     * @return nick
     */
    public Object hashGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hash Key对应的所有键值
     *
     * @param key 键
     * @return nick
     */
    public Map<Object, Object> hashGet(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * hash Set
     *
     * @param key 键
     * @param map 对应多键值
     * @return nick
     */
    public boolean hashSet(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * hashSet 并设置时间
     * @param key  键
     * @param map  对应多键值
     * @param time 时间（秒）
     * @return nick
     */
    public boolean hashSet(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中存入数据，如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return 成功失败
     */
    public boolean hashSet(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中存入数据，如果不存在将创建
     * 并设置时间
     * @param key   键
     * @param item  项
     * @param value 值
     * @return 成功失败
     */
    public boolean hashSet(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     * @param key 键
     * @param item 项 可以是多个 不能为null
     */
    public void hashDel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表里是否有该项值
     * @param key 键
     * @param item 项
     * @return 存在与否
     */
    public boolean hashHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递减
     * @param key 键
     * @param item 项
     * @param by (小于0)
     * @return nick
     */
    public double hashEcr(String key, String item, Double by) {
        return redisTemplate.opsForHash().increment(key,item,-by);
    }

    /**
     * 根据key获取set集合值
     * @param key
     * @return nick
     */
    public Set<Object> SSet(String key) {
        try{
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据value 从一个set中查询，是否存在
     * @param key 键
     * @param value 值
     * @return 存在与否
     */
    public boolean setHasKey(String key, Object value) {
        try{
            return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(key, value));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将数据存入set缓存
     * @param key 键
     * @param values 值 可以是多个
     * @return nick
     */
    public long sSet(String key,Object... values) {
        try{
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将set数据放入缓存
     * @param key 键
     * @param time 时间(秒)
     * @param values 值 可以是多个
     * @return nick
     */
    public long sSetAndTime(String key, long time, Object... values) {
        try{
            Long count = redisTemplate.opsForSet().add(key, values);
            if(time > 0) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     * @param key 键
     * @return 长度
     */
    public long getSetSize(String key) {
        try{
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 移除值为value的
     * @param key 键
     * @param values 值 可以是多个
     * @return 成功数量
     */
    public long setRemove(String key,Object... values) {
        try{
            return redisTemplate.opsForSet().remove(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    /*-------------------------list缓存--------------------------*/

    /**
     * 获取list缓存的内容
     * @param key 键
     * @param start 开始
     * @param end 结束 0 到 -1代表所有值
     * @return list缓存内容
     */
    public List<Object> lGet(String key, long start, long end) {
        try{
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取List缓存长度
     * @param key 键
     * @return nick
     */
    public long lGetListSize(String key) {
        try{
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     * @param key 键
     * @param index 索引， index>=0时， 0 表头 1 第二个元素 依此类推，-1 表尾 -2倒数第二个元素 依此类推
     * @return nick
     */
    public Object lGetIndex(String key, long index) {
        try{
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将List放入缓存
     * @param key 键
     * @param value 值
     * @return 成功与否
     */
    public boolean lSet(String key, Object value) {
        try{
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存，并设置时间
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return 成功与否
     */
    public boolean lSet(String key, Object value, long time) {
        try{
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param values 值
     * @return 成功与否
     */
    public boolean lSet(String key, List<Object> values) {
        try{
            redisTemplate.opsForList().rightPushAll(key, values);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 将list放入缓存
     * @param key 键
     * @param values 值
     * @param time  时间(秒)
     * @return 成功与否
     */
    public boolean lSet(String key, List<Object> values, long time) {
        try{
            redisTemplate.opsForList().rightPushAll(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改list中的数据
     * @param key 键
     * @param index 索引
     * @param value 值
     * @return 成功与否
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        try{
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除N个值为value的
     * @param key 键
     * @param count 移除多少个
     * @param value 值
     * @return 成功个数
     */
    public long lRemove(String key, long count, Object value) {
        try{
            return redisTemplate.opsForList().remove(key, count ,value);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
