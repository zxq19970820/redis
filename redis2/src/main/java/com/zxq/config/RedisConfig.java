package com.zxq.config;


import com.zxq.domain.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.io.Serializable;
import java.util.Collection;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<Serializable, Serializable> personRedisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<Serializable, Serializable> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

//        Jackson2JsonRedisSerializer<Person> serializer = new Jackson2JsonRedisSerializer<>(Person.class);
        Jackson2JsonRedisSerializer<Serializable> serializer = new Jackson2JsonRedisSerializer<>(Serializable.class);

        template.setDefaultSerializer(serializer);

        return template;
    }

    @Bean
    public RedisTemplate<Object, Person> PersonTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<Object, Person> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        Jackson2JsonRedisSerializer<Person> serializer = new Jackson2JsonRedisSerializer<>(Person.class);

        template.setDefaultSerializer(serializer);

        return template;
    }


    @Bean
    public RedisTemplate<Serializable, Collection> TemplateS(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<Serializable, Collection> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        Jackson2JsonRedisSerializer<Collection> serializer = new Jackson2JsonRedisSerializer<>(Collection.class);

        template.setDefaultSerializer(serializer);

        return template;
    }




}
