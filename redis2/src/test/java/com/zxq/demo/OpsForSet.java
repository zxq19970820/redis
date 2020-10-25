package com.zxq.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Set;

/**
 * 2 * @Author: zxq
 * 3 * @Date: 2020/10/25 15:01
 * 4
 */
@SpringBootTest
public class OpsForSet {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Test
    public void add() {
        SetOperations<String, String> opsForSet = stringRedisTemplate.opsForSet();

        opsForSet.add("key", "values1");
        Long count = opsForSet.add("key", "values2", "values3");

        System.out.println("存入" + count + "个");
    }


//    查询
    @Test
    public void select(){

//查询所有
        SetOperations<String, String> opsForSet = stringRedisTemplate.opsForSet();
        Set<String> key = opsForSet.members("key");
        System.out.println(key);
        System.out.println("-------");
        for (String s : key) {
            System.out.println(s);
        }

//    查询是否有值
        Boolean target = opsForSet.isMember("key", "value");
        System.out.println(target);

    }

//    获取长度
    @Test
    public void getsize(){
        SetOperations<String, String> opsForSet = stringRedisTemplate.opsForSet();
        Long key1 = opsForSet.size("key");
        System.out.println("set长度为:" + key1);

    }

}
