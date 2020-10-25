package com.zxq.demo;

import com.zxq.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 2 * @Author: zxq
 * 3 * @Date: 2020/10/25 13:10
 * 4
 */

@SpringBootTest
public class OpsForList {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<Serializable, Collection> redisTemplate;

    @Autowired
    private RedisTemplate<Serializable,Serializable> redisTemplate1;


    //添加单个
    @Test
    void test2() {


        ListOperations<String, String> opsForList = stringRedisTemplate.opsForList();

        opsForList.leftPush("userList", "jack1");               //     添加到最左端
        Long aLong = opsForList.rightPush("userList", "tom1");  //     添加到最右边

//        设置时间
//        stringRedisTemplate.expire("userList",10 , TimeUnit.SECONDS);

    }

    @Test
    void test2_1() {
        ListOperations<Serializable, Collection> opsForList = redisTemplate.opsForList();
        Person person = new Person();
        person.setUsername("zhangxingqiang");
        List<Person> list = new ArrayList<>();
        list.add(person);
        opsForList.leftPush("user1", list);
    }


    @Test
    void test2_2() {
        ListOperations<Serializable, Serializable> opsForList = redisTemplate1.opsForList();
        Person person = new Person();
        person.setUsername("zxq");
        List<Serializable> list = new ArrayList<>();
        list.add(person);
        opsForList.leftPush("user1", (Serializable) list);
    }


    //添加多个
    @Test
    public void addMore() {

        ListOperations<String, String> opsForList = stringRedisTemplate.opsForList();

        List<String> list = new ArrayList<>();
        list.add("德玛西亚");
        list.add("艾欧尼亚");
        list.add("恕瑞玛");
        opsForList.rightPushAll("namelist", list);                    //添加集合
        opsForList.rightPushAll("namelist", "a", "b", "c");       //添加多个
    }

    @Test
    public void addMore1() {

        ListOperations<String, String> opsForList = stringRedisTemplate.opsForList();

        List<String> list = new ArrayList<>();
        list.add("德玛西亚");
        list.add("艾欧尼亚");
        list.add("恕瑞玛");
        opsForList.rightPushAll("namelist", list);                    //添加集合
        opsForList.rightPushAll("namelist", "a", "b", "c");       //添加多个
    }


    //    查询
    @Test
    public void select() {
        ListOperations<String, String> opsForList = stringRedisTemplate.opsForList();

        System.out.println(opsForList.range("namelist", 0, -1));   //查询全部元素
        System.out.println(opsForList.range("namelist", 0, 1));    //查询前两个元素

    }


    //    弹出
    @Test
    public void pop() {
        ListOperations<String, String> opsForList = stringRedisTemplate.opsForList();
        String namelist = opsForList.leftPop("namelist");      //        弹出最左边
        System.out.println(namelist);

        String namelist1 = opsForList.rightPop("namelist");     //        弹出最右边
        System.out.println(namelist1);
    }


    //    删除
    @Test
    public void delete() {

        ListOperations<String, String> opsForList = stringRedisTemplate.opsForList();
        opsForList.trim("namelist", 1, 0);

        Boolean namelist = stringRedisTemplate.delete("userList");
        Boolean namelist1 = stringRedisTemplate.delete("\"userList\"");

        System.out.println(namelist1);


        //           从左往右删除list中元素A  (1:从左往右 -1:从右往左 0:删除全部)
        opsForList.remove("namelist", 1, "A");
    }

}
