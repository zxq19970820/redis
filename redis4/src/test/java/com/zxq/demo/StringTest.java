package com.zxq.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * 2 * @Author: zxq
 * 3 * @Date: 2020/10/24 20:50
 * 4
 */
@SpringBootTest
public class StringTest {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set("msg", "hello redis");
        String msg = valueOperations.get("msg");
        System.out.println(msg);
    }
    @Test
    void add() {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set("userList", "hello redis");
        String msg = valueOperations.get("userList");
        System.out.println(msg);
    }

    @Test
    public void clear() {

        stringRedisTemplate.delete("msg");
    }

}
