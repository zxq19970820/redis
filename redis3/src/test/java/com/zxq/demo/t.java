package com.zxq.demo;

import com.zxq.utils.RedisUtil2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 2 * @Author: zxq
 * 3 * @Date: 2020/10/24 9:46
 * 4
 */
public class t {

    @Autowired
    RedisUtil2 iRedisService;


    @Test
            public void add(){
        //将session信息存入redis，设置存活时间为30分钟
//        iRedisService.set(sessionid, sessionJson, 30 * 60L);
    }




}
