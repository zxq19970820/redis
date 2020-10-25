package com.zxq.demo;

import com.zxq.utils.RedisUtil2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 2 * @Author: zxq
 * 3 * @Date: 2020/10/24 20:12
 * 4
 */
@SpringBootTest
public class ListTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil2 redisUtil2;

    @Test
    public void clear(){
        Boolean userList = redisTemplate.delete("userList");
        System.out.println(userList);
    }
}
