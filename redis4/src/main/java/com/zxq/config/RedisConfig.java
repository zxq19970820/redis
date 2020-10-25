package com.zxq.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxq.domain.Person;
import com.zxq.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 2 * @Author: zxq
 * 3 * @Date: 2020/10/24 13:58
 * 4
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<Object, Person> personRedisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<Object, Person> template = new RedisTemplate<>();

        template.setConnectionFactory(redisConnectionFactory);

        Jackson2JsonRedisSerializer<Person> serializer = new Jackson2JsonRedisSerializer<>(Person.class);
        template.setDefaultSerializer(serializer);
        return template;
    }

    @Bean
    public RedisTemplate<Object, User> userRedisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<Object, User> template = new RedisTemplate<>();

        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<User> serializer = new Jackson2JsonRedisSerializer<User>(User.class);
        template.setDefaultSerializer(serializer);
        return template;

    }

//    @Bean
//    public RedisTemplate<Object, User> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<Object, User> template = new RedisTemplate<Object, User>();
//        template.setConnectionFactory(factory);
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(User.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        // key采用String的序列化方式
//        template.setKeySerializer(stringRedisSerializer);
//        // hash的key也采用String的序列化方式
//        template.setHashKeySerializer(stringRedisSerializer);
//        // value序列化方式采用jackson
//        template.setValueSerializer(stringRedisSerializer);
//        // hash的value序列化方式采用jackson
//        template.setHashValueSerializer(stringRedisSerializer);
//        template.afterPropertiesSet();
//        return template;
//    }


}