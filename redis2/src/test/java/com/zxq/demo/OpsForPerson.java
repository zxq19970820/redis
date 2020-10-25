package com.zxq.demo;

import com.zxq.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class OpsForPerson {



    @Autowired
    private RedisTemplate<Object, Person> redisTemplate1;

    @Autowired
    RedisTemplate<Serializable,Serializable> redisTemplate;



    @Test
    public void test4() {


        Person p = new Person();
        p.setPid("10011");
        p.setUsername("hahhahahahhaha");
        p.setPassword("123456");

        ListOperations<Object, Person> opsForList = redisTemplate1.opsForList();
        opsForList.leftPush("userList", p);

        Object p1 = opsForList.range("userList",0,1);
        System.out.println(p1);
    }

    @Test
    public void test4_1() {
        Person p = new Person();
        p.setPid("10011");
        p.setUsername("haha");
        p.setPassword("123456");

        List<Serializable> list = new ArrayList<>();
        ListOperations<Serializable, Serializable> opsForList = redisTemplate.opsForList();
        list.add(p);
        opsForList.leftPush("userList", (Serializable) list);
    }



}
