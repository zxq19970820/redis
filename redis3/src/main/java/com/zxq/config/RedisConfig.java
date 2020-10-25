package com.zxq.config;


import com.zxq.domain.Person;
import com.zxq.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

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
    public RedisTemplate<Object, User> userUserRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, User> template = new RedisTemplate<>();

        template.setConnectionFactory(redisConnectionFactory);

        Jackson2JsonRedisSerializer<User> serializer = new Jackson2JsonRedisSerializer<User>(User.class);
        template.setDefaultSerializer(serializer);
        return template;
    }


}
