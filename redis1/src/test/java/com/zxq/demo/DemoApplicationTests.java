package com.zxq.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

@SpringBootTest
class DemoApplicationTests {


    @Test
    void contextLoads() {
    }


    @Test
    public void testString() throws InterruptedException {

//        1.获取连接
        Jedis jedis = new Jedis("localhost");


//        2.操作
        jedis.set("name", "a");
        String name = jedis.get("name");
        System.out.println(name);

        jedis.setex("name", 5, "hhhhhhhh");
        System.out.println(jedis.get("name"));
        System.out.println(jedis.get("name"));

//        Thread.sleep(5000);
//        System.out.println(jedis.get("name"));

        jedis.hset("a", "asd", "qwer");
        System.out.println(jedis.hget("a", "asd"));

    }


    @Test
    public void redisTester() {
        Jedis jedis = new Jedis("localhost", 6379, 100000);
        int i = 0;
        try {
            long start = System.currentTimeMillis();//开始毫秒数
            while (true) {
                long end = System.currentTimeMillis();
                if (end - start >= 1000) {  //当大于等于10000毫秒，相当于一秒时
                    break;
                }
                i++;
                jedis.set("test" + i, i + "");
            }
        } finally {
            jedis.close();
        }
        System.out.println("redis每秒操作：" + i + "次");
    }
}
