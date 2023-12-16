package com.store.management.tool.config.redis;

import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
@Configuration
public class RedisClientConfig {

    private final RedisClientConfigProperties properties;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() throws IOException {
        return Redisson.create(Config.fromYAML(readConfigFileAsStream(properties.configFile())));
    }

    private InputStream readConfigFileAsStream(final String configFileName) {
        return getClass()
                .getClassLoader()
                .getResourceAsStream(configFileName);
    }
}
