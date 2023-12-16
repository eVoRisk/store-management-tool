package com.store.management.tool.config.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "redis")
public record RedisClientConfigProperties(String configFile) {
}
