package com.zxq.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 2 * @Author: zxq
 * 3 * @Date: 2020/10/10 11:22
 * 4
 */

@SpringBootTest
public class OpsForString {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<Serializable,Serializable> RedisTemplate;



    //    对字符串进行操作
    @Test
    void test1() {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set("user", "uuu");        //        存字符串
        String v = (String) valueOperations.get("user");      //        取字符串
        System.out.println(v);
    }


    @Test
    void test1_1() {
        ValueOperations<Serializable, Serializable> valueOperations = RedisTemplate.opsForValue();
        valueOperations.set("user", "vvv");        //        存字符串
        String v = (String) valueOperations.get("user");      //        取字符串
        System.out.println(v);
    }


    @Test
    void t() {
//        删除键
        stringRedisTemplate.delete("user");
    }





}
