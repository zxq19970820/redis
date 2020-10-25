package com.zxq.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;

/**
 * 2 * @Author: zxq
 * 3 * @Date: 2020/10/25 16:06
 * 4
 */

@SpringBootTest
public class OpsForZset {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void add() {
        ZSetOperations<String, String> opsForZSet = stringRedisTemplate.opsForZSet();
        opsForZSet.add("score", "liubei", 90);
        opsForZSet.add("score", "guanyu", 92);
        opsForZSet.add("score", "zhangfei", 60);
        opsForZSet.add("score", "wangping", 99);

    }

    //查询
    @Test
    public void get() {
        ZSetOperations<String, String> opsForZSet = stringRedisTemplate.opsForZSet();

//        查询前两个
        Set<String> score = opsForZSet.range("score", 0, 1);
        System.out.println(score);
        for (String s : score) {
            System.out.println(s);
        }

        System.out.println("-------------");


//        查询score90-99的值
        Set<ZSetOperations.TypedTuple<String>> set = opsForZSet.rangeByScoreWithScores("score", 90, 99);
        for (ZSetOperations.TypedTuple<String> typedTuple : set) {
            System.out.println(typedTuple.getValue() + ":" + typedTuple.getScore());
        }

    }
}
