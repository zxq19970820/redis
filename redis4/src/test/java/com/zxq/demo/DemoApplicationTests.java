package com.zxq.demo;

import com.zxq.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<Object, Person> redisTemplate;
    //如果没有声明新的RedisTemplate，只能用RedisTemplate<String,String>
    //RedisTemplate<Object,Object>这两个可以通过@Autowired注入

    //如果没有配置某个泛型的RedisTemplate，必须用下面的写法
//    @Resource
//    RedisTemplate<String,Object>

    @Test
    void contextLoads() {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set("msg","hello redis");
        String msg = valueOperations.get("msg");
        System.out.println(msg);
    }

    @Test
    void test2(){
        ListOperations<String, String> listOperations = stringRedisTemplate.opsForList();
        //不同数据类型的键不相同
        listOperations.leftPush("nameList","rose");
        listOperations.leftPush("nameList", "jack");
        listOperations.rightPush("nameList","tom");
        //获取值
        String nameList = listOperations.leftPop("nameList");
        System.out.println(nameList);
        String nameList1 = listOperations.rightPop("nameList");
        System.out.println(nameList1);
    }

    @Test
    void test3(){
        HashOperations<String, Object, Object> hashOperations = stringRedisTemplate.opsForHash();
        hashOperations.put("person","username","zhangsan");
        hashOperations.put("person","age","20");
        hashOperations.put("person","password","123456");
        //取值
        Object o = hashOperations.get("person", "username");
        System.out.println(o);
    }

    @Test
    public void test4(){
        Person p = new Person();
        p.setPid("10011");
        p.setUsername("rose");
        p.setPassword("123456");
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("p", p);
        Object p1 = valueOperations.get("p");
        System.out.println(p1);
    }

}
