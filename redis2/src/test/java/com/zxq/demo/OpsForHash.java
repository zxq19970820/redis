package com.zxq.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 2 * @Author: zxq
 * 3 * @Date: 2020/10/25 13:52
 * 4
 */

@SpringBootTest
public class OpsForHash {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //    添加
    @Test
    void test3() {
        //判断key对应的map中是否存在key1
        HashOperations hashOperations = stringRedisTemplate.opsForHash();

        Boolean b = hashOperations.hasKey("hash", "key1");
        System.out.println("是否有键为hash:" + b);

        //往key对应的map中新增(key1,value1)
        hashOperations.put("hash", "key1", "value1");
        hashOperations.put("hash", "key2", "value2");
        hashOperations.put("hash", "key3", "value3");
        hashOperations.put("hash", "key4", "value4");
        hashOperations.put("hash", "key5", "value5");
        hashOperations.put("hash", "key6", "value6");


        //获取key对应的map中key1的值
        Object o = hashOperations.get("hash", "key1");
        System.out.println("hash中key1的值:" + o);


        /**
         * 判断hash表中是否有该项的值
         * @param key 键 不能为null
         * @param item 项 不能为null
         * @return true 存在 false不存在
         */
        Boolean aBoolean = hashOperations.hasKey("hash", "key1");
        System.out.println("hash中是否含有key1:" + aBoolean);


    }


    //    获取键值对
    @Test
    public void get() {
        HashOperations<String, Object, Object> opsForHash = stringRedisTemplate.opsForHash();

        //获取hash对应的map
        Map<Object, Object> map = opsForHash.entries("hash");
        Set<Object> objects = map.keySet();
        for (Object object : objects) {
            System.out.println(object + "........" + map.get(object));
        }

        System.out.println("分割");

        //获取hash对应的map中全部子key集合
        Set<Object> hash1 = opsForHash.keys("hash");
        for (Object k : hash1) {
            System.out.println("k........." + k);
        }

        System.out.println("分割");

        //获取hash对应的map中全部value集合
        List<Object> hash2 = opsForHash.values("hash");
        for (Object v : hash2) {
            System.out.println("v........." + v);
        }
    }

    @Test
    public void getsize() {
        HashOperations<String, Object, Object> opsForHash = stringRedisTemplate.opsForHash();
        // 获取map对象大小
        long size = opsForHash.size("hash");
        System.out.println("长度为" + size);

    }


    //    递增递减
    @Test
    public void inc() {

        HashOperations<String, Object, Object> opsForHash = stringRedisTemplate.opsForHash();
        /**
         * hash递增 如果不存在,就会创建一个 并把新增后的值返回
         * @param key 键
         * @param item 项
         * @param by 要增加几(大于0)
         * @return
         */

        Long increment = opsForHash.increment("hash", "keya", 1);
        System.out.println(increment);


        /**
         * hash递减
         * @param key 键
         * @param item 项
         * @param by 要减少记(小于0)
         * @return
         */
        Long increment1 = opsForHash.increment("hash", "keyb", -2);
        System.out.println(increment);
    }

    //    删除
    @Test
    public void delete() {

        HashOperations<String, Object, Object> opsForHash = stringRedisTemplate.opsForHash();

        //删除hash对应的map中多个子key(可变参数)
        Long delete = opsForHash.delete("hash", "key1", "key2", "key3");
        System.out.println("删除了" + delete + "个");
    }

    @Test
    public void remove() {
        stringRedisTemplate.delete("hash");
    }

}
