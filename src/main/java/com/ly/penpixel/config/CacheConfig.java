package com.ly.penpixel.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.time.Duration;

@Configuration
public class CacheConfig {

  @Value("${app.cache.ttl:10}")
  private int TTL_CONFIG;

  @Bean
  public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
    return (builder -> builder
        .withCacheConfiguration("postCache",
            RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(TTL_CONFIG)))
        .withCacheConfiguration("authorCache",
            RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(TTL_CONFIG))));
  }
}
