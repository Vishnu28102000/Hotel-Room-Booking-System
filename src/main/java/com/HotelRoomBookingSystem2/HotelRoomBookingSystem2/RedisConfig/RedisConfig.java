package com.HotelRoomBookingSystem2.HotelRoomBookingSystem2.RedisConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

	 @Bean
	    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
	        RedisTemplate<String, Object> template = new RedisTemplate<>();
	        template.setConnectionFactory(connectionFactory);

	        // String key serialization
	        StringRedisSerializer stringSerializer = new StringRedisSerializer();

	        // JSON value serialization
	        GenericJackson2JsonRedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer();

	        template.setKeySerializer(jsonSerializer);
	        
	        template.setValueSerializer(jsonSerializer);
	        template.setHashValueSerializer(jsonSerializer);

	        template.afterPropertiesSet();
	        return template;
	    }
	}