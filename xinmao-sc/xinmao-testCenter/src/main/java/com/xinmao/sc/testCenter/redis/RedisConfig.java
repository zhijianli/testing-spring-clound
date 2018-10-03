//package com.xinmao.sc.testCenter.redis;
//
//
//import java.util.Map;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.*;
//
//import com.xinmao.sc.testCenter.domain.TestScaleClassMessage;
//import com.xinmao.sc.testCenter.domain.TestScaleInfoMessage;
//
///**
// * @author 李志坚
// * @date 2018/2/8 
// */
//@Configuration
//public class RedisConfig {
//	
//    @Autowired
//    RedisConnectionFactory redisConnectionFactory;
//
//    @Bean
//    public RedisTemplate<String,Map<String,TestScaleClassMessage>> redisTestScaleClassTemplate() {
//        RedisTemplate<String,Map<String,TestScaleClassMessage>> template = new RedisTemplate<String,Map<String,TestScaleClassMessage>>();
//        template.setConnectionFactory(redisConnectionFactory);
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new RedisObjectSerializer());
//        return template;
//    }
//    
//    @Bean
//    public RedisTemplate<String,Map<String,TestScaleInfoMessage>> redisTestScaleInfoTemplate() {
//        RedisTemplate<String,Map<String,TestScaleInfoMessage>> template = new RedisTemplate<String,Map<String,TestScaleInfoMessage>>();
//        template.setConnectionFactory(redisConnectionFactory);
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new RedisObjectSerializer());
//        return template;
//    }
//    
////    @Bean
////    public RedisTemplate<String,Set<Integer>> redisTestScaleIndexesTemplate() {
////        RedisTemplate<String,Set<Integer>> template = new RedisTemplate<String,Set<Integer>>();
////        template.setConnectionFactory(redisConnectionFactory);
////        template.setKeySerializer(new StringRedisSerializer());
////        template.setValueSerializer(new RedisObjectSerializer());
////        return template;
////    }
//
//    @Bean
//    public RedisTemplate<String,Object> redisTestScaleIndexesTemplate() {
//        RedisTemplate<String,Object> template = new RedisTemplate<String,Object>();
//        template.setConnectionFactory(redisConnectionFactory);
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new RedisObjectSerializer());
//        return template;
//    }
//
//    
//
//}
//
