package com.qatix.base.spring.cache;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/23 1:59 PM
 */
@Configuration
public class CacheConfig extends CachingConfigurerSupport {

//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    @Bean
//    @Override
//    public CacheManager cacheManager() {
//        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
//        configuration = configuration.entryTtl(Duration.ofMinutes(5));
//
//        return new RedisCacheManager(
//                RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getRequiredConnectionFactory()),
//                configuration,
//                true
//        );
//    }
}
